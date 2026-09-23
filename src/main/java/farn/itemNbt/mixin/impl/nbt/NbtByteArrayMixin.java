package farn.itemNbt.mixin.impl.nbt;
import farn.itemNbt.impl.nbt.NbtByteArrayCopy;
import net.minecraft.src.NBTTagByteArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Arrays;

@Mixin(NBTTagByteArray.class)
class NbtByteArrayMixin implements NbtByteArrayCopy {
    @Shadow public byte[] byteArray;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagByteArray && Arrays.equals(byteArray, ((NBTTagByteArray)obj).byteArray));
    }

    @Override
    @Unique
    public NBTTagByteArray copy() {
        return new NBTTagByteArray(Arrays.copyOf(byteArray, byteArray.length));
    }
}
