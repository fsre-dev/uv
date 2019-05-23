package com.jis.uv.service;

import com.jis.uv.model.Membership;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MembershipValidator {
    Boolean validate(Membership membership) {
        Date date = new Date(System.currentTimeMillis());
        return (membership != null &&
                membership.getMemberFrom() != null &&
                membership.getMemberFrom().compareTo(date) < 0 &&
                membership.getMemberTo() != null &&
                membership.getMemberTo().compareTo(date) > 0 &&
                membership.getPrice() != null);
    }
}