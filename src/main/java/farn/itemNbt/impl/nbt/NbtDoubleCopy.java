package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagDouble;

public interface NbtDoubleCopy extends NbtCopy {
    @Override
    default NBTTagDouble copy() {
        return null;
    }
}
