package farn.itemNbt.mixin.impl.nbt;
import farn.itemNbt.impl.nbt.NbtEndCopy;
import net.minecraft.src.NBTTagEnd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NBTTagEnd.class)
class NbtEndMixin implements NbtEndCopy {
    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || obj instanceof NBTTagEnd;
    }

    @Override
    @Unique
    public NBTTagEnd copy() {
        return new NBTTagEnd();
    }
}
