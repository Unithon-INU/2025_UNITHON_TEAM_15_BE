package com.unithon.team15_server.domain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @PostMapping("/sign-up")
    public void signup() {

    }

    @PostMapping("/sign-in")
    public void signin() {

    }
}
