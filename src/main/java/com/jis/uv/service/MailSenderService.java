package com.jis.uv.service;

import com.jis.uv.model.Member;
import com.jis.uv.model.Membership;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailSenderService {
    private final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    private JavaMailSender javaMailSender;
    private MemberService memberService;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender, MemberService memberService) {
        this.javaMailSender = javaMailSender;
        this.memberService = memberService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void notifyForExpiringMembership() {
        List<Member> members = memberService.findAll();
        for (Member member : members) {
            Membership membership = member.getMembership();
            if (isMembershipExpiring(membership.getMemberTo())) {
                sendMessage(member);
                logger.info("E-mail sent successfully");
            }
        }
    }

    private void sendMessage(Member member) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(member.getEmail());
        message.setSubject("Membership");
        message.setText("Your membership is going to expire in 14 days !");

        javaMailSender.send(message);
    }

    private Boolean isMembershipExpiring(Date membershipExpirationDate) {
        Date today = new java.sql.Date(new Date().getTime());
        int daysBeforeExpiration = Days.daysBetween(
                new LocalDate(membershipExpirationDate.getTime()),
                new LocalDate(today.getTime())).getDays();

        return Math.abs(daysBeforeExpiration) <= 14;
    }
}