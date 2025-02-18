package net.test.testmod.clientServerSync;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.test.testmod.TestMod;

public record PlayerSyncPacket(int hungerSprintValue) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PlayerSyncPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "effective_food_value"));

    public static final StreamCodec<ByteBuf, PlayerSyncPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PlayerSyncPacket::hungerSprintValue,
            PlayerSyncPacket::new
    );


    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
