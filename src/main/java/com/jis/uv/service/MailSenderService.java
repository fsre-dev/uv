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
        int daysBeforeExpiration;
        String messageText;
        List<Member> members = memberService.findAll();
        for (Member member : members) {
            Membership membership = member.getMembership();
            daysBeforeExpiration = calculateDaysBeforeExpiration(membership.getMemberTo());
            if (daysBeforeExpiration == 14) {
                messageText = "Your membership is expiring in 14 days";
                sendMessage(member, messageText);
                logger.info("E-mail sent successfully");
            } else if (daysBeforeExpiration == -1) {
                messageText = "Your membership has expired";
                sendMessage(member, messageText);
                logger.info("E-mail sent successfully");
            }
        }
    }

    private void sendMessage(Member member, String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(member.getEmail());
        message.setSubject("Membership");
        message.setText(messageText);

        javaMailSender.send(message);
    }

    private Integer calculateDaysBeforeExpiration(Date membershipExpirationDate) {
        Date today = new java.sql.Date(new Date().getTime());

        return Days.daysBetween(
                new LocalDate(today.getTime()),
                new LocalDate(membershipExpirationDate.getTime())).getDays();
    }
}