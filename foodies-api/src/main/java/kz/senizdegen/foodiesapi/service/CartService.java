package kz.senizdegen.foodiesapi.service;

import kz.senizdegen.foodiesapi.io.CartRequest;
import kz.senizdegen.foodiesapi.io.CartResponse;

public interface CartService {
    CartResponse addToCart(CartRequest request);
    CartResponse getCartOfCurrentUser();
    void clearCart();
    CartResponse removeFromCart(CartRequest request);
}
