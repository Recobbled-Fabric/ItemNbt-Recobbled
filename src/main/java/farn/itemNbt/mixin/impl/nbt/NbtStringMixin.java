package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtStringCopy;
import net.minecraft.src.NBTTagString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Objects;

@Mixin(NBTTagString.class)
class NbtStringMixin implements NbtStringCopy {
    @Shadow public String stringValue;

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagString && Objects.equals(stringValue, ((NBTTagString)obj).stringValue));
    }

    @Override
    @Unique
    public NBTTagString copy() {
        return new NBTTagString(stringValue);
    }
}
