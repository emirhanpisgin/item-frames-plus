package com.kryp.itemframesplus.mixin;

import net.minecraft.entity.decoration.ItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public class MinimalItemFramesMixin {

	@Inject(at = @At("RETURN"), method = "getWidthPixels", cancellable = true)
	private void getWidthPixels(CallbackInfoReturnable<Integer> cir){
		cir.setReturnValue(6);
	}

	@Inject(at = @At("RETURN"), method = "getHeightPixels", cancellable = true)
	private void getHeightPixels(CallbackInfoReturnable<Integer> cir){
		cir.setReturnValue(6);
	}


}
