package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
    MembershipRepository membershipRepository;

    public Membership insert(Membership membership) {
        return membershipRepository.save(membership);
    }

    public void delete(Membership membership) {
        membership.setIsDeleted(true);
        membershipRepository.save(membership);
    }
}
