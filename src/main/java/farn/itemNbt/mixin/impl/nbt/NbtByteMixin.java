package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtByteCopy;
import net.minecraft.src.NBTTagByte;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagByte.class)
class NbtByteMixin implements NbtByteCopy {
    @Shadow public byte byteValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagByte && byteValue == ((NBTTagByte)obj).byteValue);
    }

    @Override
    @Unique
    public NBTTagByte copy() {
        return new NBTTagByte(byteValue);
    }
}
