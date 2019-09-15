package com.example.hophacks;

public class GroceryStore {
    private String name;
    private String address;
    private int openTime;
    private int closeTime;
    private int lateDiscountTime;
    private boolean beingFollowed;

    public GroceryStore(String nameX, String addressX, int openT, int closeT, int lateDiscountT) {
        this.name = nameX;
        this.address = addressX;
        this.openTime = openT;
        this.closeTime = closeT;
        this.lateDiscountTime = lateDiscountT;
        this.beingFollowed = false;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public int getLateDiscountTime() {
        return lateDiscountTime;
    }

    public boolean isBeingFollowed() {
        return beingFollowed;
    }

    public void setFollowStatus(boolean b) {
        beingFollowed = b;
    }

    private String formattedTime(int timeCode) {
        String ampm = "am";
        int hr = timeCode/60;
        if (hr % 24 >= 12) {
            ampm = "pm";
            hr = hr - 12;
        }
        if (hr == 0) hr = 12;
        int min = timeCode % 60;
        return hr + ":" + min + ampm;
    }

    public String discountTimes() {
        return formattedTime(lateDiscountTime) + " - " + formattedTime(closeTime);
    }

    public String toString() {
        return name + "\n" + address + "    Closes " + formattedTime(closeTime);
    }
}
