package com.j2kb.minipetpee.api.setting;

import com.j2kb.minipetpee.api.setting.dto.ProfileDto;
import com.j2kb.minipetpee.api.setting.dto.ViewProfileDto;
import com.j2kb.minipetpee.api.setting.dto.ViewTabDto;
import com.j2kb.minipetpee.domain.Gender;
import com.j2kb.minipetpee.domain.Type;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apis/{homepee-id}/settings")
public class SettingController {

    @GetMapping
    public ResponseEntity<Map> lookUpSettings(@PathVariable(name = "homepee-id") int hompeeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("profile", new ViewProfileDto("enna@gmail.com", "enna", "2000-03-21", "dog", "순하고 똑똑함", Gender.FEMALE, "ssss", "iiii"));
        map.put("tabs", new ViewTabDto(0,hompeeId, Type.BOARD, true));
        return ResponseEntity.ok(map);
    }

    //프로필 변경
    @PutMapping("/profile")
    public ResponseEntity updateProfile(@PathVariable(name = "homepee-id") int hompeeId, @RequestBody ProfileDto profile) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //탭 공개여부 설정
    @PutMapping("/tabs")
    public ResponseEntity updateTabs(@PathVariable(name = "homepee-id") int hompeeId, @RequestBody String tabs) {
        JSONObject obj = new JSONObject(tabs);
        JSONArray tabList = new JSONArray("tabs");

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
