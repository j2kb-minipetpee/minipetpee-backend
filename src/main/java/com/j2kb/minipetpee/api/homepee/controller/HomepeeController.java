package com.j2kb.minipetpee.api.homepee.controller;

import com.j2kb.minipetpee.api.homepee.controller.dto.HomepeeResponse;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.fancomment.service.FanCommentService;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "홈피 API")
@RestController
@RequestMapping("apis/{homepee-id}")
@RequiredArgsConstructor
public class HomepeeController {

    private final HomepeeService homepeeService;
    private final FanCommentService fanCommentService;

    @Operation(summary = "홈피 조회")
    @GetMapping
    public ResponseEntity<HomepeeResponse> find(@PathVariable(name = "homepee-id") Long homepeeId) {
        Homepee homepee = homepeeService.findById(homepeeId);
        List<FanComment> fanComments = fanCommentService.findAllByHomepeeId(homepeeId);

        return ResponseEntity.ok(new HomepeeResponse(homepee, fanComments));
    }
}
