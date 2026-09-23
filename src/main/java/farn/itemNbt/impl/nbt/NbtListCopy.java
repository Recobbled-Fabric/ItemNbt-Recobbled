package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagList;

public interface NbtListCopy extends NbtCopy {
    @Override
    default NBTTagList copy() {
        return null;
    }
}
