package net.lawliet.nea_hunger.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//@Debug(export = true)
@Mixin(Gui.class)
public abstract class GuiMixin {

//    @Unique
//    private static final Logger nea_hunger$LOGGER = LogUtils.getLogger();

    @Shadow
    protected abstract Player getCameraPlayer();

    @Shadow public int rightHeight;

    // To modify the for loop
    @ModifyExpressionValue(method = "renderFood", at = @At(value = "CONSTANT", args = "intValue=10"))
    private int modifyFoodLevelForDisplay(int original_value, GuiGraphics guiGraphics, Player player, int y, int x) {
        double food_attribute_value = player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        return (int) (food_attribute_value / 2);
    }


    // to modify the k value that is height of the render in the food Level
    // Need to check this as ordinal number can change
    @ModifyVariable(method = "renderFood", at = @At("STORE"), ordinal = 4)
    private int modifySpriteHeightArg(int original_value,GuiGraphics guiGraphics, Player player, int y, int x, @Local(ordinal = 3) int j) {
        int food_attribute_value = (int) player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
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

    @Inject(method = "renderFoodLevel", at = @At("TAIL"))
    private void modifyHeightForAirLevel(GuiGraphics p_283143_, CallbackInfo ci) {
        Player player = this.getCameraPlayer();
        int food_attribute_value = (int) player.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        int max_rows = Mth.ceil(food_attribute_value / 2.0F / 10.0F);
        int height = Math.max(10 - (max_rows - 2), 3);
        this.rightHeight += (max_rows - 1) * height;
    }



}
