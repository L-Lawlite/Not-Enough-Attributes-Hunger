package net.lawliet.nea_hunger.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.lawliet.nea_hunger.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin{

    @ModifyExpressionValue(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/world/food/FoodData"))
    public FoodData addPlayerReference(FoodData original) {
        ((FoodDataDuck) original).nea_hunger$GetPlayer((Player) ((Object) this));
        return original;
    }
}
