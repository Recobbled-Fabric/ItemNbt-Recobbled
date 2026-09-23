package farn.itemNbt.mixin.impl;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({BlockChest.class, BlockDispenser.class, BlockFurnace.class, BlockFreezer.class})
public class BlockWithInventoryMixin {

    @WrapOperation(method="onBlockRemoval", at = @At(value = "NEW", target = "(III)Lnet/minecraft/src/ItemStack;"))
    public ItemStack itemNbt_fixContainerDrop(int id, int count, int damage, Operation<ItemStack> original, @Local(type = ItemStack.class) ItemStack itemStack) {
        ItemStack copy = original.call(id, count, damage);
        copy.setItemData(itemStack.getItemData());
        return copy;
    }
}
