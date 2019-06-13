package com.jis.uv.repository;

import com.jis.uv.model.MembershipAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipAuditRepository extends JpaRepository<MembershipAudit, Long> {
    Page<MembershipAudit> findAll(Pageable pageRequest);

}