package kz.senizdegen.foodiesapi.service;

import kz.senizdegen.foodiesapi.io.UserRequest;
import kz.senizdegen.foodiesapi.io.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest request);
    String getAuthenticatedUserId();
}
