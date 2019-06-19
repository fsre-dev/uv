package com.jis.uv.controller;

import com.jis.uv.model.Membership;
import com.jis.uv.service.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final Logger logger = LoggerFactory.getLogger(MembershipController.class);

    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Membership> create(@RequestBody Membership membership) {
        try {
            Membership insertedMembership = membershipService.create(membership);
            logger.info("Membership successfully created");
            return ResponseEntity.ok(insertedMembership);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Unable to create membership {}", membership.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Membership> update(@RequestBody Membership membership, @PathVariable Long id) {
        try {
            Membership updatedMembership = membershipService.update(membership, id);
            logger.info("Membership successfully updated");
            return ResponseEntity.ok(updatedMembership);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Unable to update membership with id {}", id.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            membershipService.delete(id);
            logger.info("Membership successfully deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.getMessage();
            logger.error("Unable to delete membership with id {}", id.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Membership>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        List<Membership> memberships = membershipService.findAll(PageRequest.of(page, size));
        if (!memberships.isEmpty()) {
            logger.info("Successfully found all memberships");
            return ResponseEntity.ok(memberships);
        }
        logger.error("Unable to find memberships");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> findById(@PathVariable Long id) {
        Optional<Membership> existingMembership = membershipService.findById(id);
        if (existingMembership.isPresent()) {
            logger.info("Found membership with id {}", id.toString());
            return ResponseEntity.ok(existingMembership.get());
        }
        logger.error("Unable to find membership with id {}", id.toString());
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<Membership>> findAllDeleted(@RequestParam Integer page, @RequestParam Integer size) {
        List<Membership> memberships = membershipService.findAllDeleted(PageRequest.of(page, size));
        if (memberships.isEmpty()) {
            logger.info("Successfully found all deleted memberships");
            return ResponseEntity.notFound().build();
        }
        logger.error("Unable to find deleted memberships");
        return ResponseEntity.ok(memberships);
    }
}