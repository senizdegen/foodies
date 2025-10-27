package kz.senizdegen.foodiesapi.service;

import kz.senizdegen.foodiesapi.io.OrderRequest;
import kz.senizdegen.foodiesapi.io.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request);
    void verifyPayment(String orderId, String status);
    List<OrderResponse> getUsersOrders();
    void removeOrder(String orderId);

    List<OrderResponse> getOrdersOfAllUsers();
    void updateOrderStatus(String orderId, String status);
}
