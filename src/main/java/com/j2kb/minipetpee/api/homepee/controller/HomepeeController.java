package com.j2kb.minipetpee.api.homepee.controller;

import com.j2kb.minipetpee.api.homepee.controller.dto.HomepeeResponse;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.fancomment.service.FanCommentService;
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

import java.util.List;
import java.util.Objects;

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

        // 로그인 여부 체크
        Long currentMemberId = (Objects.isNull(principal))? null: principal.getId();
        // 스타(팔로잉) 여부 확인
        Relationship relationship = starService.checkStarRelationship(currentMemberId, homepee.memberId());
        return ResponseEntity.ok(new HomepeeResponse(homepee, fanComments, relationship));
    }
}
