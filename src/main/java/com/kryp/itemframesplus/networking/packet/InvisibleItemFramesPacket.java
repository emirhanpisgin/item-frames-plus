package com.kryp.itemframesplus.networking.packet;

import com.kryp.itemframesplus.ItemFramesPlus;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record InvisibleItemFramesPacket(Boolean bool) implements CustomPayload {
    public static final CustomPayload.Id<InvisibleItemFramesPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of(ItemFramesPlus.MOD_ID, "invisible-item-frames"));

    public static final PacketCodec<RegistryByteBuf, InvisibleItemFramesPacket> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.BOOLEAN, InvisibleItemFramesPacket::bool, InvisibleItemFramesPacket::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
