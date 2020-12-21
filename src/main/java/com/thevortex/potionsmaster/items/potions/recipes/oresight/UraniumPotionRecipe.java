package com.thevortex.potionsmaster.items.potions.recipes.oresight;


import com.thevortex.potionsmaster.init.ModItems;
import com.thevortex.potionsmaster.init.ModPotions;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class UraniumPotionRecipe extends BrewingRecipe {


    public UraniumPotionRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
    }


    @Override
    public boolean isInput(ItemStack input) {
        return (PotionUtils.getPotionFromItem(input) == Potions.MUNDANE);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return (ingredient.getItem().asItem() == ModItems.CALCINATEDURANIUM_POWDER);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;

    }

    public ItemStack getOutput() {
        return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModPotions.URANIUM_SIGHT);

    }

}
