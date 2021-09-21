package com.houseBuilder.houseParts;


public class Basement extends HousePart {

    private boolean presence;

    public boolean getPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    @Override
    public String getType() {
        return "Basement";
    }

    @Override
    public int getTime() {
        if (getPresence()) {
            time = 20;
        } else {
            time = 0;
        }
        return time;
    }
}


