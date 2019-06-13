package com.jis.uv.service;

import com.jis.uv.model.Membership;
import com.jis.uv.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {
    private MembershipRepository membershipRepository;
    private MembershipValidator membershipValidator;
    private MembershipAuditService membershipAuditService;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository, MembershipValidator membershipValidator,
                             MembershipAuditService membershipAuditService) {
        this.membershipRepository = membershipRepository;
        this.membershipValidator = membershipValidator;
        this.membershipAuditService = membershipAuditService;
    }

    public Membership create(Membership membership) throws Exception {
        if (membershipValidator.validate(membership)) {
            membershipAuditService.createAudit(membership);
            return membershipRepository.save(membership);
        }
        throw new Exception("Invalid membership");
    }

    public Membership update(Membership membership, Long id) throws Exception {
        Optional<Membership> existingMembership = this.findById(id);
        if (membershipValidator.validate(membership) && existingMembership.isPresent()) {
            membership.setId(id);
            membershipAuditService.updateAudit(membership);
            return membershipRepository.save(membership);
        }
        throw new Exception("Could not update membership");
    }

    public void delete(Long id) throws Exception {
        Optional<Membership> membership = this.findById(id);
        if (membership.isPresent()) {
            Membership deletedMembership = membership.get();
            deletedMembership.setIsDeleted(true);
            membershipAuditService.deleteAudit(deletedMembership);
            membershipRepository.save(deletedMembership);
        } else {
            throw new Exception("Membership does not exist");
        }
    }

    public List<Membership> findAll(Pageable pageRequest) {
        Page<Membership> pageableMemberships = membershipRepository.findAllByIsDeletedFalse(pageRequest);
        return pageableMemberships.getContent();
    }

    public Optional<Membership> findById(Long id) {
        return membershipRepository.findById(id);
    }

    public List<Membership> findAllDeleted(Pageable pageRequest) {
        Page<Membership> pageableMemberships = membershipRepository.findAllByIsDeletedTrue(pageRequest);
        return pageableMemberships.getContent();
    }
}