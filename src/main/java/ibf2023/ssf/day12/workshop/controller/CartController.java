package ibf2023.ssf.day12.workshop.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2023.ssf.day12.workshop.models.Item;

@Controller
@RequestMapping (path="/cart")
public class CartController {    

    @GetMapping 
    public ModelAndView addToCart(@RequestParam String name, 
                                @RequestParam String quantity, 
                                @RequestParam(defaultValue ="") String cart) 
    {           
        List <Item> cartList = new LinkedList<>();
        //retrieve cart 
        if (!cart.isEmpty()) {
            cart += ",%s|%s".formatted(name, quantity);
            String[] cartItems = cart.split("[,|]");
            for(int i = 0; i < cartItems.length; i += 2){
                Item newItem = new Item(); //must inside the loop so it is different object everytime added to list
                newItem.setName(cartItems[i]);
                newItem.setQuantity(cartItems[i + 1]);
                cartList.add(newItem);
            }

        }
        else{
            cart += "%s|%s".formatted(name, quantity);
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setQuantity(quantity);
            cartList.add(newItem);
        }

        // System.out.println(cart);
        // for (Item item : cartList) {
        //     System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity());
        // }
        
        ModelAndView mav = new ModelAndView(); 
        mav.setViewName("index");
        mav.addObject("cart", cart);
        mav.addObject("cartList", cartList);
        mav.setStatus(HttpStatusCode.valueOf(200));       

        return mav; 
    }

}
