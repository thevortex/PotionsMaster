package com.thevortex.potionsmaster.capabilities;

public interface IBrew {

    int getBrewTime();

    boolean isBrewing();

    void startBrewing();

    boolean hasCatalyst();

    void giveCatalyst();

    boolean isReadyToBrew();

    void goForReady();

    boolean isComplete();

    void brewComplete();

    void resetAll();

}
