package com.example.Traveling.service;

import com.example.Traveling.model.User;
import com.example.Traveling.repository.UserRepository;
import com.example.Traveling.request.LoginRequest;
import com.example.Traveling.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmailService emailService; // EmailService injected

    // ---------------------- REGISTER ----------------------
    public ResponseEntity<?> register(RegisterRequest r) {

        // Email already exists?
        if (userRepo.findByEmail(r.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email Already Exists");
        }

        // Password match?
        if (!r.getPassword().equals(r.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password Mismatch");
        }

        // Create User
        User user = new User();
        user.setName(r.getName());
        user.setEmail(r.getEmail());
        user.setPassword(r.getPassword());

        userRepo.save(user);

        // -------------------- EMAIL TRIGGER --------------------
        String subject = "New User Registered";
        String msg = "A new user has registered:\n\n"
                + "Name: " + user.getName() + "\n"
                + "Email: " + user.getEmail() + "\n";

        try {
            emailService.sendEmail("shivamsaini6013@gmail.com", subject, msg);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
        // -------------------------------------------------------

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Registered Successfully");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    // ---------------------- LOGIN ----------------------
    public ResponseEntity<?> login(LoginRequest r) {

        User user = userRepo.findByEmail(r.getEmail());

        if (user == null) {
            return ResponseEntity.badRequest().body("User Not Found!");
        }

        if (!user.getPassword().equals(r.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid Credentials!");
        }

        // Response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login Success");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    // ---------------------- GET ALL USERS ----------------------
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
