package net.lawliet.nea_hunger.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.lawliet.nea_hunger.NeaHungerAttributes;
import net.lawliet.nea_hunger.clientServerSync.ClientPacketListner;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

//@Debug(export = true)
@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends Player{
    public LocalPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }


    @Unique
    private static final Logger nea_hunger$LOGGER = LogUtils.getLogger();


    @ModifyExpressionValue(method = "hasEnoughFoodToSprint", at = @At(value = "CONSTANT", args = "floatValue=6.0F"))
    private float changeFoodValueOnSprint(float original_value) {

        int hungerSprintValue = ClientPacketListner.hungerSprintValue;
        int max_hunger_value = (int) this.getAttributeValue(NeaHungerAttributes.MAX_HUNGER);
        return (float) Math.min(max_hunger_value - 1,hungerSprintValue);
    }
}
