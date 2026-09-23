package farn.itemNbt.mixin.impl;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.itemNbt.impl.ItemStackWithNbt;
import farn.itemNbt.mixin.NewConstructor;
import farn.itemNbt.mixin.ShadowSuperConstructor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackWithNbt {

    @Shadow
    public int stackSize;
    @Shadow
    public int itemID;
    @Shadow
    public int itemDamage;
    @Unique
    public NBTTagCompound itemData;

    @SuppressWarnings("MissingUnique")
    @ShadowSuperConstructor
    public abstract void itemNbt_ObjectInit();

    @SuppressWarnings({"MissingUnique", "unused"})
    @NewConstructor
    public void itemNbt_newItemStack(int id, int count, int damage, NBTTagCompound tag) {
        itemNbt_ObjectInit();
        this.itemID = id;
        this.stackSize = count;
        this.itemDamage = damage;
        this.itemData = tag;
    }

    @Inject(method="<init>(III)V", at = @At("TAIL"))
    public void item_newItemStack(int id, int count, int damage, CallbackInfo ci) {
        this.itemData = (NBTTagCompound) NBTBase.createTagOfType((byte)10);
    }

    @Override
    public NBTTagCompound getItemData() {
        return this.itemData;
    }

    @Override
    public void setItemData(NBTTagCompound compound) {
        this.itemData = compound;
    }

    @WrapMethod(method={"isItemStackEqual", "isStackEqual", "isItemEqual"})
    private boolean itemNbt_Equal(ItemStack other, Operation<Boolean> original) {
        return original.call(other) && this.itemData.equals(other.getItemData());
    }

    @WrapMethod(method="copy")
    private ItemStack itemNbt_Copy(Operation<ItemStack> original) {
        ItemStack copy = original.call();
        copy.setItemData(this.itemData.copy());
        return copy;
    }

    @Inject(method="writeToNBT", at = @At("RETURN"))
    public void itemNbt_writeToNBT(NBTTagCompound compound, CallbackInfoReturnable<NBTTagCompound> ci) {
        compound.setCompoundTag("Data", this.itemData);
    }

    @Inject(method="readFromNBT", at = @At("RETURN"))
    public void itemNbt_readFromNBT(NBTTagCompound compound, CallbackInfo ci) {
        this.itemData = compound.getCompoundTag("Data");
    }

    @Inject(
            method = "splitStack",
            at = @At("RETURN")
    )
    private void itemNbt_setSplitStackNbt(int par1, CallbackInfoReturnable<ItemStack> cir) {
        if (!this.itemData.func_28110_c().isEmpty())
            cir.getReturnValue().setItemData(this.itemData.copy());
    }

}
