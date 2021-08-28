package com.j2kb.minipetpee.api.member.controller;

import com.j2kb.minipetpee.api.member.controller.dto.LoginRequest;
import com.j2kb.minipetpee.api.member.controller.dto.LoginResponse;
import com.j2kb.minipetpee.api.member.controller.dto.SaveMemberRequest;
import com.j2kb.minipetpee.api.member.controller.dto.ValidateEmailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 API")
@RestController
@RequestMapping("/apis")
public class MemberController {

    @Operation(summary = "회원가입")
    @PostMapping("/members")
    public ResponseEntity<Void> saveMember(@RequestBody SaveMemberRequest member) {
        /**
         * 회원 서비스: 회원가입 메서드 호출
         */
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "이메일 중복 확인")
    @GetMapping("/validate-email")
    public ResponseEntity<ValidateEmailResponse> validateEmail(@RequestParam("email") String email){
        /**
         * 회원 서비스: 중복 확인 메서드 호출
         */
        ValidateEmailResponse emailResponse = new ValidateEmailResponse(email);
        return ResponseEntity.ok(emailResponse);
    }

    @Operation(summary = "로그인")
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest member) {
        /**
         * 회원 서비스: 로그인 메서드 호출
         */
        // 로그인할 멤버 결과 토큰 받아오기
        LoginResponse loginMember = new LoginResponse("1234", "1234");
        return ResponseEntity.ok(loginMember);
    }
}
