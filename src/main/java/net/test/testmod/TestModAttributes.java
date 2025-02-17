package net.test.testmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.test.testmod.TestMod.MODID;

public class TestModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE,MODID);

    // Creating a new attribute for max hunger bar
    public static final DeferredHolder<Attribute, Attribute> MAX_HUNGER = ATTRIBUTES.register("max_hunger",
            () -> new RangedAttribute("attribute.name.max_hunger",20.0D, 1.0D, 1024.0D).setSyncable(true));


}
