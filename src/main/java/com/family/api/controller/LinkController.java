package com.family.api.controller;

import com.family.api.model.Link;
import com.family.api.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Link> createLink(@RequestBody Link linkRequest) {
        Link savedLink = linkRepository.save(linkRequest);
        return ResponseEntity.ok(savedLink);
    }
}
