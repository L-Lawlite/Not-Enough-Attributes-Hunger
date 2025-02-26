package net.lawliet.nea_hunger;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeaHungerAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, NeaHunger.MODID);

    // Creating a new attribute for max hunger bar
    public static final DeferredHolder<Attribute, Attribute> MAX_HUNGER = ATTRIBUTES.register("max_hunger",
            () -> new RangedAttribute("attribute.name.max_hunger",20.0D, 1.0D, 128.0D).setSyncable(true));

    public static final DeferredHolder<Attribute, Attribute> MAX_SATURATION = ATTRIBUTES.register("max_saturation",
            () -> new RangedAttribute("attribute.name.max_saturation", 20.0D, 0.0D, 128.0D).setSyncable(true));

}
