import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @BeforeEach
   public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime Mocktime= LocalTime.parse("11:00:00");
        Restaurant RestaurantMock= Mockito.spy(restaurant);
        when(RestaurantMock.getCurrentTime()).thenReturn(Mocktime);
        assertTrue(RestaurantMock.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime Mocktime= LocalTime.parse("23:00:00");
        Restaurant RestaurantMock= Mockito.spy(restaurant);
        when(RestaurantMock.getCurrentTime()).thenReturn(Mocktime);
        assertFalse(RestaurantMock.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_order_should_return_a_value_greater_than_zero(){
        String item1= "Sweet corn soup";
        String item2= "Vegetable lasagne";
        double costOfOrder= restaurant.calculateOrderValue(item1,item2);
         assertTrue(costOfOrder>0);
    }
    //Part 3: Solution
    @Test
    public void calculate_order_should_display_an_error_if_the_cost_of_the_order_is_less_than_or_equal_to_zero(){
        restaurant.addToMenu("Tangy hot tomato soup", 0);
        restaurant.addToMenu("Vegetable pizza", 0);
        String item1= "Tangy hot tomato soup";
        String item2= "Vegetable pizza";
        double costOfOrder= restaurant.calculateOrderValue(item1,item2);
        assertTrue(costOfOrder<=0,"Incorrect Order Value calculations");
    }


}