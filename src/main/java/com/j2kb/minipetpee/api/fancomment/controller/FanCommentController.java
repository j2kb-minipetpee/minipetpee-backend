package com.j2kb.minipetpee.api.fancomment.controller;

import com.j2kb.minipetpee.api.fancomment.controller.dto.FanCommentPaginationResponse;
import com.j2kb.minipetpee.api.fancomment.controller.dto.FanCommentResponse;
import com.j2kb.minipetpee.api.fancomment.controller.dto.SaveFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.controller.dto.UpdateFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.fancomment.service.FanCommentService;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
        Long currentMemberId = Optional.of(principal.getId())
            .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP11006));
        FanComment fanComment = fanCommentService.saveFanComment(homepeeId, currentMemberId, request);
        return ResponseEntity.ok(new FanCommentResponse(fanComment));
    }

    @Parameter(in = ParameterIn.QUERY
        , description = "페이지 (0 부터 시작)"
        , name = "page"
        , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
        , description = "반환할 데이터 수"
        , name = "size"
        , content = @Content(schema = @Schema(type = "integer", defaultValue = "5")))
    @Operation(summary = "공생평 조회")
    @GetMapping
    public ResponseEntity<FanCommentPaginationResponse> findAllFanComments(
        @PathVariable("homepee-id") Long homepeeId,
        @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<FanComment> fanComments = fanCommentService.findAllByHomepeeId(homepeeId, pageable);
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
