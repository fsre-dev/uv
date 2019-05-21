package com.jis.uv.controller;

import com.jis.uv.model.Membership;
import com.jis.uv.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/membership")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @PostMapping
    public ResponseEntity<Membership> insert(@RequestBody Membership membership) {
        Membership inserted = membershipService.insert(membership);
        if(inserted == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = membershipService.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<Membership>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Membership> memberships = membershipService.findAll(PageRequest.of(page, size));
        if (memberships.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> findById(@PathVariable Long id) {
        Membership membership = membershipService.findById(id);
        if (membership == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(membership, HttpStatus.OK);
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<Page<Membership>> findAllDeleted(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Membership> memberships = membershipService.findAllDeleted(PageRequest.of(page, size));
        if (memberships == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }
}