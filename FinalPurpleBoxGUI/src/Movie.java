/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public class Movie extends Media{
    
    private String diskType;
    private String rating;

    public Movie() {
    }

    public Movie(String diskType, String rating) {
        this.diskType = diskType;
        this.rating = rating;
    }

    public Movie(int primeKey, int type, String name, String Desc, String genre, String releaseYear, double criticScore, boolean aval, double price, String diskType, String rating) {
        super(primeKey, type, name, Desc, genre, releaseYear, criticScore, aval, price);
        this.diskType = diskType;
        this.rating = rating;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "%-7s \t%-8s", diskType, rating);
    }

    
    
    
    
    
}
