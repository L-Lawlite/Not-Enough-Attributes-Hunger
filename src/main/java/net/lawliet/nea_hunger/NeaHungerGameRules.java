package net.lawliet.nea_hunger;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.lawliet.nea_hunger.clientServerSync.PlayerSyncPacket;


@EventBusSubscriber(modid = NeaHunger.MODID)
public class NeaHungerGameRules {
    public static GameRules.Key<GameRules.IntegerValue> RULE_HUNGER_HEALING_PERCENTAGE;
    public static GameRules.Key<GameRules.IntegerValue> RULE_HUNGER_SPRINT_VALUE;

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
     RULE_HUNGER_HEALING_PERCENTAGE = GameRules.register("hungerHealingPercentage",GameRules.Category.PLAYER, GameRules.IntegerValue.create(90));
     RULE_HUNGER_SPRINT_VALUE = GameRules.register("hungerSprintValue",GameRules.Category.PLAYER,GameRules.IntegerValue.create(6, (player,value) -> {
         for(ServerPlayer serverPlayer : player.getPlayerList().getPlayers()) {
             PacketDistributor.sendToPlayer(serverPlayer,new PlayerSyncPacket(value.get()));
         }
     }));
    }

}
