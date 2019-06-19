package com.jis.uv.repository;

import com.jis.uv.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
    Page<Ticket> findAllByIsDeletedFalse(Pageable pageRequest);
    Page<Ticket> findAllByIsDeletedTrue(Pageable pageRequest);
}
