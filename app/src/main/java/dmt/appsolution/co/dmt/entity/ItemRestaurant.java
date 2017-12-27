package dmt.appsolution.co.dmt.entity;

import android.graphics.Point;

public class ItemRestaurant {
    private int image;
    private String name;
    private String summary;
    private int rating;
    private String details;
    private boolean favorite;
    private String webSite;
    private String number;
    private String mail;
    private String typeFood;
    private Point location;

    public ItemRestaurant(int image, String name, String summary, int rating,
                          String details, boolean favorite, String webSite,
                          String number, String mail, String typeFood, Point location) {
        this.image = image;
        this.name = name;
        this.summary = summary;
        this.rating = rating;
        this.details = details;
        this.favorite = favorite;
        this.webSite = webSite;
        this.number = number;
        this.mail = mail;
        this.typeFood = typeFood;
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
