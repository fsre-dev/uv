package com.jis.uv.controller;

import com.jis.uv.model.MemberAudit;
import com.jis.uv.service.MemberAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/audit")
public class MemberAuditController {

    private final Logger logger = LoggerFactory.getLogger(MemberAuditController.class);

    @Autowired
    private MemberAuditService memberAuditService;

    @GetMapping
    public ResponseEntity<Page<MemberAudit>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        Page<MemberAudit> memberAudits = memberAuditService.findAll(PageRequest.of(page, size));
        if (memberAudits.isEmpty()) {
            logger.info("Audits not found");
            return ResponseEntity.noContent().build();
        }
        logger.info("Audits found");
        return ResponseEntity.ok(memberAudits);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<MemberAudit>> findAllByMemberId(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer size) {
        Page<MemberAudit> memberAudits = memberAuditService.findAllByMemberId(id, PageRequest.of(page, size));
        if (memberAudits.isEmpty()) {
            logger.info("Audits empty for user with id: {}", id);
            return ResponseEntity.noContent().build();
        }
        logger.info("Audits find for user with id: {}", id);
        return ResponseEntity.ok(memberAudits);

    }
}
