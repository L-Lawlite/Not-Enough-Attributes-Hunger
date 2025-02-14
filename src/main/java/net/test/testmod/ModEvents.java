package net.test.testmod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

import javax.print.attribute.Attribute;
import javax.swing.text.html.parser.Entity;


@EventBusSubscriber(modid = TestMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void addAttribute(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER,TestMod.MAX_HUNGER,(double) 20.0F);
    }
}
