package net.lawliet.nea_hunger.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.lawliet.nea_hunger.NeaHungerGameRules;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import net.lawliet.nea_hunger.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(FoodData.class)
public class FoodDataMixin implements FoodDataDuck {

    @Unique
    private Player nea_hunger$Player;

    @Override
    public void nea_hunger$GetPlayer(Player player) {
        this.nea_hunger$Player = player;
    }

    @Unique
    private int nea_hunger$GetMaxHungerAttributeValue(int original_value) {
        if (nea_hunger$Player == null) return original_value;
        return (int) nea_hunger$Player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
    }

    @Unique
    private float nea_hunger$GetMaxSaturationAttributeValue(float original_value) {
        if (nea_hunger$Player == null) return original_value;
        return (float) nea_hunger$Player.getAttributeValue(NeaHungerAttributes.MAX_SATURATION);
    }


    @ModifyExpressionValue(method = "add", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapInAdd(int value) {
        return nea_hunger$GetMaxHungerAttributeValue(value);
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapInTick(int value) {
        return nea_hunger$GetMaxHungerAttributeValue(value);
    }

    @ModifyExpressionValue(method = "needsFood", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapNeedsFood(int value) {
        return nea_hunger$GetMaxHungerAttributeValue(value);

    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=18"))
    private int modifyMaxHungerHealValue(int value) {
        float percentageHealing = Objects.requireNonNull(nea_hunger$Player.getServer()).getGameRules().getInt(NeaHungerGameRules.RULE_HUNGER_HEALING_PERCENTAGE) / 100F;
        return (int) (nea_hunger$GetMaxHungerAttributeValue(value) * percentageHealing);
    }

    @ModifyExpressionValue(method = "add", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F"))
    private float modifySaturationLevelInAdd(float original_value) {
        return Math.min(original_value, nea_hunger$GetMaxSaturationAttributeValue(original_value));
    }

}
