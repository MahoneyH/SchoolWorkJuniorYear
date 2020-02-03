
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author om3705is
 */
public class Purple implements UserInterface, AdminInterface{
    //creates an overall inventory list
    ArrayList<Media> inventory = new ArrayList<Media>();
    ArrayList<Media> cartList = new ArrayList<Media>();
    static ArrayList<String> promos = new ArrayList<String>();
    
    //USER METHODS
    @Override
    public ArrayList<Media> loadList(){
        ArrayList<Media> inventory = new ArrayList<Media>();
        //add 2 promo codes
        promos.add("popcorn");
        promos.add("binge");
        //Load file
        while( true ){
            ArrayList<String> bigList = new ArrayList<>();
        
            String fileName = ("./src/DatabasePurpleBox.csv");//current folder
            
    
            //read the file
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
            
                //for each line
                String thisLine = null;
                //for splitting out commas
                String[] lineArray;
            
                while (true) {
                    thisLine = reader.readLine();//reads line by line
                    if (thisLine == null ) {
                        break;
                        //breaks if empty or at the end
                    }
                    else {
                        lineArray = thisLine.split(",");//split commas put strings into array
                        for(int n = 0; n < lineArray.length; n++) {
                            if( !"".equals(lineArray[n])) {
                                bigList.add(lineArray[n]);
                            }
                        }
                    
                    }
                }
                //make a media array after seperated commas taken
                for(int m = 0; m < bigList.size() ; m+=11) {
                    int primeKey = Integer.parseInt(bigList.get(m + 0));
                    int type = Integer.parseInt(bigList.get(m + 1));
                    String name = (bigList.get(m + 2));
                    String desc = (bigList.get(m + 3));
                    String genre = (bigList.get(m + 4));
                    String releaseYear = (bigList.get(m + 5));
                    double criticScore = Double.parseDouble(bigList.get(m + 6));
                    boolean aval = Boolean.parseBoolean(bigList.get(m + 7));
                    double price = Double.parseDouble(bigList.get(m + 8));
                    String tempPlat = (bigList.get(m + 9));
                    String tempRate = (bigList.get(m + 10));
                        
                    if (type == 2) {
                        Game tempGame = new Game(primeKey, type, name, desc, genre, releaseYear,
                                                     criticScore, aval, price, tempPlat, tempRate);
                        inventory.add(tempGame);
                    }
                    else {
                        Movie tempMovie = new Movie(primeKey, type, name, desc, genre, releaseYear,
                                                     criticScore, aval, price, tempPlat, tempRate);
                        inventory.add(tempMovie);
                    }
                        
                }//end for
                
            }//end try
            catch (IOException e) {
            }
            break;
        }//end while
        
