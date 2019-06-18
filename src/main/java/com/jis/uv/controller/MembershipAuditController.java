package com.jis.uv.controller;

import com.jis.uv.model.MembershipAudit;
import com.jis.uv.service.MembershipAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(MembershipAuditController.class);

    private MembershipAuditService membershipAuditService;

    public MembershipAuditController(MembershipAuditService membershipAuditService) {
        this.membershipAuditService = membershipAuditService;
    }

    @GetMapping
    public ResponseEntity<List<MembershipAudit>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        List<MembershipAudit> audits = membershipAuditService.findAll(PageRequest.of(page, size));
        if (!audits.isEmpty()) {
            logger.info("Successfully found all audits");
            return ResponseEntity.ok(audits);
        }
        logger.error("Audits not found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/add")
    public ResponseEntity<List<MembershipAudit>> findAllCreated(@RequestParam Integer page, @RequestParam Integer size) {
        List<MembershipAudit> audits = membershipAuditService.findAllCreated(PageRequest.of(page, size));
        if (!audits.isEmpty()) {
            logger.info("Successfully found all create audits");
            return ResponseEntity.ok(audits);
        }
        logger.error("No create audits found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/upd")
    public ResponseEntity<List<MembershipAudit>> findAllUpdated(@RequestParam Integer page, @RequestParam Integer size) {
        List<MembershipAudit> audits = membershipAuditService.findAllUpdated(PageRequest.of(page, size));
        if (!audits.isEmpty()) {
            logger.info("Successfully found all update audits");
            return ResponseEntity.ok(audits);
        }
        logger.error("No update audits found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/term")
    public ResponseEntity<List<MembershipAudit>> findAllTerminated(@RequestParam Integer page, @RequestParam Integer size) {
        List<MembershipAudit> audits = membershipAuditService.findAllTerminated(PageRequest.of(page, size));
        if (!audits.isEmpty()) {
            logger.info("Successfully found all terminate audits");
            return ResponseEntity.ok(audits);
        }
        logger.error("No terminate audits found");
        return ResponseEntity.notFound().build();
    }
}
