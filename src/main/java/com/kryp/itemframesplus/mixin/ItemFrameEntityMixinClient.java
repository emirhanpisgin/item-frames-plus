package com.kryp.itemframesplus.mixin;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import com.kryp.itemframesplus.util.ItemFramesPlusPlayerPreferences;
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
}
