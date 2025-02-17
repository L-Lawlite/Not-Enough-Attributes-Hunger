package net.test.testmod.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.test.testmod.TestModAttributes;
import net.test.testmod.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public class FoodDataMixin implements FoodDataDuck {

    @Unique
    private Player testmod$player;

    @Override
    public void testmod$getPlayer(Player player) {
        this.testmod$player = player;
    }

    @Unique
    private int testmod$getMaxHungerAttributeValue(int original_value) {
        if (testmod$player == null) return original_value;
        return (int) testmod$player.getAttributeValue(TestModAttributes.MAX_HUNGER);
    }

    @Unique
    private float testmod$getMaxSaturationAttributeValue(float original_value) {
        if (testmod$player == null) return original_value;
        return (float) testmod$player.getAttributeValue(TestModAttributes.MAX_SATURATION);
    }


    @ModifyExpressionValue(method = "add", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapInAdd(int value) {
        return testmod$getMaxHungerAttributeValue(value);
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapInTick(int value) {
        return testmod$getMaxHungerAttributeValue(value);
    }

    @ModifyExpressionValue(method = "needsFood", at = @At(value = "CONSTANT", args = "intValue=20"))
    private int modifyMaxFoodCapNeedsFood(int value) {
        return testmod$getMaxHungerAttributeValue(value);

    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=18"))
    private int modifyMaxHungerHealValue(int value) {
        return (int) (testmod$getMaxHungerAttributeValue(value) * 0.9);
    }

    @ModifyExpressionValue(method = "add", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F"))
    private float modifySaturationLevelInAdd(float original_value) {
        return Math.min(original_value,testmod$getMaxSaturationAttributeValue(original_value));
    }

}
