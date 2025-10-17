package kz.senizdegen.foodiesapi.service;

import kz.senizdegen.foodiesapi.entity.UserEntity;
import kz.senizdegen.foodiesapi.io.UserRequest;
import kz.senizdegen.foodiesapi.io.UserResponse;
import kz.senizdegen.foodiesapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);
    }

    @Override
    public String getAuthenticatedUserId() {
        String loggerInUserEmail = authenticationFacade.getAuthentication().getName();
        UserEntity loggerInUser = userRepository.findByEmail(loggerInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return loggerInUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
    }

    private UserResponse convertToResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
