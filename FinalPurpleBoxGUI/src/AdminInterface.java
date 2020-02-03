
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
public interface AdminInterface {
    /**
     * This method will allow an Admin to access the inventory system, and add items that they wish to rent.<br><br>
     * 
     * <b>Require</b><br>
     * That the admin has access to the inventory, and that they have a Item of type media that they wish to add.<br><br>
     * 
     * <b>Ensure</b><br>
     * This will update the current inventory list held with in the machine, to contain all the new items that<br> 
     * the admin wished to add.<br>
     * @param inventory ArrayList
     * @param item Media
     */
    public void addInventory(ArrayList<Media> inventory, Media item);
    /**
     * This will update the inventory document by the admin to remove the media they want.<br><br>
     * 
     * <b>Require</b><br>
     * An inventory to be loaded into the array, and an index/ primary key number to remove said items.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the media item you want is removed from the inventory list.
     * 
     * @param inventory arrayList
     * @param index int
     */
    public void removeInventory(ArrayList<Media> inventory, int index);
    /**
     * This method will allow the Admin to change the price of the movies and the games at will.<br><br>
     * 
     * <b>Require</b><br>
     * An array filled with the inventory, along with the index number(s) and the price you would<br>
     * like to change it to.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the inventory list will be updated to the new price set by admin.
     * 
     * @param inventory ArrayList
     * @param index int
     * @param price double
     */
    
    public void changePrice(ArrayList<Media> inventory, int index, double price); //change the daily rate
    /**
     * This method will allow the admin to change the discount the user can get based on the purchased amount.<br><br>
     * 
     * <b>Require</b><br>
     * That the cart array has a certain number of items based on the admins set number.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the checkout price will be adjusted based on the discount.
     * @param cart ArrayList
     */
    public void bulkDisco(ArrayList<Media> cart);
    /**
     * This method will allow and admin to change promocodes as they see fit.<br><br>
     * 
     * <b>Require</b><br>
     * That the admin has a set of promocodes that they wish to change, whether it be adding or removing 
     * <br>existing codes.<br><br>
     * 
     * <b>Ensure</b><br>
     * It will be sure to disable current working promocodes, and replace them with new promocodes.
     * 
     * @param code String
     * @return boolean 
     */
    public boolean changePromoCodes(String code); //only one code a week
    /**
     * This method will re-enable the machine so the user(s) may rent their desired media.<br><br>
     * 
     * <b>Require</b><br>
     * the machine must be disabled in order to enable the machine again.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the machine in fully enabled and ready to go for the user.
     * 
     */
    public void enable();
    /**
     * This method will disable all functions on the users end and prevent them from using the machine.<br><br>
     * 
     * <b>Require</b><br>
     * That the person disabling is an admin.<br><br>
     * 
     * <b>Ensure</b><br>
     * That the user can not use the machine until and admin enables it.
     */
    public void disable();
    
    
}
