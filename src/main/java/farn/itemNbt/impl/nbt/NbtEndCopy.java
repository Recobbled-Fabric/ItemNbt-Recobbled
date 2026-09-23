package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagEnd;

public interface NbtEndCopy extends NbtCopy {
    @Override
    default NBTTagEnd copy() {
        return null;
    }
}
