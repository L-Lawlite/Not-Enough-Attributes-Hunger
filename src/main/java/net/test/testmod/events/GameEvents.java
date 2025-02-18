package net.test.testmod.events;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.test.testmod.TestMod;
import net.test.testmod.TestModGameRules;
import net.test.testmod.clientServerSync.PlayerSyncPacket;

import java.util.Objects;

@EventBusSubscriber(modid = TestMod.MODID,bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {
    @SubscribeEvent
    public static void syncGameRulesPackets(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide) {
            int hungerSprintValue = Objects.requireNonNull(event.getEntity().getServer()).getGameRules().getInt(TestModGameRules.RULE_HUNGER_SPRINT_VALUE);
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(), new PlayerSyncPacket(hungerSprintValue));
        }
    }
}
