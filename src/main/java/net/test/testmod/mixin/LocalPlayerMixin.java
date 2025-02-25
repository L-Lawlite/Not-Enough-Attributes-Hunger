package net.test.testmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.test.testmod.TestModAttributes;
import net.test.testmod.clientServerSync.ClientPacketListner;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

//@Debug(export = true)
@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends Player{
    public LocalPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

//    @Unique
//    private static final Logger testmod$LOGGER = LogUtils.getLogger();

    @ModifyExpressionValue(method = "hasEnoughFoodToStartSprinting", at = @At(value = "CONSTANT", args = "floatValue=6.0F"))
    private float changeFoodValueOnSprint(float original_value) {

        int hungerSprintValue = ClientPacketListner.hungerSprintValue;
        int max_hunger_value = (int) this.getAttributeValue(TestModAttributes.MAX_HUNGER);
//        testmod$LOGGER.info("game_rule = {} , max_hunger_value = {}",hungerSprintValue,max_hunger_value);
        return (float) Math.min(max_hunger_value - 1,hungerSprintValue);
    }
}
