package net.sunsetsatellite.itemnbt;

import net.minecraft.src.ItemStack;

@Deprecated
public interface IDataItem {
    @Deprecated
    String getDescription(ItemStack stack);
    @Deprecated
    int getDescriptionColor(ItemStack stack);
    @Deprecated
    int getNameColor(ItemStack stack);
}
