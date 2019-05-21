package com.jis.uv.repository;

import com.jis.uv.model.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Page<Membership> findAllByIsDeletedFalse(Pageable pageRequest);
    Page<Membership> findAllByIsDeletedTrue(Pageable pageRequest);
}
