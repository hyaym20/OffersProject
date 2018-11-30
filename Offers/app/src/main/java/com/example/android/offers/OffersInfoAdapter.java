package com.example.android.offers;



public class OffersInfoAdapter {

    private String name;
    private String mobileNumber;
    private String placeLocation;
    private int image = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;

    public OffersInfoAdapter(String name, String mobileNumber, String placeLocation, int image) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.placeLocation = placeLocation;
        this.image = image;
    }

    public OffersInfoAdapter(String name, String mobileNumber, String placeLocation) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.placeLocation = placeLocation;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public int getImage() {
        return image;
    }

    public boolean hasImage(){
        return image!=NO_IMAGE_PROVIDED;
    }
}
