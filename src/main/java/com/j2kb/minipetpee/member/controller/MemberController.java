package com.j2kb.minipetpee.member.controller;

import com.j2kb.minipetpee.member.controller.dto.LoginMemberRequest;
import com.j2kb.minipetpee.member.controller.dto.LoginMemberResponse;
import com.j2kb.minipetpee.member.controller.dto.SaveMemberRequest;
import com.j2kb.minipetpee.member.controller.dto.ValidateEmailResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MemberController {

    // 회원가입
    @PostMapping("/apis/members")
    public ResponseEntity<Void> saveMember(@RequestBody SaveMemberRequest member) {
        /**
         * 회원 서비스: 회원가입 메서드 호출
         */
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 이메일 중복 확인
    @GetMapping("/apis/validate-email")
    public ResponseEntity<ValidateEmailResponse> validateEmail(@RequestParam("email") String email){
        ValidateEmailResponse emailResponse = new ValidateEmailResponse(email);
        return ResponseEntity.ok(emailResponse);
    }

    // 로그인
    @GetMapping("/apis/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest member) {
        /**
         * 회원 서비스: 로그인 메서드 호출
         */
        // 로그인할 멤버 결과 토큰 받아오기
        LoginMemberResponse loginMember = new LoginMemberResponse("1234", "1234");
        return ResponseEntity.ok(loginMember);
    }

    // 로그아웃
    @GetMapping("/apis/logout")
    public ResponseEntity<Void> logout(){
        /**
         * 회원 서비스: 로그아웃 메서드 호출
         */
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
