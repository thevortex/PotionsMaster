package com.thevortex.potionsmaster.network;

import java.util.function.Supplier;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockInfo;
import com.thevortex.potionsmaster.render.util.BlockStore;

import com.thevortex.potionsmaster.render.util.xray.Controller;
import com.thevortex.potionsmaster.render.util.xray.Render;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class PotionPacket {
    private String potionName;

    public PotionPacket(String potion) {
        this.potionName = potion;
    }

    public static void encode(PotionPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.potionName);

    }

    public static PotionPacket decode(FriendlyByteBuf buf) {
        return new PotionPacket(buf.readUtf());
    }

    public static class Handler {
        public static void handle(final PotionPacket message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                String removed_potion = message.potionName;
                BlockStore.BlockDataWithUUID store = PotionsMaster.blockStore.getStoreByReference("forge:ores/" + removed_potion);
                store.getBlockData().setDrawing(false);
                if (Controller.drawOres()) {
                    Controller.toggleDrawOres();
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
