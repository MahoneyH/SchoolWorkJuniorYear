/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public class Game extends Media{
    
    private String platform;
    private String rating;

    public Game() {
    }


    public Game(int primeKey, int type, String name, String desc, String genre, String releaseYear, double criticScore, boolean aval, double price,String platform, String rating) {
        super(primeKey, type, name, desc, genre, releaseYear, criticScore, aval, price);
        this.platform = platform;
        this.rating = rating;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "%-7s \t%-8s", platform, rating);
    }
    
    
    
    
}
