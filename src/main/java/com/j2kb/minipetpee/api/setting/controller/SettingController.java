package com.j2kb.minipetpee.api.setting.controller;

import com.j2kb.minipetpee.api.setting.controller.dto.request.SettingTabsRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateProfileRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.request.UpdateTabsRequest;
import com.j2kb.minipetpee.api.setting.controller.dto.response.SettingProfileResponse;
import com.j2kb.minipetpee.api.setting.controller.dto.response.SettingResponse;
import com.j2kb.minipetpee.api.setting.controller.dto.response.SettingTabResponse;
import com.j2kb.minipetpee.api.member.domain.Gender;
import com.j2kb.minipetpee.api.setting.domain.Type;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "관리 API")
@Slf4j
@RestController
@RequestMapping("/apis/{homepee-id}/settings")
public class SettingController {

    @Operation(summary = "관리 탭 조회")
    @GetMapping
    public ResponseEntity<SettingResponse> findSettings(
            @PathVariable(name = "homepee-id") Long homepeeId
    ) {
        SettingProfileResponse profile = new SettingProfileResponse("enna@gmail.com", "enna", "2000-03-21", "dog", "순하고 똑똑함", Gender.FEMALE, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");

        List<SettingTabResponse> tabs = new ArrayList<>();
        tabs.add(new SettingTabResponse(1L, homepeeId, Type.ALBUM, true));
        tabs.add(new SettingTabResponse(2L, homepeeId, Type.BOARD, true));
        tabs.add(new SettingTabResponse(3L, homepeeId, Type.GUEST, true));

        SettingResponse settingResponse = new SettingResponse(profile, tabs);
        return ResponseEntity.ok(settingResponse);
    }

    @Operation(summary = "프로필 변경")
    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @RequestBody UpdateProfileRequest profile
    ) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "탭 공개여부 설정")
    @PutMapping("/tabs")
    public ResponseEntity<Void> updateTabs(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @RequestBody UpdateTabsRequest updateTabs
    ) {
        for(SettingTabsRequest tab : updateTabs.getTabs()) {
            log.info("tabId = {}, tabType = {}, tabVisible={}", tab.getId(),tab.getType(), tab.isVisible());
        }
        return ResponseEntity.noContent().build();
    }
}
