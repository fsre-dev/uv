package com.jis.uv.controller;

import com.jis.uv.model.MembershipAudit;
import com.jis.uv.service.MembershipAuditService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("membership/audit")
public class MembershipAuditController {
    private MembershipAuditService membershipAuditService;

    public MembershipAuditController(MembershipAuditService membershipAuditService) {
        this.membershipAuditService = membershipAuditService;
    }

    @GetMapping
    public ResponseEntity<List<MembershipAudit>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        List<MembershipAudit> audits = membershipAuditService.findAll(PageRequest.of(page, size));
        if (audits.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(audits);
    }
}
