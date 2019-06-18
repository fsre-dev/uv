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

    public List<MembershipAudit> findAll(Pageable pageRequest) {
        Page<MembershipAudit> membershipAudits = membershipAuditRepository.findAll(pageRequest);
        return membershipAudits.getContent();
    }

    public List<MembershipAudit> findAllCreated(Pageable pageRequest) {
        Page<MembershipAudit> membershipAudits = membershipAuditRepository.findAllByAction(ActionEnum.ADD, pageRequest);
        return membershipAudits.getContent();
    }

    public List<MembershipAudit> findAllUpdated(Pageable pageRequest) {
        Page<MembershipAudit> membershipAudits = membershipAuditRepository.findAllByAction(ActionEnum.UPD, pageRequest);
        return membershipAudits.getContent();
    }

    public List<MembershipAudit> findAllTerminated(Pageable pageRequest) {
        Page<MembershipAudit> membershipAudits = membershipAuditRepository.findAllByAction(ActionEnum.TERM, pageRequest);
        return membershipAudits.getContent();
    }
}