package com.kryp.itemframesplus.mixin;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.ItemFrameEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void modifyVisibility(ItemFrameEntityRenderState itemFrameEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if(ItemFramesPlusConfig.getOptions().getInvisibleItemFrames().equals(false)){
            itemFrameEntityRenderState.invisible = false;
        }
    }
}

