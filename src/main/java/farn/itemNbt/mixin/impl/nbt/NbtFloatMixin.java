package farn.itemNbt.mixin.impl.nbt;
import farn.itemNbt.impl.nbt.NbtFloatCopy;
import net.minecraft.src.NBTTagFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagFloat.class)
class NbtFloatMixin implements NbtFloatCopy {
    @Shadow public float floatValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagFloat && floatValue == ((NBTTagFloat)obj).floatValue);
    }

    @Override
    @Unique
    public NBTTagFloat copy() {
        return new NBTTagFloat(floatValue);
    }
}
