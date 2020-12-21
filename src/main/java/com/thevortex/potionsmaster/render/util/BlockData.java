package com.thevortex.potionsmaster.render.util;

import com.thevortex.potionsmaster.render.util.OutlineColor;

import net.minecraft.item.ItemStack;

public class BlockData {

    private String entryName;
    private String blockName;
    private OutlineColor color;
    private ItemStack itemStack;
    private boolean drawing;
    private int order;

    public BlockData(String entryName, String blockName, OutlineColor color, ItemStack itemStack, boolean drawing, int order) {
        this.entryName = entryName;
        this.blockName = blockName;
        this.color = color;
        this.itemStack = itemStack;
        this.drawing = drawing;
        this.order = order;
    }

    public String getEntryName() {
        return entryName;
    }

    public String getBlockName() {
        return blockName;
    }

    public OutlineColor getColor() {
        return color;
    }

    public void setColor(OutlineColor color) {
        this.color = color;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public int getOrder() {
        return order;
    }
}

