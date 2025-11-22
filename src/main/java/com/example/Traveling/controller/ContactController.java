package com.example.Traveling.controller;

import com.example.Traveling.model.Contact;
import com.example.Traveling.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // frontend se access ke liye
public class ContactController {

    @Autowired
    private ContactService service;

    // -------------------- SEND MESSAGE --------------------
    @PostMapping("/send")
    public String sendMessage(@RequestBody Contact c) {
        service.save(c);
        return "Message Sent Successfully!";
    }

    // -------------------- GET ALL CONTACTS --------------------
    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return service.getAllContacts();
    }
}
