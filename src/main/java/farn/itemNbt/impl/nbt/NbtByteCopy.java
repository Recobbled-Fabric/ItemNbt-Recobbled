package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagByte;

public interface NbtByteCopy extends NbtCopy {
    @Override
    default NBTTagByte copy() {
        return null;
    }
}
