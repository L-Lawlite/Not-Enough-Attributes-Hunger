package net.test.testmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.test.testmod.TestMod;
import net.test.testmod.TestModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Gui.class)
public class GuiMixin {

    @ModifyExpressionValue(method = "renderFood", at = @At(value = "CONSTANT", args = "intValue=10"))
    private int modifyFoodLevelForDisplay(int original_value, GuiGraphics guiGraphics, Player player, int y, int x) {
        double food_attribute_value = player.getAttributeValue(TestModAttributes.MAX_HUNGER);
        return (int) (food_attribute_value / 2);
    }

}
