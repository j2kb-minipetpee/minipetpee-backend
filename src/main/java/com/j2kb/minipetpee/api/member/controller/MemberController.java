package com.j2kb.minipetpee.api.member.controller;

import com.j2kb.minipetpee.api.member.controller.dto.LoginRequest;
import com.j2kb.minipetpee.api.member.controller.dto.LoginResponse;
import com.j2kb.minipetpee.api.member.controller.dto.SignUpRequest;
import com.j2kb.minipetpee.api.member.controller.dto.ValidateEmailResponse;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.service.MemberService;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import com.j2kb.minipetpee.security.jwt.JwtResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "회원 API")
@RestController
@RequestMapping("/apis")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtResolver jwtResolver;

    @Operation(summary = "이메일 중복 확인")
    @GetMapping("/validate-email")
    public ResponseEntity<ValidateEmailResponse> validateEmail(
            @RequestParam("email") String email
    ){
        memberService.isExistedEmail(email);

        return ResponseEntity.ok(new ValidateEmailResponse(email));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/members")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpRequest request
    ) {
        memberService.saveMember(request);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
    ) {
        Member member = memberService.findByEmail(request);
        JwtAuthenticationPrincipal principal = new JwtAuthenticationPrincipal(member);
        String accessToken = jwtResolver.issueAccessToken(principal);
        String refreshToken = jwtResolver.issueRefreshToken(principal);

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }
}
