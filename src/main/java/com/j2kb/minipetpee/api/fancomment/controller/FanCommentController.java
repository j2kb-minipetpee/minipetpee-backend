package com.j2kb.minipetpee.api.fancomment.controller;

import com.j2kb.minipetpee.api.fancomment.controller.dto.FanCommentPaginationResponse;
import com.j2kb.minipetpee.api.fancomment.controller.dto.FanCommentResponse;
import com.j2kb.minipetpee.api.fancomment.controller.dto.SaveFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.controller.dto.UpdateFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.fancomment.service.FanCommentService;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공생평 API")
@RestController
@RequestMapping("/apis/{homepee-id}/fan-comments")
@RequiredArgsConstructor
public class FanCommentController {

    private final FanCommentService fanCommentService;

    @Operation(summary = "공생평 작성")
    @PostMapping
    public ResponseEntity<FanCommentResponse> saveFanComment(
        @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
        @PathVariable("homepee-id") Long homepeeId,
        @RequestBody SaveFanCommentRequest request
    ) {
        FanComment fanComment = fanCommentService.saveFanComment(homepeeId, principal.getId(), request);
        return ResponseEntity.ok(new FanCommentResponse(fanComment));
    }

    @Operation(summary = "공생평 조회")
    @GetMapping
    public ResponseEntity<FanCommentPaginationResponse> findAllFanComments(
        @PathVariable("homepee-id") Long homepeeId
    ) {
        List<FanComment> fanComments = fanCommentService.findAllByHomepeeId(homepeeId);
        return ResponseEntity.ok(new FanCommentPaginationResponse(fanComments));
    }

    @Operation(summary = "공생평 수정")
    @PutMapping
    public ResponseEntity<Void> updateFanComment(
        @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
        @PathVariable("homepee-id") Long homepeeId,
        @RequestBody UpdateFanCommentRequest request
    ){
        fanCommentService.updateFanComment(principal.getId(), request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공생평 삭제")
    @DeleteMapping("/{fan-comment-id}")
    public ResponseEntity<Void> deleteFanComment(
        @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
        @PathVariable("homepee-id") Long homepeeId,
        @PathVariable("fan-comment-id") Long fanCommentId
    ) {
        fanCommentService.deleteFanComment(principal.getId(), fanCommentId);
        return ResponseEntity.noContent().build();
    }
}
