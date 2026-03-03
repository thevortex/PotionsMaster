package com.thevortex.potionsmaster.render.util;

import com.thevortex.potionsmaster.render.util.OutlineColor;


public class BlockData {

    private String entryname;
    private String oretag;
    private int color;
    private boolean drawing;
    private int order;
    private String recipeItem;

    public BlockData(String entryname, String oretag, int color, boolean drawing, int order, String recipeItem) {
        this.entryname = entryname;
        this.oretag = oretag;
        this.color = color;
        this.drawing = drawing;
        this.order = order;
        this.recipeItem = recipeItem;
    }

    public String getEntryName() {
        return entryname;
    }

    public String getoreTag() {
        return oretag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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

    public String getrecipeItem() {
        return recipeItem;
    }
}

