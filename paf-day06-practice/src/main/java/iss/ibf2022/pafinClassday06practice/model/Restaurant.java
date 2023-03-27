package iss.ibf2022.pafinClassday06practice.model;

public class Restaurant {

    private String name;
    private String url;
    private String address;
    private String addressLine2;
    private String outcode;
    private String postcode;
    private Double rating;
    private String typeOfFood;

    public Restaurant() {
    }

    public Restaurant(String name, String url, String address, String addressLine2, String outcode, String postcode, Double rating, String typeOfFood) {
        this.name = name;
        this.url = url;
        this.address = address;
        this.addressLine2 = addressLine2;
        this.outcode = outcode;
        this.postcode = postcode;
        this.rating = rating;
        this.typeOfFood = typeOfFood;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getOutcode() {
        return this.outcode;
    }

    public void setOutcode(String outcode) {
        this.outcode = outcode;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTypeOfFood() {
        return this.typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    
}
