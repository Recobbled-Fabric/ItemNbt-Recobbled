package farn.itemNbt.mixin.impl.nbt;
import farn.itemNbt.impl.nbt.NbtDoubleCopy;
import net.minecraft.src.NBTTagDouble;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagDouble.class)
class NbtDoubleMixin implements NbtDoubleCopy {
    @Shadow public double doubleValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagDouble && doubleValue == ((NBTTagDouble)obj).doubleValue);
    }

    @Override
    @Unique
    public NBTTagDouble copy() {
        return new NBTTagDouble(doubleValue);
    }
}
