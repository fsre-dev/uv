package com.jis.uv.repository;

import com.jis.uv.model.Membership;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends PagingAndSortingRepository<Membership, Long> {
}
