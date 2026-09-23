package farn.itemNbt.mixin.impl;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.src.*;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(Container.class)
public class ContainerMixin {
    @Inject(
            method = "func_27280_a",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;itemID:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void itemNbt_captureSecondItemStack(int clickType, int flag, boolean player, EntityPlayer par4, CallbackInfoReturnable<ItemStack> cir, ItemStack var5, InventoryPlayer var6, Slot var12, ItemStack var13, ItemStack var14) {
        otherStationNBT = var14.getItemData();
    }

    @Unique
    private NBTTagCompound otherStationNBT;

    @WrapOperation(
            method = "func_27280_a",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;itemID:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private int itemNbt_continueStatement(ItemStack instance, Operation<Integer> original) {
        if (Objects.equals(instance.getItemData(), otherStationNBT))
            return instance.itemID;
        else {
            notchGodDamnit = true;
            return Integer.MIN_VALUE;
        }
    }

    @Unique
    private boolean notchGodDamnit;

    @WrapOperation(
            method = "func_27280_a",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;itemID:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 1
            )
    )
    private int itemNbt_fixStackableNBTs(ItemStack instance, Operation<Integer> original) {
        if (notchGodDamnit) {
            notchGodDamnit = false;
            return Integer.MAX_VALUE;
        } else
            return instance.itemID;
    }

    @Inject(
            method = "func_27280_a",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;itemID:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 2
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void itemNbt_captureFirstItemStack(int clickType, int flag, boolean player, EntityPlayer par4, CallbackInfoReturnable<ItemStack> cir, ItemStack var5, InventoryPlayer var6, Slot var12, ItemStack var13) {
        thisStationNBT = var13.getItemData();
    }

    @Unique
    private NBTTagCompound thisStationNBT;

    @WrapOperation(
            method = "func_27280_a",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/ItemStack;getMaxStackSize()I",
                    ordinal = 2
            )
    )
    private int itemNbt_cancelStatement(ItemStack instance, Operation<Integer> original) {
        return Objects.equals(thisStationNBT, instance.getItemData()) ? instance.itemID : 0;
    }

    @Redirect(
            method = "func_28125_a",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/src/ItemStack;itemID:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private int itemNbt_checkStatement(ItemStack instance, ItemStack arg, int i, int j, boolean flag) {
        if (Objects.equals(instance.getItemData(), arg.getItemData()))
            return instance.itemID;
        else
            return arg.itemID - 1;
    }
}
