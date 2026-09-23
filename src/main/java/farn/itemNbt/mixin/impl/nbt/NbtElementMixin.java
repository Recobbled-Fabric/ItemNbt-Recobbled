package farn.itemNbt.mixin.impl.nbt;

import farn.itemNbt.impl.nbt.NbtCopy;
import net.minecraft.src.NBTBase;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(NBTBase.class)
abstract class NbtElementMixin implements NbtCopy {

}
