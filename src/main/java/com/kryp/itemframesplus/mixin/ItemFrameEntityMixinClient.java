package com.kryp.itemframesplus.mixin;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ItemFrameEntity.class)
public class ItemFrameEntityMixinClient {
    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V", shift = At.Shift.BEFORE), cancellable = true)
    private void onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        if (player.isSneaking()) {
            Boolean invisibleItemFrames = ItemFramesPlusConfig.getOptions().getInvisibleItemFrames();

            if(invisibleItemFrames == null) {
                invisibleItemFrames = true;
            }

            if(invisibleItemFrames) {
                ((ItemFrameEntity) (Object) this).setInvisible(!((ItemFrameEntity) (Object) this).isInvisible());
            }else{
                player.sendMessage(Text.translatable("util.itemframesplus.cantChangeVisibility").setStyle(Style.EMPTY.withColor(Formatting.RED)), false);
            }

            info.setReturnValue(ActionResult.CONSUME);
        }
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;dropHeldStack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Z)V", shift = At.Shift.BEFORE))
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ((ItemFrameEntity) (Object) this).setInvisible(false);
    }

    @Inject(method = "calculateBoundingBox(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Lnet/minecraft/util/math/Box;", at = @At("HEAD"), cancellable = true)
    protected void calculateBoundingBox(BlockPos pos, Direction side, CallbackInfoReturnable<Box> cir) {
        // Shrink the bounding box by adjusting the size factors (d, e, g)
        float f = 0.46875F;
        Vec3d vec3d = Vec3d.ofCenter(pos).offset(side, -0.46875);
        Direction.Axis axis = side.getAxis();

        // Shrink the hitbox size by modifying these values
        double d = axis == Direction.Axis.X ? 0.0625 : 0.375;  // Smaller size on X-axis
        double e = axis == Direction.Axis.Y ? 0.0625 : 0.375;  // Smaller size on Y-axis
        double g = axis == Direction.Axis.Z ? 0.0625 : 0.375;  // Smaller size on Z-axis

        cir.setReturnValue(Box.of(vec3d, d, e, g));
    }
}
