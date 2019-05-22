package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MembershipValidator {
    private MembershipRepository membershipRepository;

    @Autowired
    public MembershipValidator(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    Boolean validate(Membership membership) {
        Date date = new Date(System.currentTimeMillis());
        return (membership != null && membership.getMemberFrom() != null && membership.getMemberFrom().compareTo(date) < 0 &&
                membership.getMemberTo() != null && membership.getMemberTo().compareTo(date) > 0 &&
                membership.getPrice() != null);
    }

    Boolean isExisting(Long id) {
        Membership membership = membershipRepository.findById(id).orElse(null);
        return membership != null;
    }
}
