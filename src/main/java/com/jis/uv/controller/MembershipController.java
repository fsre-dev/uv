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
    MembershipService membershipService;

    @PostMapping
    public Membership insert(@RequestBody Membership membership) {
        return membershipService.insert(membership);
    }

    @DeleteMapping
    public void delete(@RequestBody Membership membership) {
        membershipService.delete(membership);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Membership>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Membership> memberships = membershipService.findAll(PageRequest.of(page, size));
        if (memberships.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> findById(@PathVariable Long id) {
        Membership membership = membershipService.findById(id);
        if (membership == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/deleted", params = {"page", "size"})
    public ResponseEntity<Page<Membership>> findAllDeleted(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Membership> memberships = membershipService.findAllDeleted(PageRequest.of(page, size));
        if (memberships == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}