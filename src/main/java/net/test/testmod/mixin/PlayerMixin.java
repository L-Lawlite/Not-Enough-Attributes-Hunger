package net.test.testmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.test.testmod.iterface.duck.FoodDataDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin{

//    @Inject(method = "<init>", at = @At(value = "INVOKE_ASSIGN",shift = At.Shift.AFTER,target = "Lnet/minecraft/world/food/FoodData;<init>()V"))
//    public void addPlayerReference(Level level, BlockPos pos, float yRot, GameProfile gameProfile, CallbackInfo ci) {
//        ((FoodDataDuck) foodData).testmod$getPlayer((Player) ((Object) this));
//
//    }
    @ModifyExpressionValue(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/world/food/FoodData"))
    public FoodData addPlayerReference(FoodData original) {
        ((FoodDataDuck) original).testmod$getPlayer((Player) ((Object) this));
        return original;
    }
}
