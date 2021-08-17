package com.j2kb.minipetpee.member.controller;

import com.j2kb.minipetpee.member.controller.dto.LoginMemberRequest;
import com.j2kb.minipetpee.member.controller.dto.LoginMemberResponse;
import com.j2kb.minipetpee.member.controller.dto.SaveMemberRequest;
import com.j2kb.minipetpee.member.controller.dto.ValidateEmailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis")
public class MemberController {

    // 회원가입
    @PostMapping("/members")
    public ResponseEntity<Void> saveMember(@RequestBody SaveMemberRequest member) {
        /**
         * 회원 서비스: 회원가입 메서드 호출
         */
        return ResponseEntity.noContent().build();
    }

    // 이메일 중복 확인
    @GetMapping("/validate-email")
    public ResponseEntity<ValidateEmailResponse> validateEmail(@RequestParam("email") String email){
        /**
         * 회원 서비스: 중복 확인 메서드 호출
         */
        ValidateEmailResponse emailResponse = new ValidateEmailResponse(email);
        return ResponseEntity.ok(emailResponse);
    }

    // 로그인
    @GetMapping("/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest member) {
        /**
         * 회원 서비스: 로그인 메서드 호출
         */
        // 로그인할 멤버 결과 토큰 받아오기
        LoginMemberResponse loginMember = new LoginMemberResponse("1234", "1234");
        return ResponseEntity.ok(loginMember);
    }
}
