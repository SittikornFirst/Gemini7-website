package th.ac.mahidol.ict.gemini_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.mahidol.ict.gemini_backend.model.LoginRequest;
import th.ac.mahidol.ict.gemini_backend.model.LoginResponse;
import th.ac.mahidol.ict.gemini_backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            authService.logFailedError(loginRequest.getEmail());
            return ResponseEntity.badRequest().body(response);
        }
    }
}