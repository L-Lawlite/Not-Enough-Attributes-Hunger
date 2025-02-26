package net.lawliet.nea_hunger.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "onAttributeUpdated", at = @At("TAIL"))
    private void onPlayerAttributeUpdate(Holder<Attribute> attribute, CallbackInfo ci) {
        if (((Object) this) instanceof Player player) {
            if(attribute.is(NeaHungerAttributes.MAX_HUNGER) || attribute.is(NeaHungerAttributes.MAX_SATURATION)) {
                player.getFoodData().eat(0,0);
            }

        }
    }

}
