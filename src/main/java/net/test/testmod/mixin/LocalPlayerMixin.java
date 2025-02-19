package net.test.testmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.test.testmod.TestModAttributes;
import net.test.testmod.clientServerSync.ClientPacketListner;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

//    @Unique
//    private static final Logger testmod$LOGGER = LogUtils.getLogger();


    @ModifyExpressionValue(method = "hasEnoughFoodToStartSprinting", at = @At(value = "CONSTANT", args = "floatValue=6.0F"))
    private float changeFoodValueOnSprint(float original_value) {

        int hungerSprintValue = ClientPacketListner.hungerSprintValue;
        int max_hunger_value = (int) ((Player) ((Object) this)).getAttributeValue(TestModAttributes.MAX_HUNGER);
//        testmod$LOGGER.info("game_rule = {} , max_hunger_value = {}",hungerSprintValue,max_hunger_value);
        return (float) Math.min(max_hunger_value - 1,hungerSprintValue);
    }
}
