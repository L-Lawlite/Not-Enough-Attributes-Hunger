package net.test.testmod.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.test.testmod.TestMod;
import net.test.testmod.TestModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "onAttributeUpdated", at = @At("TAIL"))
    private void onPlayerAttributeUpdate(Holder<Attribute> attribute, CallbackInfo ci) {
        if ((LivingEntity) ((Object) this) instanceof Player player) {
            if(attribute.is(TestModAttributes.MAX_HUNGER)) {
                player.getFoodData().eat(0,0);
            }
        }
    }

}
