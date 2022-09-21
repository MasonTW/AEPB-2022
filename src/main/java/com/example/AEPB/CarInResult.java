package com.example.AEPB;

public class CarInResult {
    private final boolean isSucceed;
    private final String token;

    public CarInResult(boolean isSucceed, String token) {
        this.isSucceed = isSucceed;
        this.token = token;
    }

    public boolean getIsSucceed() {
        return isSucceed;
    }

    public String getToken() {
        return token;
    }
}
