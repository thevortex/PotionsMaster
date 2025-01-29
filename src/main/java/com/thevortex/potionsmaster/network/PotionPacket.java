package com.thevortex.potionsmaster.network;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockData;
import com.thevortex.potionsmaster.render.util.BlockStore;
import com.thevortex.potionsmaster.render.util.xray.Controller;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PotionPacket(String potionName) implements CustomPacketPayload {

    public static final StreamCodec<FriendlyByteBuf, PotionPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            PotionPacket::potionName,
            PotionPacket::new);
    public static final Type<PotionPacket> TYPE = new Type<>(PotionsMaster.getId("potions_packet"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final PotionPacket message, IPayloadContext ctx) {
            ctx.enqueueWork(() -> {
                        String removed_potion = message.potionName;
                        toggle(removed_potion);
                    })
                    .exceptionally(e -> {
                        // Handle exception
                        ctx.disconnect(Component.translatable("potionsmaster.networking.potions_packet.failed", e.getMessage()));
                        return null;
                    });
        }

        @OnlyIn(Dist.CLIENT)
        private static void toggle(String potion) {
            BlockStore store = PotionsMaster.blockStore;
            store.getStoreByReference(potion).getBlockData().setDrawing(false);
            if(store.getStore().values().stream().noneMatch(BlockData::isDrawing)) {
                Controller.toggleDrawOres();
            }
        }
    }
}
