package net.dragonmounts.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nonnull;

public class CraftableBlockItem extends ItemBlock {
    public final CreativeTabs tab;

    public CraftableBlockItem(Block block, CreativeTabs modTab) {
        super(block);
        this.tab = modTab;
    }

    @Override
    public @Nonnull CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[]{this.tab};
    }

    @Override
    protected boolean isInCreativeTab(@Nonnull CreativeTabs targetTab) {
        for (CreativeTabs tab : this.getCreativeTabs())
            if (tab == targetTab) return true;
        return targetTab == CreativeTabs.SEARCH;
    }
}
