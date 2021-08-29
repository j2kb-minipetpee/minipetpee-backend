package com.j2kb.minipetpee.api.setting.controller;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateProfileRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateSettingRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateTabsRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.response.SettingResponse;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import com.j2kb.minipetpee.api.setting.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // 프로필 및 탭 목록 조회
    @Operation(summary = "관리 탭 조회")
    @GetMapping
    public ResponseEntity<SettingResponse> findSettings(
            @PathVariable(name = "homepee-id") Long homepeeId
    ) {
        Member member = settingService.findMemberByHomepeeId(homepeeId); // 멤버 서비스 구현 되면 바꾸기
        List<Tab> tabs = settingService.findTabsByHomepeeId(homepeeId);
        Homepee homepee = homepeeService.findById(homepeeId);

        SettingResponse settingResponse = new SettingResponse(homepee, member, tabs);
        return ResponseEntity.ok(settingResponse);
    }

    @Operation(summary = "설정 변경")
    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateSettingRequest setting
    ) {
        settingService.updateSettings(homepeeId, setting);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "탭 공개여부 설정")
    @PutMapping("/tabs")
    public ResponseEntity<Void> updateTabs(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateTabsRequest updateTabs
    ) {
        settingService.updateTabs(homepeeId, updateTabs);
        return ResponseEntity.noContent().build();
    }
}
