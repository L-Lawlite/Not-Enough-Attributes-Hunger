package net.lawliet.nea_hunger.events;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.lawliet.nea_hunger.NeaHunger;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import net.lawliet.nea_hunger.clientServerSync.ClientPacketListner;
import net.lawliet.nea_hunger.clientServerSync.PlayerSyncPacket;
import net.lawliet.nea_hunger.clientServerSync.ServerPacketListner;


@EventBusSubscriber(modid = NeaHunger.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void addAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, NeaHungerAttributes.MAX_HUNGER,20.0D);
        event.add(EntityType.PLAYER, NeaHungerAttributes.MAX_SATURATION,20.0D);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.NETWORK);
        registrar.playToClient(
                PlayerSyncPacket.TYPE,
                PlayerSyncPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPacketListner::handleDataOnNetwork,
                        ServerPacketListner::handleDataOnNetwork
                )
        );
    }
}
