package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagCompound;

public interface NbtCompoundCopy extends NbtCopy {

    @Override
    default NBTTagCompound copy() {
        return null;
    }
}
