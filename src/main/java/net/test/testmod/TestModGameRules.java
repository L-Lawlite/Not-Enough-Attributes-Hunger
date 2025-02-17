package net.test.testmod;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = TestMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class TestModGameRules {
    //Defining game rules
    public static GameRules.Key<GameRules.IntegerValue> RULE_HUNGER_HEALING_PERCENTAGE;

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
     RULE_HUNGER_HEALING_PERCENTAGE = GameRules.register("hungerHealingPercentage",GameRules.Category.PLAYER, GameRules.IntegerValue.create(90));
    }

}
