package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtListCopy;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.Objects;

@Mixin(NBTTagList.class)
abstract
class NbtListMixin implements NbtListCopy {
    @Shadow private List<NBTBase> tagList;

    @Shadow
    public abstract int tagCount();

    @Shadow
    public abstract NBTBase tagAt(int i);

    @Override
    @Unique
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof NBTTagList && Objects.equals(tagList, ((NBTTagList) obj).tagList));
    }

    @Override
    @Unique
    public NBTTagList copy() {
        NBTTagList copy = new NBTTagList();
        for (int i = 0; i < tagCount(); i++) {
            copy.setTag(tagAt(i).copy());
        }
        return copy;
    }
}
