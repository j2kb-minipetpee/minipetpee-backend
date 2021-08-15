package com.j2kb.minipetpee.star.controller;

import com.j2kb.minipetpee.star.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apis")
public class StarController {

    // 스타
    @PostMapping("/stars/{star-id}")
    public ResponseEntity<Void> star(@PathVariable("star-id") long starId){
        /**
         * 스타 서비스: 스타 메서드 호출
         */
        return ResponseEntity.noContent().build();
    }

    // 언스타
    @DeleteMapping("/stars/{star-id}")
    public ResponseEntity<Void> unstar(@PathVariable("star-id") long starId){
        /**
         * 스타 서비스: 언스타 메서드 호출
         */
        return ResponseEntity.noContent().build();
    }


    // 스타 목록
    @GetMapping("/{member-id}/stars")
    public ResponseEntity<FindStarsResponse> findStars(@PathVariable("member-id") long memberId) {
        FindStarResponse starMember1 = new FindStarResponse(11, 1, "뽀로로", "profileImgUrl1", LocalDateTime.now());
        FindStarResponse starMember2 = new FindStarResponse(22, 2, "루피", "profileImgUrl2", LocalDateTime.now());
        FindStarResponse starMember3 = new FindStarResponse(33, 3, "포비", "profileImgUrl3", LocalDateTime.now());

        List<FindStarResponse> starList = new ArrayList<>();
        starList.add(starMember1);
        starList.add(starMember2);
        starList.add(starMember3);

        FindStarsResponse starsResponse = new FindStarsResponse(starList);
        return ResponseEntity.ok(starsResponse);
    }


    @GetMapping("/{member-id}/fans")
    public ResponseEntity<FindFansResponse> findFans(@PathVariable("member-id") long memberId) {
        FindFanResponse fanMember1 = new FindFanResponse(44, 5, "크롱", "crongImgUrl", LocalDateTime.now());
        FindFanResponse fanMember2 = new FindFanResponse(55, 6, "에디", "ethyImgUrl", LocalDateTime.now());
        FindFanResponse fanMember3 = new FindFanResponse(66, 7, "패티", "paetyImgUrl", LocalDateTime.now());
        FindFanResponse fanMember4 = new FindFanResponse(77, 8, "해리", "haeryImgUrl", LocalDateTime.now());

        List<FindFanResponse> fanList = new ArrayList<>();
        fanList.add(fanMember1);
        fanList.add(fanMember2);
        fanList.add(fanMember3);
        fanList.add(fanMember4);

        FindFansResponse fansResponse = new FindFansResponse(fanList);
        return ResponseEntity.ok(fansResponse);
    }
}
