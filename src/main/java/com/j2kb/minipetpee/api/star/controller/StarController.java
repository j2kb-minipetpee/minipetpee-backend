package com.j2kb.minipetpee.api.star.controller;

import com.j2kb.minipetpee.api.member.domain.MemberAdapter;
import com.j2kb.minipetpee.api.star.controller.dto.request.StarRequest;
import com.j2kb.minipetpee.api.star.controller.dto.response.FanPaginationResponse;
import com.j2kb.minipetpee.api.star.controller.dto.response.FanResponse;
import com.j2kb.minipetpee.api.star.controller.dto.response.StarPaginationResponse;
import com.j2kb.minipetpee.api.star.controller.dto.response.StarResponse;
import com.j2kb.minipetpee.api.star.service.StarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Tag(name = "스타/팬 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis")
public class StarController {

    private final StarService starService;

    @Operation(summary = "스타(팔로우)")
    @PostMapping("/stars")
    public ResponseEntity<Void> star(
            @AuthenticationPrincipal MemberAdapter memberAdapter,
            @Valid @RequestBody StarRequest starRequest
    ){
        starService.saveStar(memberAdapter, starRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "언스타(언팔로우)")
    @DeleteMapping("/stars/{star-id}")
    public ResponseEntity<Void> unstar(
            @AuthenticationPrincipal MemberAdapter memberAdapter,
            @PathVariable("star-id") long starId
    ){
        starService.deleteStar(starId);
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
            @PathVariable("member-id") long memberId,
            @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        StarResponse starMember1 = new StarResponse(11, 1, "뽀로로", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        StarResponse starMember2 = new StarResponse(22, 2, "루피", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());
        StarResponse starMember3 = new StarResponse(33, 3, "포비", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", LocalDateTime.now());

        List<StarResponse> stars = new ArrayList<>();
        stars.add(starMember1);
        stars.add(starMember2);
        stars.add(starMember3);

        return ResponseEntity.ok(stars);
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
            @PathVariable("member-id") long memberId,
            @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
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
