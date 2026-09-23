package farn.itemNbt.impl.nbt;


import net.minecraft.src.NBTTagShort;

public interface NbtShortCopy extends NbtCopy {
    @Override
    default NBTTagShort copy() {
        return null;
    }
}
