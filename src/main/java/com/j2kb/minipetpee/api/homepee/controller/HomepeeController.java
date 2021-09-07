package com.j2kb.minipetpee.api.homepee.controller;

import com.j2kb.minipetpee.api.homepee.controller.dto.FanCommentResponse;
import com.j2kb.minipetpee.api.homepee.controller.dto.HomepeeResponse;
import com.j2kb.minipetpee.api.homepee.controller.dto.SaveFanCommentRequest;
import com.j2kb.minipetpee.api.homepee.controller.dto.UpdateFanCommentRequest;
import com.j2kb.minipetpee.api.homepee.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.service.FanCommentService;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import com.j2kb.minipetpee.api.star.controller.dto.response.StarRelationshipResponse;
import com.j2kb.minipetpee.api.star.domain.Relationship;
import com.j2kb.minipetpee.api.star.service.StarService;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "홈피 API")
@RestController
@RequestMapping("apis/{homepee-id}")
@RequiredArgsConstructor
public class HomepeeController {

    private final HomepeeService homepeeService;
    private final FanCommentService fanCommentService;
    private final StarService starService;

    @Operation(summary = "홈피 조회")
    @GetMapping
    public ResponseEntity<HomepeeResponse> find(
            @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId
    ) {
        Homepee homepee = homepeeService.findById(homepeeId);
        List<FanComment> fanComments = fanCommentService.findAllByHomepeeId(homepeeId);

        // 스타(팔로우) 여부 확인
        Relationship relational = starService.checkStarRelationship(principal.getId(), homepee.memberId());
        return ResponseEntity.ok(new HomepeeResponse(homepee, fanComments, relational));
    }

    @Operation(summary = "공생평 작성")
    @PostMapping("/fan-comments")
    public ResponseEntity<FanCommentResponse> saveFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @RequestBody SaveFanCommentRequest fanCommentRequest
    ) {
        // 추가된 공생평 데이터의 id
        Long id = 10L;
        Long memberId = fanCommentRequest.getMemberId();
        // 멤버 아이디에 해당하는 memberName 조회
        String memberName = "춘식이";
        String content = fanCommentRequest.getContent();
        LocalDateTime createdAt = LocalDateTime.now();
        FanCommentResponse fanCommentResponse = new FanCommentResponse(id, memberId, memberName, content, createdAt);
        return ResponseEntity.ok(fanCommentResponse);
    }

    @Operation(summary = "공생평 수정")
    @PutMapping("/fan-comments")
    public ResponseEntity<Void> updateFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @RequestBody UpdateFanCommentRequest fanCommentRequest
    ){
        // 홈피 서비스: 공생평 수정 메서드 호출
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공생평 삭제")
    @DeleteMapping("/fan-comments/{fan-comment-id}")
    public ResponseEntity<Void> deleteFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @PathVariable("fan-comment-id") Long fanCommentId
    ) {
        // 홈피 서비스: 공생평 삭제 메서드 호출
        return ResponseEntity.noContent().build();
    }
}
