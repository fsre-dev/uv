package com.jis.uv.service;

import com.jis.uv.model.MemberAudit;
import com.jis.uv.repository.MemberAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberAuditService {

    @Autowired
    private MemberAuditRepository memberAuditRepository;

    public Page<MemberAudit> findAll(Pageable pageRequest) {
        return memberAuditRepository.findAll(pageRequest);
    }

    public Page<MemberAudit> findAllByMemberId(Long id, Pageable pageRequest) {
        return memberAuditRepository.findAllByMemberId(id, pageRequest);
    }
}
