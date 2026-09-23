package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTTagString;

public interface NbtStringCopy extends NbtCopy {
    @Override
    default NBTTagString copy() {
        return null;
    }
}
