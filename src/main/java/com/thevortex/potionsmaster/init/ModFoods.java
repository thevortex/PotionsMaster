package com.thevortex.potionsmaster.init;

import net.minecraft.item.Food;

public class ModFoods {


    public static final Food BEZOAR;
    public static final Food ACTIVATEDCHARCOAL;
    public static final Food GALLBLADDER;


    static {
        BEZOAR = (new Food.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
        ACTIVATEDCHARCOAL = (new Food.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
        GALLBLADDER = (new Food.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
    }
}
