package th.ac.mahidol.ict.gemini_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.mahidol.ict.gemini_backend.entity.User;
import th.ac.mahidol.ict.gemini_backend.model.LoginRequest;
import th.ac.mahidol.ict.gemini_backend.model.LoginResponse;
import th.ac.mahidol.ict.gemini_backend.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return new LoginResponse(false, "User not found");
        }

        User user = userOptional.get();

        // In a real application, you should use password encoding
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(false, "Invalid credentials");
        }

        // Generate a simple token (in production, use a proper JWT)
        String token = UUID.randomUUID().toString();

        // Log the login action
        logAction(user.getUserId(), "Login successful");

        return new LoginResponse(
                user.getUserId(),
                user.getName(),
                user.getRole(),
                token,
                true,
                "Login successful"
        );
    }

    private void logAction(String userId, String action) {
        // In a real application, you would save this to a log table
        System.out.println("User " + userId + ": " + action);
    }

    public void logFailedError(String email) {
        // Log failed login attempt
        System.out.println("Failed login attempt for email: " + email);
    }
}