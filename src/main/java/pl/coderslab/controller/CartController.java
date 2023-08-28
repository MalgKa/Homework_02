package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.pojo.Cart;
import pl.coderslab.pojo.CartItem;
import pl.coderslab.pojo.Product;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductDao productDao;

    @GetMapping("addtocart/{id}/{quantity}")
    @ResponseBody
    public String addtocart(@PathVariable("id") Long productId, @PathVariable("quantity") Integer quantity) {
        List<Product> productsList = productDao.getList();
        Product selectedProduct = productsList.get(productId.intValue() - 1);
        System.out.println(cart.getCartItems());

        boolean found = false;

        for (CartItem cartItem : cart.getCartItems()) {
            if (selectedProduct.getId() == cartItem.getProduct().getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                System.out.println("to samo id");
                found = true;
            }
        }

        if (!found) {
            cart.addToCart(new CartItem(selectedProduct, quantity));
            System.out.println("dodano");
        }

        return "addtocart";
    }

    @GetMapping("cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cart.getCartItems());
        int allQuantity = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            allQuantity += cartItem.getQuantity();
//            cartItem.getProduct().getPrice();
        }

        model.addAttribute("allQuantity", allQuantity);
//        return cart.getCartItems().toString();
        return "cart";
    }


    @GetMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        List<CartItem> productsList = cart.getCartItems();

        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getProduct().getId() == id && productsList.get(i).getQuantity() == 1) {
                productsList.remove(productsList.get(i));
            } else if (productsList.get(i).getProduct().getId() == id && productsList.get(i).getQuantity() > 1) {
                productsList.get(i).setQuantity(productsList.get(i).getQuantity() - 1);
            }
        }

        //nie używamy tu pętli forEach!!!! Nie służy ona do usuwania elementów z listy!!!
        // Usuwanie elementów kolekcji wewnątrz pętli forEach prowadzi do niespójności w iteracji! Lepiej użyć tu iteratora lub pętli for i.
//        for (CartItem cartItem : cart.getCartItems()){
//            if(cartItem.getProduct().getId()==id && cartItem.getQuantity()==1){
//                productsList.remove(cartItem);
//            } else if(cartItem.getProduct().getId()==id && cartItem.getQuantity()>1){
//                cartItem.setQuantity(cartItem.getQuantity()-1);
//            }
//        }
        return "redirect:/cart";
    }
    @GetMapping("deleteAll")
    public String deleteAll() {
        List<CartItem> productsList = cart.getCartItems();
        productsList.clear();
        return "redirect:/cart";
    }
    @GetMapping("addProduct/{id}")
    public String addProduct(@PathVariable("id") Long id) {
        List<CartItem> productsList = cart.getCartItems();

        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getProduct().getId() == id) {
                productsList.get(i).setQuantity(productsList.get(i).getQuantity() + 1);
            }
        }
        return "redirect:/cart";
    }
}



