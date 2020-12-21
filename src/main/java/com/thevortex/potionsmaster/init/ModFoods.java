package com.thevortex.potionsmaster.init;

import net.minecraft.item.Food;

public class ModFoods {


    public static final Food BEZOAR;
    public static final Food ACTIVATEDCHARCOAL;
    public static final Food GALLBLADDER;


    static {
        BEZOAR = (new Food.Builder()).hunger(1).saturation(0.5F).fastToEat().setAlwaysEdible().build();
        ACTIVATEDCHARCOAL = (new Food.Builder()).hunger(1).saturation(0.5F).fastToEat().setAlwaysEdible().build();
        GALLBLADDER = (new Food.Builder()).hunger(1).saturation(0.5F).fastToEat().setAlwaysEdible().build();
    }
}
