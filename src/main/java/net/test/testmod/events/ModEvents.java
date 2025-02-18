package net.test.testmod.events;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.test.testmod.TestMod;
import net.test.testmod.TestModAttributes;
import net.test.testmod.clientServerSync.ClientPacketListner;
import net.test.testmod.clientServerSync.PlayerSyncPacket;
import net.test.testmod.clientServerSync.ServerPacketListner;


@EventBusSubscriber(modid = TestMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void addAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, TestModAttributes.MAX_HUNGER,20.0D);
        event.add(EntityType.PLAYER, TestModAttributes.MAX_SATURATION,20.0D);
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
