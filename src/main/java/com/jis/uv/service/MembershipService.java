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
    private MembershipRepository membershipRepository;

    public Membership insert(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Boolean delete(Long id) {
        Membership membership = membershipRepository.getOne(id);
        if(membership == null) {
            return false;
        }
        membership.setIsDeleted(true);
        membershipRepository.save(membership);
        return true;
    }

    public Page<Membership> findAll(Pageable pageRequest) {
        return membershipRepository.findAllByIsDeletedFalse(pageRequest);
    }

    public Membership findById(Long id) {
        return membershipRepository.findById(id).orElse(null);
    }

    public Page<Membership> findAllDeleted(Pageable pageRequest) {
        return membershipRepository.findAllByIsDeletedTrue(pageRequest);
    }
}
