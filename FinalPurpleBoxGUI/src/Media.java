/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public class Media {
    
    private int primeKey;
    private int type; //1 = movie, 2 = games
    private String name;
    private String Desc;
    private String genre;
    private String releaseYear;
    private double criticScore;
    private boolean aval;
    private double price;

    public Media() {
    }

    public Media(int primeKey, int type, String name, String Desc, String genre, String releaseYear, double criticScore, boolean aval, double price) {
        this.primeKey = primeKey;
        this.type = type;
        this.name = name;
        this.Desc = Desc;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.criticScore = criticScore;
        this.aval = aval;
        this.price = price;
    }

    public int getPrimeKey() {
        return primeKey;
    }

    public void setPrimeKey(int primeKey) {
        this.primeKey = primeKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getCriticScore() {
        return criticScore;
    }

    public void setCriticScore(double criticScore) {
        this.criticScore = criticScore;
    }

    public boolean isAval() {
        return aval;
    }

    public void setAval(boolean aval) {
        this.aval = aval;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static String heading() {
        String a = "#";
        String b = "Name";
        String c = "Description";
        String d = "Genre";
        String e = "Released";
        String f = "Score";
        String g = "Type";
        String h = "Rating";
        
        return String.format("%-3s \t%-39s       \t%-45s \t%-19s   \t%-20s \t%-8s \t%-8s \t%-6s%n%n", a, b, c, d, e, f, g, h);
    }

    @Override
    public String toString() {
        /*return "Media{" + "primeKey=" + primeKey + ", type=" + type + 
                ", name=" + name + ", Desc=" + Desc + ", genre=" + genre + 
                ", releaseYear=" + releaseYear + ", criticScore=" + criticScore + 
                ", aval=" + aval + ", price=" + price + '}';
        */
        
        return String.format("%-3d   \t%-39s       \t%-45s \t%-19s   \t%-20s  \t%.1f/5 \t", primeKey, name, Desc, genre, releaseYear, criticScore);
    }
    
    
    
}
