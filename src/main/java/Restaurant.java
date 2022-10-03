import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    // Part2- methods implemented
    public boolean isRestaurantOpen() {
        LocalTime timeNow=getCurrentTime();
        int openCheck= timeNow.compareTo(openingTime);
        int closeCheck= timeNow.compareTo(closingTime);
        if (openCheck>=0 && closeCheck<0) return true;
        else return false;

        //return true;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    // Part2- methods implemented

    public List<Item> getMenu() {
        //return null;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return this.menu;
    }

    // Part2- methods implemented

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //Part 3: Solution
public double calculateOrderValue(String name1, String name2) {
    Item item1 = findItemByName(name1);
    Item item2 = findItemByName(name2);
    double orderValue = item1.getPrice() + item2.getPrice();
    return orderValue;
}

}
