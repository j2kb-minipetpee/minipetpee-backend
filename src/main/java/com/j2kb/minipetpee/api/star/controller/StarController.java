package com.j2kb.minipetpee.api.star.controller;

import com.j2kb.minipetpee.api.star.controller.dto.response.FanPaginationResponse;
import com.j2kb.minipetpee.api.star.controller.dto.response.StarPaginationResponse;
import com.j2kb.minipetpee.api.star.controller.dto.response.StarRelationshipResponse;
import com.j2kb.minipetpee.api.star.domain.Star;
import com.j2kb.minipetpee.api.star.service.StarService;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "스타/팬 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis")
public class StarController {

    private final StarService starService;

    @Operation(summary = "스타(팔로우) 여부 확인")
    @GetMapping("/{fan-member-id}/star/{star-member-id}")
    public ResponseEntity<StarRelationshipResponse> checkStarRelationship(
            @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable("fan-member-id") Long fanMemberId,
            @PathVariable("star-member-id") Long starMemberId
    ){
        boolean result = starService.hasStarRelationship(principal.getId(), fanMemberId, starMemberId);
        return ResponseEntity.ok(new StarRelationshipResponse(fanMemberId, starMemberId, result));
    }

    @Operation(summary = "스타(팔로우)")
    @PostMapping("/{fan-member-id}/star/{star-member-id}")
    public ResponseEntity<Void> star(
            @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable("fan-member-id") Long fanMemberId,
            @PathVariable("star-member-id") Long starMemberId
    ){
        // 저장
        starService.saveStar(principal.getId(), fanMemberId, starMemberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "언스타(언팔로우)")
    @DeleteMapping("/{fan-member-id}/star/{star-member-id}")
    public ResponseEntity<Void> unstar(
            @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable("fan-member-id") Long fanMemberId,
            @PathVariable("star-member-id") Long starMemberId
    ){
        // 삭제
        starService.deleteStar(principal.getId(), fanMemberId, starMemberId);
        return ResponseEntity.noContent().build();
    }


    @Parameter(in = ParameterIn.QUERY
            , description = "페이지 (0 부터 시작)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "반환할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "5")))
    @Operation(summary = "스타 목록")
    @GetMapping("/{member-id}/stars")
    public ResponseEntity<StarPaginationResponse> findStars(
            @PathVariable("member-id") Long memberId,
            @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Star> stars = starService.findStars(memberId, pageable);
        return ResponseEntity.ok(new StarPaginationResponse(stars));
    }


    @Parameter(in = ParameterIn.QUERY
            , description = "페이지 (0 부터 시작)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "반환할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "4")))
    @Operation(summary = "팬 목록")
    @GetMapping("/{member-id}/fans")
    public ResponseEntity<FanPaginationResponse> findFans(
            @PathVariable("member-id") Long memberId,
            @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Star> fans = starService.findFans(memberId, pageable);
        return ResponseEntity.ok(new FanPaginationResponse(fans));
    }
}
