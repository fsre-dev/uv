package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
    private MembershipRepository membershipRepository;
    private MembershipValidator membershipValidator;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository, MembershipValidator membershipValidator) {
        this.membershipRepository = membershipRepository;
        this.membershipValidator = membershipValidator;
    }

    public Membership create(Membership membership) throws Exception {
        if (membershipValidator.validate(membership)) {
            return membershipRepository.save(membership);
        }
        throw new Exception("Invalid membership");
    }

    public Membership update(Membership membership, Long id) throws Exception {
        if (membershipValidator.validate(membership) && membershipValidator.isExisting(id)) {
            membership.setId(id);
            return membershipRepository.save(membership);
        }
        throw new Exception("Could not update membership !");
    }

    public void delete(Long id) throws Exception {
        if (membershipValidator.isExisting(id)) {
            Membership membership = this.findById(id);
            membership.setIsDeleted(true);
            membershipRepository.save(membership);
        }
        throw new Exception("Membership does not exist");
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
