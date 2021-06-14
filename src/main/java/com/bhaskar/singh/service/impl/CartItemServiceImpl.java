package com.bhaskar.singh.service.impl;

import com.bhaskar.singh.entity.CartItem;
import com.bhaskar.singh.entity.Product;
import com.bhaskar.singh.entity.ShoppingCart;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.repository.CartItemRepository;
import com.bhaskar.singh.repository.ShoppingCartRepository;
import com.bhaskar.singh.repository.UserRepository;
import com.bhaskar.singh.service.CartItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Bhaskar Singh
 * @date 6/9/2021 9:11 PM
 */

@Transactional
@Service
public class CartItemServiceImpl implements CartItemService {

    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    public CartItemServiceImpl(UserRepository userRepository,
                               CartItemRepository cartItemRepository,
                               ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public CartItem addProductToCart(Product product, int quantity, long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()) {
            throw new NoSuchElementException();
        }

        User user = optionalUser.get();
        ShoppingCart shoppingCart = user.getShoppingCart();

        Optional<List<CartItem>> optionalCartItems = findCartItemsByShoppingCart(shoppingCart);

        if(optionalCartItems.isPresent()) {
            for (CartItem cartItem: optionalCartItems.get()) {
                if(cartItem.getProduct().getId().equals(product.getId())) {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItem.setGrandTotal(product.getUnitPrice()
                            .multiply(new BigDecimal(cartItem.getQuantity())));
                    shoppingCart.setGrandTotal(shoppingCart.getGrandTotal()
                            .add(product.getUnitPrice().multiply(new BigDecimal(quantity))));
                    shoppingCartRepository.save(shoppingCart);
                    return cartItemRepository.save(cartItem);
                }
            }
        }

        shoppingCart.setGrandTotal(shoppingCart.getGrandTotal()
                .add(product.getUnitPrice().multiply(new BigDecimal(quantity))));
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setGrandTotal(product.getUnitPrice().multiply(new BigDecimal(quantity)));

        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem = cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Override
    public Optional<CartItem> findCartItemById(Long cartItemId) {
        return this.cartItemRepository.findById(cartItemId);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        shoppingCart.setGrandTotal(shoppingCart.getGrandTotal().subtract(cartItem.getGrandTotal()));
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.delete(cartItem);
    }

    private Optional<List<CartItem>> findCartItemsByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }
}
