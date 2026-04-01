package net.lawliet.nea_hunger.mixin;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class)
public abstract class GuiMixin {

    @Unique
    private static final Logger nea_hunger$LOGGER = LogUtils.getLogger();

    @Shadow
    protected abstract Player getCameraPlayer();

    @Shadow public int rightHeight;

    // To modify the for loop
    @ModifyExpressionValue(method = "extractFood", at = @At(value = "CONSTANT", args = "intValue=10"))
    private int modifyFoodLevelForDisplay(int original_value, GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight) {
        double food_attribute_value = player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        return (int) (food_attribute_value / 2);
    }


    // to modify the "yo" value that is height of the render in the food Level
    @ModifyVariable(method = "extractFood", at = @At(value = "STORE"), name = "yo")
    private int modifySpriteHeightArg(int original_value, GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight, @Local(name = "i") int i) {
        // This is going to wrong place
        int food_attribute_value = (int) player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        int max_rows = Mth.ceil(food_attribute_value / 2.0F / 10.0F);
        int height = Math.max(10 - (max_rows - 2), 3);
        int num_row = i / 10;
        return original_value - height * num_row;
    }

    // to modify the width (xo value) so it resets every 10
    @Expression("? - @(?) * 8 - 9")
    @ModifyExpressionValue(method = "extractFood", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int modifySpriteWidthArg(int original_value) {
        // This is going to wrong place
        return original_value % 10;
    }

    @Inject(method = "extractFoodLevel", at = @At("TAIL"))
    private void modifyHeightForAirLevel(GuiGraphicsExtractor graphics, CallbackInfo ci) {
        Player player = this.getCameraPlayer();
        int food_attribute_value = (int) player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        int max_rows = Mth.ceil(food_attribute_value / 2.0F / 10.0F);
        int height = Math.max(10 - (max_rows - 2), 3);
        this.rightHeight += (max_rows - 1) * height;
    }



}
