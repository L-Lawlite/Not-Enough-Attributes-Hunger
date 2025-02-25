package net.test.testmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.test.testmod.TestModAttributes;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;


//@Debug(export = true)
@Mixin(Gui.class)
public abstract class GuiMixin {

//    @Unique
//    private static final Logger testmod$LOGGER = LogUtils.getLogger();

    @Shadow
    protected abstract Player getCameraPlayer();

    // To modify the for loop
    @ModifyExpressionValue(method = "renderFood", at = @At(value = "CONSTANT", args = "intValue=10"))
    private int modifyFoodLevelForDisplay(int original_value, GuiGraphics guiGraphics, Player player, int y, int x) {
        double food_attribute_value = player.getAttributeValue(TestModAttributes.MAX_HUNGER);
        return (int) (food_attribute_value / 2);
    }


    // to modify the k value that is height of the render in the food Level
    // Need to check this as ordinal number can change
    @ModifyVariable(method = "renderFood", at = @At("STORE"), ordinal = 4)
    private int modifySpriteHeightArg(int original_value,GuiGraphics guiGraphics, Player player, int y, int x, @Local(ordinal = 3) int j) {
        int food_attribute_value = (int) player.getAttributeValue(TestModAttributes.MAX_HUNGER);
        int max_rows = Mth.ceil(food_attribute_value / 2.0F / 10.0F);
        int height = Math.max(10 - (max_rows - 2), 3);
        int num_row = j / 10;
        return original_value - height * num_row;
    }

    // to modify the width (l value) so it resets every 10
    // Need to check this as ordinal number can change
    @ModifyVariable(method = "renderFood", at = @At("STORE"), ordinal = 5)
    private int modifySpriteWidthArg(int original_value,GuiGraphics guiGraphics, Player player, int y, int x, @Local(ordinal = 3) int j) {
        return x - (j % 10) * 8 - 9;
    }

    //
    @ModifyExpressionValue(method = "renderFoodLevel", at = @At(value = "CONSTANT", args = "intValue=10"))
    private int modifyHeightForAirLevel(int original) {
        Player player = this.getCameraPlayer();
        int food_attribute_value = (int) player.getAttributeValue(TestModAttributes.MAX_HUNGER);
        int max_rows = Mth.ceil(food_attribute_value / 2.0F / 10.0F);
        int height = Math.max(10 - (max_rows - 2), 3);
        return (max_rows - 1) * height + original;
    }



}
