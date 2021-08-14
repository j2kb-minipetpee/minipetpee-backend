package com.j2kb.minipetpee.star.controller;

import com.j2kb.minipetpee.star.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StarController {

    // 스타
    @PostMapping("/apis/stars/{star-id}")
    public ResponseEntity<Void> star(@PathVariable("star-id") long starId, @RequestBody String body){
        /**
         * 스타 서비스: 스타 메서드 호출
         */
        // StarRequest: empty Dto - path variable로 값을 받아서 담을 데이터 없음
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 언스타
    public ResponseEntity<Void> unstar(@PathVariable("star-id") long starId){
        /**
         * 스타 서비스: 언스타 메서드 호출
         */
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    // 스타 목록
    @GetMapping("/apis/{member-id}/stars")
    public ResponseEntity<FindAllStarsResponse> findAllStars(@PathVariable("member-id") long memberId) {
        FindStarResponse starMember1 = new FindStarResponse(11, 1, "뽀로로", "profileImgUrl1", LocalDateTime.now());
        FindStarResponse starMember2 = new FindStarResponse(22, 2, "루피", "profileImgUrl2", LocalDateTime.now());
        FindStarResponse starMember3 = new FindStarResponse(33, 3, "포비", "profileImgUrl3", LocalDateTime.now());

        List<FindStarResponse> starList = new ArrayList<>();
        starList.add(starMember1);
        starList.add(starMember2);
        starList.add(starMember3);

        FindAllStarsResponse starsResponse = new FindAllStarsResponse(starList);
        return ResponseEntity.ok(starsResponse);
    }


    @GetMapping("/apis/{member-id}/fans")
    public ResponseEntity<FindAllFansResponse> findAllFans(@PathVariable("member-id") long memberId) {
        FindFanResponse fanMember1 = new FindFanResponse(44, 5, "크롱", "crongImgUrl", LocalDateTime.now());
        FindFanResponse fanMember2 = new FindFanResponse(55, 6, "에디", "ethyImgUrl", LocalDateTime.now());
        FindFanResponse fanMember3 = new FindFanResponse(66, 7, "패티", "paetyImgUrl", LocalDateTime.now());
        FindFanResponse fanMember4 = new FindFanResponse(77, 8, "해리", "haeryImgUrl", LocalDateTime.now());

        List<FindFanResponse> fanList = new ArrayList<>();
        fanList.add(fanMember1);
        fanList.add(fanMember2);
        fanList.add(fanMember3);
        fanList.add(fanMember4);

        FindAllFansResponse fansResponse = new FindAllFansResponse(fanList);
        return ResponseEntity.ok(fansResponse);
    }
}
