
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oy5836jw
 */
public interface UserInterface {
    
    /*load the data from an infile*/
    /**
     * This method will load the list of all the data from our database that is stored<br>
     * within an outside file.<br><br>
     * <b>Require</b><br>
     * There must be a complete database.tsv to pull from.<br><br>
     * 
     * <b>Ensure</b><br>
     * That an array list is filled complete with media that a user can purchased and sort through.
     * 
     * @return 
     * An array list for people to sort, search, and choose from.
     */
    public ArrayList<Media> loadList();
   
    //Start filter section methods
    /**
     * This is probably the most important method, it will use a switch in order to help search through the data.<br>
     * It will control all the switches and toggles to sort through the data, that the user wishes to find.<br><br>
     * 
     * <b>Require:</b><br>
     * The program must have initialized that array list filled with Media options. without the Filled array<br>
     * we can not sort through the database.<br><br>
     * 
     * <b>Ensure:</b><br>
     * The method will control the switches and call all methods required to properly sort, and search the Array
     * 
     * @param media ArrayList 
     * 
     */
    public void filter(ArrayList<Media> media );
    /**
     * This method will allow the user to search the array list by name, by using .contains() method.<br>
     * allowing them to find the media they desire.<br><br>
     * 
     * <b>Require:</b><br>
     * You need a populated array of Media type objects, as well as a String of the name you which to find.<br>
     * This will act as the key for searching and possibly locating desired media.<br><br>
     * 
     * <b>Ensure:</b><br>
     * That the user will be told, if their desire media is in the machine or not. if not <br>
     * they will be notified that the title they are looking for does not exist, but will also <br>
     * display media with similar names.
     * 
     * @param media ArrayList
     *  
     */
    public void searchByName(ArrayList<Media> media );
    /**
     * This method will allow users to searched based on desired Genre of movies they would like to watch.<br><br>
     * 
     * <b>Require</b><br>
     * Need an array list to search, and a key for the genre to locate in the list.<br><br>
     * 
     * <b>Ensure</b><br>
     * The user will find all titles related to there desired genre
     * 
     * @param media ArrayList
     * 
     */
    public void searchByGenre(ArrayList<Media> media);
    /**
     * Movies and games have critic scores, and this method will allow the user to sort by<br>
     * scores given by critics to find the highest rating for them. <br><br>
     * 
     * <b>Require:</b><br>
     * Requires to have a loaded array list and an Integer for searching the score rating that they want to <br>
     * search by.<br><br>
     * 
     * <b>Ensure:</b><br>
     * Will ensure that the list will be sorted and printed out in order by critics score.
     * 
     * @param media ArrayList
     * 
     */
    public void sortByScore(ArrayList<Media> media);
    /**
     * This method will sort the list From A to Z <br><br>
     * 
     * <b>Require</b><br>
     * Only requirement is to have a list of media that you wish to sort<br><br>
     * 
     * <b>Ensure</b><br>
     * That the array is sorted by name from A to Z.
     * 
     * @param media ArrayList
     */
    public void sortByAlphabeticalAtoZ(ArrayList<Media> media);
    /**
     * This method will sore the list by name for Z to A<br><br>
     * 
     * <b>Require</b><br>
     * Method requires and arraylist of type media to be sorted<br><br>
     * 
     * <b>Ensure</b><br>
     * Method will ensure that the list is sorted by name for Z to A.
     * 
     * @param media ArrayList
     */
    public void sortByAlphabeticalZtoA(ArrayList<Media> media);
    /**
     * This method will sort the array based on the release date of the media.<br><br>
     * 
     * <b>Require</b><br>
     * that we have an arrayList that can be sort by year that it was released.<br>
     * 
     * <b>Ensure</b><br>
     * That the array is sorted from oldest released date to the more recently released media.
     * 
     * @param media ArrayList
     */
    public void oldToNew(ArrayList<Media> media);
    /**
     * This method will sort the media list from Newest release date to the oldest release date.<br><br>
     * 
     * <b>Require</b><br>
     * That there is an arrayList that can be sort by year.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the media list that they user is looking through is sorted from new release date to the oldest release.
     * @param media 
     */
    public void newToOld(ArrayList<Media> media);
    /**
     * This method will show you which media are on special in terms of promo codes<br>
     * or in terms of holiday deals.<br><br>
     * <b>Require</b><br>
     * To have a list filled with media objects to apply specials to.<br><br>
     * 
     * <b>Ensure</b><br>
     * That any media object that can be used with a promo code / special is displayed to the user.
     * 
     * @param media ArrayList
     */
    public void special(ArrayList<Media> media);
    /**
     * This method will allow the user to search the media based on the rating of the media<br><br>
     * 
     * <b>Require</b><br>
     * That the media has a variable of rating that can be sorted through.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the arrayList is sorted based on user preference of rating.
     * 
     * @param media ArrayList
     * 
     */
    public void searchByRating(ArrayList<Media> media);
    /**
     * This method will sort out games, and only display media with the movie tags.<br><br>
     * <b>Require</b><br>
     * That a media arraylist is filled with two different types of media called movie and game<br><br>
     * 
     * <b>Ensure</b><br>
     * That only movies will be displayed to the user and that games will not be displayed.
     * 
     * @param media ArrayList
     */
    public void sortMovie(ArrayList<Media> media);
    /**
     * This method will sort by games only, with no movies displayed to the user<br><br>
     * <b>Require</b><br>
     * This method needs to a list filled with media that has sub-type of games.<br><br>
     * <b>Ensure</b>
     * That the user will be displayed all games that are available.
     * @param media ArrayList
     */
    public void sortGame(ArrayList<Media> media);
    //end filter methods                
    
