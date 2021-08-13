package com.j2kb.minipetpee.api.setting;

import com.j2kb.minipetpee.api.setting.dto.*;
import com.j2kb.minipetpee.domain.Gender;
import com.j2kb.minipetpee.domain.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/apis/{homepee-id}/settings")
public class SettingController {

    @GetMapping
    public ResponseEntity<FindSettingsResponse> findSettings(@PathVariable(name = "homepee-id") int homepeeId) {
        FindSettingsProfile profile = new FindSettingsProfile("enna@gmail.com", "enna", "2000-03-21", "dog", "순하고 똑똑함", Gender.FEMALE, "ssss", "iiii");

        List<FindSettingsTab> tabs = new ArrayList<>();
        tabs.add(new FindSettingsTab(1, homepeeId, Type.ALBUM, true));
        tabs.add(new FindSettingsTab(2, homepeeId, Type.BOARD, true));
        tabs.add(new FindSettingsTab(3, homepeeId, Type.GUEST, true));

        FindSettingsResponse settingsResponse = new FindSettingsResponse(profile, tabs);
        return ResponseEntity.ok(settingsResponse);
    }

    //프로필 변경
    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@PathVariable(name = "homepee-id") int homepeeId, @RequestBody UpdateProfileRequest profile) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //탭 공개여부 설정
    @PutMapping("/tabs")
    public ResponseEntity<Void> updateTabs(@PathVariable(name = "homepee-id") int homepeeId, @RequestBody UpdateTabsRequest updateTabs) {

        for(SettingTabs tabDto : updateTabs.getTabs()) {
            log.info("tabId = {}, tabType = {}, tabVisible={}", tabDto.getId(),tabDto.getType(), tabDto.isVisible());
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
