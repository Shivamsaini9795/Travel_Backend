package com.example.Traveling.service;

import com.example.Traveling.model.Contact;
import com.example.Traveling.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepo;

    @Autowired
    private EmailService emailService; // Email service added

    @Autowired
    public ContactService(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }

    // -------------------- Save a contact & Send Email --------------------
    public Contact save(Contact c) {

        // 1️⃣ Save contact to database
        Contact savedContact = contactRepo.save(c);

        // 2️⃣ Email trigger
        String subject = "New Contact Message Received";
        String text = "You have received a new message:\n\n"
                + "Name: " + c.getName() + "\n"
                + "Email: " + c.getEmail() + "\n"
                + "Message: " + c.getMessage() + "\n";

        // Your email (receiver)
        emailService.sendEmail("shivamsaini6013@gmail.com", subject, text);

        return savedContact;
    }

    // -------------------- Get all contacts --------------------
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }
}
