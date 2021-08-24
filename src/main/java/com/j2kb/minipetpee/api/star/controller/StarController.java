package com.j2kb.minipetpee.api.star.controller;

import com.j2kb.minipetpee.api.star.controller.dto.*;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<StarResponse>> findStars(@PathVariable("member-id") long memberId) {
        StarResponse starMember1 = new StarResponse(11, 1, "뽀로로", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        StarResponse starMember2 = new StarResponse(22, 2, "루피", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        StarResponse starMember3 = new StarResponse(33, 3, "포비", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());

        List<StarResponse> stars = new ArrayList<>();
        stars.add(starMember1);
        stars.add(starMember2);
        stars.add(starMember3);

        return ResponseEntity.ok(stars);
    }


    @GetMapping("/{member-id}/fans")
    public ResponseEntity<List<FanResponse>> findFans(@PathVariable("member-id") long memberId) {
        FanResponse fanMember1 = new FanResponse(44, 5, "크롱", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        FanResponse fanMember2 = new FanResponse(55, 6, "에디", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        FanResponse fanMember3 = new FanResponse(66, 7, "패티", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        FanResponse fanMember4 = new FanResponse(77, 8, "해리", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());

        List<FanResponse> fans = new ArrayList<>();
        fans.add(fanMember1);
        fans.add(fanMember2);
        fans.add(fanMember3);
        fans.add(fanMember4);

        return ResponseEntity.ok(fans);
    }
}
