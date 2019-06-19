package com.jis.uv.repository;

import com.jis.uv.model.MemberAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuditRepository extends JpaRepository<MemberAudit,Long> {

    Page<MemberAudit> findAllByMemberId(Long id, Pageable pageable);
}
