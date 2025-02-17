package net.test.testmod.events;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.test.testmod.TestMod;
import net.test.testmod.TestModAttributes;


@EventBusSubscriber(modid = TestMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void addAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, TestModAttributes.MAX_HUNGER,20.0D);
        event.add(EntityType.PLAYER, TestModAttributes.MAX_SATURATION,20.0D);
    }
}
