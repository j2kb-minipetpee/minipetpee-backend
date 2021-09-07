package com.j2kb.minipetpee.api.setting.controller;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.service.MemberService;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateProfileRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateSettingRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateTabsRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.response.SettingResponse;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import com.j2kb.minipetpee.api.setting.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "관리 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis/{homepee-id}/settings")
public class SettingController {

    private final SettingService settingService;
    private final HomepeeService homepeeService;
    private final MemberService memberService;

    // 프로필 및 탭 목록 조회
    @Operation(summary = "관리 탭 조회")
    @GetMapping
    @PreAuthorize("isAuthenticated() && hasAuthority('UPDATE_HOMEPEE') && #principal.homepeeId.equals(#homepeeId)")
    public ResponseEntity<SettingResponse> findSettings(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId
    ) {
        Member member = memberService.findById(principal.getId());
        List<Tab> tabs = settingService.findTabsByHomepeeId(homepeeId);
        Homepee homepee = homepeeService.findById(homepeeId);

        SettingResponse settingResponse = new SettingResponse(homepee, member, tabs);
        return ResponseEntity.ok(settingResponse);
    }

    @Operation(summary = "프로필 및 홈피 설정 변경")
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated() && hasAuthority('UPDATE_HOMEPEE') && #principal.homepeeId.equals(#homepeeId)")
    public ResponseEntity<Void> updateProfile(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateSettingRequest request
    ) {
        settingService.updateSettings(homepeeId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "탭 공개여부 설정")
    @PutMapping("/tabs")
    @PreAuthorize("isAuthenticated() && hasAuthority('UPDATE_HOMEPEE') && #principal.homepeeId.equals(#homepeeId)")
    public ResponseEntity<Void> updateTabs(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateTabsRequest request
    ) {
        settingService.updateTabs(homepeeId, request);
        return ResponseEntity.noContent().build();
    }
}
