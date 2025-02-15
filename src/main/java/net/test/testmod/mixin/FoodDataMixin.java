package net.test.testmod.mixin;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.test.testmod.TestMod;
import net.test.testmod.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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
        return (int) testmod$player.getAttributeValue(TestMod.MAX_HUNGER);
    }

    @ModifyConstant(method = "add", constant = @Constant(intValue = 20))
    public int modifyMaxFoodCapInAdd(int value) {
        // How to get player reference
        return testmod$getFoodAttributeValue(value);
    }

    @ModifyConstant(method = "tick",constant = @Constant(intValue = 20))
    public int modifyMaxFoodCapInTick(int value) {
        return testmod$getFoodAttributeValue(value);
    }

    @ModifyConstant(method = "needsFood",constant = @Constant(intValue = 20))
    public int modifyMaxFoodCapNeedsFood(int value) {
        return testmod$getFoodAttributeValue(value);

    }


}
