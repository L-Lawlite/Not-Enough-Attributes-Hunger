package net.lawliet.nea_hunger.clientServerSync;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPacketListener {

    public static int hungerSprintValue;

    public static void handleHungerSprintValue(final PlayerSyncPacket playerSyncPacket, IPayloadContext context) {
        hungerSprintValue = playerSyncPacket.hungerSprintValue();
    }
}
