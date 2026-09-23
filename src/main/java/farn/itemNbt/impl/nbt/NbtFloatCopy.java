package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagFloat;

public interface NbtFloatCopy extends NbtCopy {
    @Override
    default NBTTagFloat copy() {
        return null;
    }
}
