package com.thevortex.potionsmaster.init;



import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

public class ModFoods {


    public static final FoodProperties BEZOAR;
    public static final FoodProperties ACTIVATEDCHARCOAL;
    public static final FoodProperties GALLBLADDER;


    static {
        BEZOAR = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
        ACTIVATEDCHARCOAL = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
        GALLBLADDER = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.5F).fast().alwaysEat().build();
    }
}
