package farn.itemNbt.impl.nbt;

import net.minecraft.src.NBTBase;

public interface NbtCopy {
    default NBTBase copy() {
        return (NBTBase)this;
    }
}
