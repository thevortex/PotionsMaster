package com.thevortex.potionsmaster.reference;

public class Reference {

    // Mod Constants
    public static final String MOD_ID = "potionsmaster";
    public static final String MOD_NAME = "Potions Master";
    public static final String MOD_VERSION = "0.0.2";
    public static final String UPDATE_JSON = "";

    // UI Constants
    public static final String PREFIX_MOD = MOD_ID + ":";

    public static final String PREFIX_TEX = PREFIX_MOD + "/textures/";
    public static String tab() {
        return String.format("itemGroup.%s", MOD_ID);
    }
}
