package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Membership> existingMembership = this.findById(id);
        if (membershipValidator.validate(membership) && existingMembership.isPresent()) {
            membership.setId(id);
            return membershipRepository.save(membership);
        }
        throw new Exception("Could not update membership");
    }

    public void delete(Long id) throws Exception {
        Optional<Membership> membership = this.findById(id);
        if (membership.isPresent()) {
            Membership deleteMembership = membership.get();
            deleteMembership.setIsDeleted(true);
            membershipRepository.save(deleteMembership);
        }
        throw new Exception("Membership does not exist");
    }

    public List<Membership> findAll(Pageable pageRequest) {
        return membershipRepository.findAllByIsDeletedFalse(pageRequest).getContent();
    }

    public Optional<Membership> findById(Long id) {
        return membershipRepository.findById(id);
    }

    public List<Membership> findAllDeleted(Pageable pageRequest) {
        return membershipRepository.findAllByIsDeletedTrue(pageRequest).getContent();
    }
}
