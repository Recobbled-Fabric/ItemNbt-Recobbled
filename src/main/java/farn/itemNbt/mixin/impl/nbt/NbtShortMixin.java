package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtShortCopy;
import net.minecraft.src.NBTTagShort;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagShort.class)
class NbtShortMixin implements NbtShortCopy {
    @Shadow public short shortValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagShort && shortValue == ((NBTTagShort)obj).shortValue);
    }

    @Override
    @Unique
    public NBTTagShort copy() {
        return new NBTTagShort(shortValue);
    }
}
