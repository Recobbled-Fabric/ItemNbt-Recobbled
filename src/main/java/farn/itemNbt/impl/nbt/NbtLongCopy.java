package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagLong;

public interface NbtLongCopy extends NbtCopy {
    @Override
    default NBTTagLong copy() {
        return null;
    }
}
