package net.test.testmod.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.test.testmod.TestMod;
import net.test.testmod.TestModAttributes;
import net.test.testmod.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FoodData.class)
public class FoodDataMixin implements FoodDataDuck {

    @Unique
    private Player testmod$player;

    @Override
    public void testmod$getPlayer(Player player) {
        this.testmod$player = player;
    }

    @Unique
    private int testmod$getFoodAttributeValue(int original_value) {
        if (testmod$player == null) return original_value;
        return (int) testmod$player.getAttributeValue(TestModAttributes.MAX_HUNGER);
    }


    @ModifyExpressionValue(method = "add", at = @At(value = "CONSTANT", args = "intValue=20"))
    public int modifyMaxFoodCapInAdd(int value) {
        return testmod$getFoodAttributeValue(value);
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=20"))
    public int modifyMaxFoodCapInTick(int value) {
        return testmod$getFoodAttributeValue(value);
    }

    @ModifyExpressionValue(method = "needsFood", at = @At(value = "CONSTANT", args = "intValue=20"))
    public int modifyMaxFoodCapNeedsFood(int value) {
        return testmod$getFoodAttributeValue(value);

    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=18"))
    public int modifyMaxHungerHealValue(int value) {
        return (int) (testmod$getFoodAttributeValue(value) * 0.9);
    }


}
