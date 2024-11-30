package com.kryp.itemframesplus.mixin;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.decoration.ItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.render.entity.ItemFrameEntityRenderer;

@Environment(EnvType.CLIENT)
@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin {
    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 0)
    private boolean modifyVisibility(boolean x, ItemFrameEntity itemFrameEntity) {
        if(ItemFramesPlusConfig.getOptions().getInvisibleItemFrames().equals(false)){
            return false;
        }

        return itemFrameEntity.isInvisible();
    }
}