        return inventory;
        
    }//end loadlist
    @Override
    public void filter(ArrayList<Media> media ) {
        
    }
    @Override
    public void searchByName(ArrayList<Media> media ) {
        String name = JOptionPane.showInputDialog("Enter the name of the item.");
        PurpleGUI.bigTextArea.setText("Results\n");
        for (int i = 0; i<media.size(); i++ ) {
            if (media.get(i).isAval() && media.get(i).getName().toLowerCase().contains(name.toLowerCase()) ) {
                PurpleGUI.bigTextArea.append(media.get(i).toString() + "\n");
            }
        }
    }
    @Override
    public void searchByGenre(ArrayList<Media> media) {
        String name = JOptionPane.showInputDialog("Enter the genre.");
        PurpleGUI.bigTextArea.setText("Results\n");
        for (int i = 0; i<media.size(); i++ ) {
            if ( media.get(i).isAval() && media.get(i).getGenre().toLowerCase().contains(name.toLowerCase()) ) {
                PurpleGUI.bigTextArea.append(media.get(i).toString() + "\n");
            }
        }
    }
    @Override
    public void sortByScore(ArrayList<Media> media) {
        
    }
    @Override
    public void sortByAlphabeticalAtoZ(ArrayList<Media> media) {
        int minIndex;
        Media temp;
        PurpleGUI.bigTextArea.setText("");
        
        //sort
        for(int x=0; x < media.size(); x++) {
        
            minIndex = x; 

            for(int i=(x+1); i < media.size(); i++) {
                if(!(media.get(i).getName().compareTo(media.get(x).getName()) > 0)) {
                    minIndex = i;
                    temp = media.get(x);
                    media.set(x, media.get(minIndex));
                    media.set(minIndex, temp);
                }
            }
            
        }
        for(int p = 0; p< media.size(); p++ ) {
            if(media.get(p).isAval() ) {
                PurpleGUI.bigTextArea.append(media.get(p).toString() + "\n");
            }
        }
        
    }//end AtoZ
    
    @Override
    public void sortByAlphabeticalZtoA(ArrayList<Media> media) {
        int maxIndex;
        Media temp;
        PurpleGUI.bigTextArea.setText("");
        
        //sort
        for(int x=0; x < media.size(); x++) {
        
            maxIndex = x; 

            for(int i=(x+1); i < media.size(); i++) {
                if(media.get(i).getName().compareTo(media.get(x).getName()) > 0) {
                    maxIndex = i;
                    temp = media.get(x);
                    media.set(x, media.get(maxIndex));
                    media.set(maxIndex, temp);
                }
            }
            
        }
        for(int p = 0; p< media.size(); p++ ) {
            if(media.get(p).isAval() ) {
                PurpleGUI.bigTextArea.append(media.get(p).toString() + "\n");
            }
        }
        
    }//end ZtoA
        
    @Override
    public void oldToNew(ArrayList<Media> media) {
        int maxIndex;
        Media temp;
        PurpleGUI.bigTextArea.setText("");
        
        //sort
        for(int x=0; x < media.size(); x++) {
        
            maxIndex = x; 

            for(int i=(x+1); i < media.size(); i++) {
                if(!(media.get(i).getReleaseYear().compareTo(media.get(x).getReleaseYear() ) > 0)) {
                    maxIndex = i;
                    temp = media.get(x);
                    media.set(x, media.get(maxIndex));
                    media.set(maxIndex, temp);
                }
            }
            
        }
        for(int p = 0; p< media.size(); p++ ) {
            if(media.get(p).isAval() ) {
                PurpleGUI.bigTextArea.append(media.get(p).toString() + "\n");
            }
        }
        
    }//end oldtonew
    
    @Override
    public void newToOld(ArrayList<Media> media) {
        int minIndex;
        Media temp;
         PurpleGUI.bigTextArea.setText("");
        
        //sort
        for(int x=0; x < media.size(); x++) {
        
            minIndex = x; 

            for(int i=(x+1); i < media.size(); i++) {
                if(media.get(i).getReleaseYear().compareTo(media.get(x).getReleaseYear() ) > 0) {
                    minIndex = i;
                    temp = media.get(x);
                    media.set(x, media.get(minIndex));
                    media.set(minIndex, temp);
                }
            }
            
        }
        for(int p = 0; p< media.size(); p++ ) {
            if(media.get(p).isAval() ) {
                PurpleGUI.bigTextArea.append(media.get(p).toString() + "\n");
            }
        }
    }//end newtoold
    
    @Override
    public void special(ArrayList<Media> media) {
        
    }
    @Override
    public void searchByRating(ArrayList<Media> media) {
       
    }
    @Override
    public void sortMovie(ArrayList<Media> media) {
        PurpleGUI.bigTextArea.setText("");
        for(int i=0; i<media.size(); i++ ) {
            if (media.get(i).getType() == 1 && media.get(i).isAval()) {
                PurpleGUI.bigTextArea.append(media.get(i).toString() + "\n");
            }
        }
        
    }
    @Override
    public void sortGame(ArrayList<Media> media) {
        PurpleGUI.bigTextArea.setText("");
        for(int i=0; i<media.size(); i++ ) {
            if (media.get(i).getType() == 2 && media.get(i).isAval()) {
                PurpleGUI.bigTextArea.append(media.get(i).toString() + "\n");
            }
        }
        
    }
    @Override
    public Media addToCart() {
        int key;
        Media found = null;
        while (true) {
            key = Integer.parseInt( JOptionPane.showInputDialog("Enter the number of the item you wish to add to cart"));
            
            for (int i=0; i<inventory.size(); i++ ) {
                if (inventory.get(i).getPrimeKey() == key && inventory.get(i).isAval()) {
                    inventory.get(i).setAval(false);
                    cartList.add(inventory.get(i));
                    found = inventory.get(i);
                }
            }
            break;
            
        }
        return found;
        
    }
    @Override
    public void removeFromCart() {
        int key;
        while (true) {
            key = Integer.parseInt( JOptionPane.showInputDialog("Enter the number of the item you wish to remove"));
                
            for (int i=0; i<cartList.size(); i++ ) {
                if (cartList.get(i).getPrimeKey() == key ) {
                    cartList.remove(cartList.get(i));
                    inventory.get(i).setAval(true);
                }
            }
            break;
            
        }
        
    }
    @Override
    public void emptyCart() {
        PurpleGUI.bigTextArea.setText("Cart Empty");
        for (int i = 0; i<cartList.size(); i++ ) {
            cartList.get(i).setAval(true);
        }
        cartList.clear();
        
        
        
    }
    @Override
    public void returnItem(Media item) {
        item.setAval(true);
        
    }
    @Override
    public void checkOut() {
        String codeEntered = "";
        boolean foundCode = false;
        double total = cartList.get(0).getPrice() * cartList.size();
        String cardNum = "";    
        boolean flag = true;
        
        codeEntered = JOptionPane.showInputDialog("Enter promo code or press ok");
        while (flag) {
            for (int i=0; i<promos.size(); i++ ) {
                if (promos.get(i).compareTo(codeEntered) == 0 ) {
                    foundCode = true;
                    flag = false;
                }
            }
            if (foundCode == false ) {
                JOptionPane.showMessageDialog(null, "Code not found");
                flag = false;
            }
        }
        if (foundCode) {
            total = total / 2;
        }
        
        JOptionPane.showMessageDialog(null, String.format("The total is $%.2f", total));
        cardNum = JOptionPane.showInputDialog("Please enter your credit card number");
        while(cardNum.length() < 19) {
            JOptionPane.showMessageDialog(null, "Invalid entry. Card number is 19 digits.");
            cardNum = JOptionPane.showInputDialog("Enter card number.");
        }
        JOptionPane.showMessageDialog(null, "Thank you for your purchase!");
        email();
        cartList.clear();
        PurpleGUI.bigTextArea.setText("Cart Empty");
        
    }
    @Override
    public void applyCode(String code) {
        
    }
    @Override
    public void callAdminScreen() {
        
    }
    @Override
    public void email() {
        JOptionPane.showInputDialog("Enter your email for future promotions or press ok");
        JOptionPane.showMessageDialog(null, "Thanks for choosing PurpleBox");
        
    }
    //Admin Methods----------------------------------------------------------------
    @Override
    public void addInventory(ArrayList<Media> inventory, Media item) {
        inventory.add(item);
    }
    @Override
    public void removeInventory(ArrayList<Media> inventory, int index) {
        inventory.get(index).setAval(false);
    }
    @Override
    public void changePrice(ArrayList<Media> inventory, int index, double price) {
        
        while (true) {
            double newPrice;
            try {
                newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the new price for items."));
                for (int i=0; i<inventory.size(); i++ ) {
                    inventory.get(i).setPrice(newPrice);
                }
                JOptionPane.showMessageDialog(null, String.format("Price is now $%.2f", newPrice));
                break;
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid entry.");
            }
        }
        
        
    }
    @Override
    public void bulkDisco(ArrayList<Media> cart) {
        
    }
    @Override
    public boolean changePromoCodes(String code) {
        return true;
    }
    @Override
    public void enable() {
        PurpleGUI.searchButton.setEnabled(true);
        PurpleGUI.sortButton.setEnabled(true);
        PurpleGUI.userButton.setEnabled(true);
        PurpleGUI.returnButton.setEnabled(true);
        PurpleGUI.emptyButton.setEnabled(true);
        PurpleGUI.adminButton.setEnabled(true);
        PurpleGUI.cartButton.setEnabled(true);
        PurpleGUI.checkButton.setEnabled(true);
        PurpleGUI.invButton.setEnabled(true);
        PurpleGUI.addCartButton.setEnabled(true);
    }
    @Override
    public void disable() {
        PurpleGUI.searchButton.setEnabled(false);
        PurpleGUI.sortButton.setEnabled(false);
        PurpleGUI.userButton.setEnabled(false);
        PurpleGUI.returnButton.setEnabled(false);
        PurpleGUI.emptyButton.setEnabled(false);
        PurpleGUI.cartButton.setEnabled(false);
        PurpleGUI.checkButton.setEnabled(false);
        PurpleGUI.invButton.setEnabled(false);
        PurpleGUI.addCartButton.setEnabled(false);
    }
    
}
