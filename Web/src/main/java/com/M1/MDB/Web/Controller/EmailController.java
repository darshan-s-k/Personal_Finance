package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")

public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/processLatest")
    public void processLatestEmail() {
        emailService.processMostRecentEmail();
    }
}
