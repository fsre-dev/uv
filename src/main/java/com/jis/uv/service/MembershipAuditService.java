package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.model.MembershipAudit;
import com.jis.uv.model.enums.ActionEnum;
import com.jis.uv.repository.MembershipAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipAuditService {
    private final MembershipAuditRepository membershipAuditRepository;

    @Autowired
    public MembershipAuditService(MembershipAuditRepository membershipAuditRepository) {
        this.membershipAuditRepository = membershipAuditRepository;
    }

    public List<MembershipAudit> findAll(Pageable pageRequest) {
        Page<MembershipAudit> membershipAudits = membershipAuditRepository.findAll(pageRequest);
        return membershipAudits.getContent();
    }

    MembershipAudit createAudit(Membership membership) {
        MembershipAudit membershipAudit = new MembershipAudit(membership.getMemberFrom(),
                membership.getMemberTo(), membership.getPrice(), ActionEnum.ADD, false);

        return membershipAuditRepository.saveAndFlush(membershipAudit);
    }

    MembershipAudit updateAudit(Membership membership) {
        MembershipAudit membershipAudit = new MembershipAudit(membership.getMemberFrom(),
                membership.getMemberTo(), membership.getPrice(), ActionEnum.UPD, false);

        return membershipAuditRepository.saveAndFlush(membershipAudit);
    }

    MembershipAudit deleteAudit(Membership membership) {
        MembershipAudit membershipAudit = new MembershipAudit(membership.getMemberFrom(),
                membership.getMemberTo(), membership.getPrice(), ActionEnum.TERM, true);

        return membershipAuditRepository.saveAndFlush(membershipAudit);
    }
}