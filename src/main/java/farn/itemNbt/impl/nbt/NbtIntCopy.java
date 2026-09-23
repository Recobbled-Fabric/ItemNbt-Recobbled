package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagInt;

public interface NbtIntCopy extends NbtCopy {
    @Override
    default NBTTagInt copy() {
        return null;
    }
}
