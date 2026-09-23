package farn.itemNbt.impl;

import net.minecraft.src.NBTTagCompound;

public interface ItemStackWithNbt {

    default NBTTagCompound getItemData() {
        return null;
    }

    default void setItemData(NBTTagCompound compound) {
    }
}
