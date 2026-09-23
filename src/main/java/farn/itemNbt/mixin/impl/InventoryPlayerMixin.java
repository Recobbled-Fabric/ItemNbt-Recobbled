package farn.itemNbt.mixin.impl;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin {

    @WrapOperation(
            method = "storePartialItemStack",
            at = @At(
                    value = "NEW",
                    target = "(III)Lnet/minecraft/src/ItemStack;"
            )
    )
    private ItemStack itemNbt_newItemStack(
            int id, int count, int damage, Operation<ItemStack> original,
            @Local(index = 1, argsOnly = true) ItemStack stack
    ) {
        ItemStack newStack = original.call(id, count, damage);
        newStack.setItemData(stack.getItemData());
        return newStack;
    }

    @WrapOperation(
            method = "storeItemStack",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;stackSize:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 1
            )
    )
    private int itemNbt_captureItemStack(ItemStack instance, Operation<Integer> original, @Local(argsOnly = true) ItemStack instance2) {
        if (Objects.equals(instance.getItemData(), instance2.getItemData()))
            return instance.stackSize;
        else {
            notchGodDamnit = true;
            return Integer.MAX_VALUE;
        }
    }

    @Unique
    private boolean notchGodDamnit;

    @WrapOperation(
            method = "storeItemStack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/InventoryPlayer;getInventoryStackLimit()I"
            )
    )
    private int itemNbt_fixStackableNBTs(InventoryPlayer instance, Operation<Integer> original) {
        if (notchGodDamnit) {
            notchGodDamnit = false;
            return Integer.MIN_VALUE;
        } else
            return instance.getInventoryStackLimit();
    }
}
