package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
    @Autowired
    MembershipRepository membershipRepository;

    public Membership insert(Membership membership) {
        return membershipRepository.save(membership);
    }

    public void delete(Membership membership) {
        membership.setIsDeleted(true);
        membershipRepository.save(membership);
    }

    public Page<Membership> findAll(Pageable pageRequest) {
        return membershipRepository.findAll(pageRequest);
    }

    public Membership findById(Long id) {
        return membershipRepository.getOne(id);
    }

    public Page<Membership> findAllDeleted(Pageable pageRequest) {
        return membershipRepository.findAllByIsDeleted(true, pageRequest);
    }
}
