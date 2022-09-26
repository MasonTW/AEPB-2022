package com.example.AEPB.model;

public class CarParkingState {
    private boolean available;
    private int availableCount;

    public CarParkingState(boolean available, int availableCount) {
        this.available = available;
        this.availableCount = availableCount;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public int getAvailableCount() {
        return this.availableCount;
    }
}
