package com.swagger.tutorial;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api")
public class AddressBookResources {
    ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();


    @GetMapping("/{id}")
    public Contact getContact(@PathVariable String id) {
        return contacts.get(id);
    }

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return new ArrayList<Contact>(contacts.values());
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact){
        contacts.put(contact.getId(),contact);
        return  contact;
    }
}