    //check primary key, if already in cart say in cart
    /**
     * This method will add the media of your choice into your shopping cart <br>
     * as well as comparing that media with media in your cart to prevent double media.<br><br>
     * 
     * <b>Require</b><br>
     * This requires a media list to be chosen from, as well as a primary key.<br><br>
     * <b>Ensure</b><br>
     * That the media added to the cart is in the cart, and that the item is not duplicated in the cart<br>
     * 
     * @return 
     */
    public Media addToCart();
    /**
    * This method will allow a User to remove an item from there cart that they do not wish to rent.<br><br>
    * 
    * <b>Require</b><br>
    * This requires to have an item within the shopping cart for them to remove, as well as the primeKey of the<br>
    * that you wish to remove.<br><br>
    * 
    * <b>Ensure</b><br>
    * That the item the user wishes to remove from there cart, is successfully removed from the arrayList.
    * 
    * 
    * 
    */
    public void removeFromCart();
    /**
     * This method clear the array of that holds the item in your cart.<br><br>
     * <b>Require</b><br>
     * To have an array with at least one item of type media in the cart.<br><br>
     * 
     * <b>Ensure</b><br>
     * To fully clear the array of all items held with the Array cart.
     * 
     * 
     * @param cart ArrayList
     */
     
    public void emptyCart();
    /**
     * this method will allow the user to return an item using its primary key of the item. <br>
     * and update the inventory list of the item from sold to not sold.<br><br>
     * 
     * <b>Require</b><br>
     * That the user has an item to return.<br><br>
     * <b>Ensure</b><br>
     * That the item in the database is updated, and saved correctly so the next user can then rent that media.
     * 
     * @param item Media
     */
    public void returnItem(Media item);
    /**
     * This will allow the user to rent the items that they have placed within there cart, and ask<br>
     * for payment for the desired media.<br><br>
     * 
     * <b>Require</b><br>
     * That the user has at least one item in the arraylist that is their cart.
     * 
     * <b>Ensure</b><br>
     * That the user is able to purchased, and be charged for the desire media, and adjust the item<br>
     * from Available to not available.
     * 
     * 
     */
    public void checkOut();
    /**
     * This method will be called during the checkOut method, and will ask the user to enter<br>
     * a valid promocode to add a discount to the price.<br><br>
     * 
     * <b>Requires</b><br>
     * This method will need a valid code.<br><br>
     * 
     * <b>Ensure</b><br>
     * This method will be sure to verify that the code is valid, and adjust the final check out price of the cart<br>
     * based on the code that you have entered.
     * 
     * @param code String
     */
    public void applyCode(String code);
    /**
     * This method will allow an admin to call a new GUI and Admin class, do admin<br>
     * duties.<br><br>
     * 
     * <b>Require</b><br>
     * That the person accessing is an admin and not a user.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the admin makes it to the admin screen with all the methods that they are able to do.
     * 
     */
    public void callAdminScreen();
    /**
     * This method will be asked upon check out, asking the user to enter their email address, if the<br>
     * they want to receive future promocodes.<br><br>
     * 
     * <b>Require</b><br>
     * The user to enter an email if they want to receive a promocode.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the user receive a valid promo if they enter an email.
     * 
     */
    public void email();
    
    
}
