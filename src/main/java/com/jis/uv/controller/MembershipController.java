package com.jis.uv.controller;

import com.jis.uv.model.Membership;
import com.jis.uv.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/membership")
public class MembershipController {
    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public ResponseEntity<Membership> create(@RequestBody Membership membership) {
        try {
            Membership insertedMembership = membershipService.create(membership);
            return ResponseEntity.ok(insertedMembership);
        } catch (Exception e) {
            e.getMessage();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membership> update(@RequestBody Membership membership, @PathVariable Long id) {
        try {
            Membership updatedMembership = membershipService.update(membership, id);
            return ResponseEntity.ok(updatedMembership);
        } catch (Exception e) {
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            membershipService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Membership>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        List<Membership> memberships = membershipService.findAll(PageRequest.of(page, size));
        if (memberships.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memberships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> findById(@PathVariable Long id) {
        Optional<Membership> existingMembership = membershipService.findById(id);
        if (existingMembership.isPresent()) {
            return ResponseEntity.ok(existingMembership.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<Membership>> findAllDeleted(@RequestParam Integer page, @RequestParam Integer size) {
        List<Membership> memberships = membershipService.findAllDeleted(PageRequest.of(page, size));
        if (memberships.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memberships);
    }
}