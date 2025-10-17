package kz.senizdegen.foodiesapi.service;

import kz.senizdegen.foodiesapi.entity.CartEntity;
import kz.senizdegen.foodiesapi.io.CartRequest;
import kz.senizdegen.foodiesapi.io.CartResponse;
import kz.senizdegen.foodiesapi.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    public CartResponse addToCart(CartRequest request) {
        String loggedInUserId = userService.getAuthenticatedUserId();
        validateFoodId(request);

        CartEntity cart = cartRepository.findByUserId(loggedInUserId)
                .orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));

        Map<String, Integer> cartItems = cart.getItems();
        cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);

        cart.setItems(cartItems);
        cart = cartRepository.save(cart);

        return convertToResponse(cart);
    }

    @Override
    public CartResponse getCartOfCurrentUser() {
        String loggedInUserId = userService.getAuthenticatedUserId();
        CartEntity cart = cartRepository.findByUserId(loggedInUserId)
                .orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));

        return convertToResponse(cart);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.getAuthenticatedUserId();
        cartRepository.deleteByUserId(loggedInUserId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest request) {
        String loggedInUserId = userService.getAuthenticatedUserId();
        validateFoodId(request);

        CartEntity entity = cartRepository.findByUserId(loggedInUserId)
                .orElseThrow(() -> new RuntimeException("Cart is not found"));

        Map<String, Integer> cartItems = entity.getItems();
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (cartItems.containsKey(request.getFoodId())) {
            int currentQty = cartItems.get(request.getFoodId());
            if (currentQty > 1) {
                cartItems.put(request.getFoodId(), currentQty - 1);
            } else {
                cartItems.remove(request.getFoodId());
            }
            entity.setItems(cartItems);
            entity = cartRepository.save(entity);
        }

        return convertToResponse(entity);
    }

    private void validateFoodId(CartRequest request) {
        if (request.getFoodId() == null || request.getFoodId().isEmpty()) {
            throw new IllegalArgumentException("Food ID cannot be null or empty");
        }
    }

    private CartResponse convertToResponse(CartEntity cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cart.getItems())
                .build();
    }
}

