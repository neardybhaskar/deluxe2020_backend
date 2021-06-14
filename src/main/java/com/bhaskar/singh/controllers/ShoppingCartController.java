package com.bhaskar.singh.controllers;

import com.bhaskar.singh.entity.CartItem;
import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.entity.ShoppingCart;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.service.CartItemService;
import com.bhaskar.singh.service.ProductService;
import com.bhaskar.singh.service.UserService;
import javafx.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 1:40 AM
 */

@RestController
public class ShoppingCartController {

    private final CartItemService cartItemService;

    private final ProductService productService;

    private final UserService userService;

    public ShoppingCartController(CartItemService cartItemService,
                                  ProductService productService,
                                  UserService userService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(value = "/cart/addItem", method = RequestMethod.POST)
    public ResponseEntity<?> addProductToCart(@RequestBody Map<String, String> map) {
        long productId = Long.parseLong(map.get("productId"));
        int quantity = Integer.parseInt(map.get("quantity"));
        long userId = Long.parseLong(map.get("userId"));

        Optional<Product> optionalProduct = productService.findById(productId);

        if(!optionalProduct.isPresent()) {
            throw new NoSuchElementException("Cannot find Product with id: "+productId);
        }
        Product product = optionalProduct.get();
        if(quantity > product.getUnitsInStock()) {
            return new ResponseEntity<>("Not enough Stock!!", HttpStatus.BAD_REQUEST);
        }
        cartItemService.addProductToCart(product, quantity, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cartItem/getQuantity", method = RequestMethod.POST)
    public int getCartItemCount(@RequestBody Map<String, Long> mapper) {
        long userId = mapper.get("userId");
        User user = userService.findById(userId);
        return user.getShoppingCart().getCartItem()
                .stream().mapToInt(CartItem::getQuantity).sum();
    }

    @RequestMapping(value = "/cartItem/list/{userId}", method = RequestMethod.GET)
    public Map<String, Object> getUserCartItem(@PathVariable(name = "userId") String userId) {
        User user = userService.findById(Long.parseLong(userId));
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = shoppingCart.getCartItem();

        List<Pair<?,?>> pairList = new ArrayList<>();

        //Will add cartItem and Products as pair and send in UI for retrieving data
        cartItemList.forEach(cartItem -> pairList
                .add(new Pair<>(cartItem, cartItem.getProduct())));

        Map<String, Object> cartItemDetails = new HashMap<>();
        cartItemDetails.put("cartItems", pairList);
        cartItemDetails.put("subTotalCost",shoppingCart.getGrandTotal());
        return cartItemDetails;
    }

    @RequestMapping(value = "/cartItem/delete/{cartItemId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCartItem(@PathVariable(name = "cartItemId") String cartItemId) {

        Optional<CartItem> cartItemOptional = this.cartItemService.
                findCartItemById(Long.valueOf(cartItemId));
        
        if(!cartItemOptional.isPresent()) {
            throw new NoSuchElementException("No CartElement found with Id: "+cartItemId);
        }
        this.cartItemService.deleteCartItem(cartItemOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
