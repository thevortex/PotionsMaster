package com.thevortex.potionsmaster.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.thevortex.potionsmaster.PotionsMaster;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;


public class PacketHandler {


    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(PotionsMaster.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static int index;

    public PacketHandler() {
    }

    public static void register() {
        registerMessage(PotionPacket.class, PotionPacket::encode, PotionPacket::decode, PotionPacket.Handler::handle);

    }

    private static <MSG> void registerMessage(Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder,
                                              BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        HANDLER.registerMessage(index++, type, encoder, decoder, consumer);
    }

    /**
     * Sends a packet to the server.<br> Must be called Client side.
     */
    public static void sendToServer(Object msg) {

    }

    /**
     * Send a packet to a specific player.<br> Must be called Server side.
     */
    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            HANDLER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}