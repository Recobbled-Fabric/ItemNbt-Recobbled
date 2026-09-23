package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagByteArray;

public interface NbtByteArrayCopy extends NbtCopy {
    @Override
    default NBTTagByteArray copy() {
        return null;
    }
}
