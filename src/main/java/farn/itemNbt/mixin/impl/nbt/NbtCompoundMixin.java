package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtCompoundCopy;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Mixin(NBTTagCompound.class)
class NbtCompoundMixin implements NbtCompoundCopy {
    @Shadow private Map<String, NBTBase> tagMap;


    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagCompound && Objects.equals(tagMap, ((NBTTagCompound) obj).tagMap));
    }

    @Override
    @Unique
    public NBTTagCompound copy() {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.tagMap = (HashMap)((HashMap)tagMap).clone();
        return var1;
    }
}
