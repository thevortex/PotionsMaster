package com.thevortex.potionsmaster.network;

import java.util.function.Supplier;

import com.thevortex.potionsmaster.PotionsMaster;
import com.thevortex.potionsmaster.render.util.BlockStore;

import com.thevortex.potionsmaster.render.util.xray.Controller;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PotionPacket {
    private String potionName;

    public PotionPacket(String potion) {
        this.potionName = potion;
    }

    public static void encode(PotionPacket msg, PacketBuffer buf) {
        buf.writeUtf(msg.potionName);

    }

    public static PotionPacket decode(PacketBuffer buf) {
        return new PotionPacket(buf.readUtf());
    }

    public static class Handler {
        public static void handle(final PotionPacket message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                String removed_potion = message.potionName;
                PotionsMaster.blockStore.getStoreByReference("forge:ores/" + removed_potion).getBlockData().setDrawing(false);
                if (Controller.drawOres()) {
                    Controller.toggleDrawOres();
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
