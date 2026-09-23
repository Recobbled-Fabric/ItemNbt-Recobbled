package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtIntCopy;
import net.minecraft.src.NBTTagInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagInt.class)
class NbtIntMixin implements NbtIntCopy {
    @Shadow public int intValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagInt && intValue == ((NBTTagInt)obj).intValue);
    }

    @Override
    @Unique
    public NBTTagInt copy() {
        return new NBTTagInt(intValue);
    }
}
