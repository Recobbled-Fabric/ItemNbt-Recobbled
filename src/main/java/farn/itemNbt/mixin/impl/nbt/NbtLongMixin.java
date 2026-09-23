package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtLongCopy;
import net.minecraft.src.NBTTagLong;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagLong.class)
class NbtLongMixin implements NbtLongCopy {
    @Shadow public long longValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagLong && longValue == ((NBTTagLong)obj).longValue);
    }

    @Override
    @Unique
    public NBTTagLong copy() {
        return new NBTTagLong(longValue);
    }
}
