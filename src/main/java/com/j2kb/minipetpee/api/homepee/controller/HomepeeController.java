package com.j2kb.minipetpee.api.homepee.controller;

import com.j2kb.minipetpee.api.homepee.controller.dto.HomepeeResponse;
import com.j2kb.minipetpee.api.homepee.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.service.FanCommentService;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("apis/{homepee-id}")
@RequiredArgsConstructor
public class HomepeeController {

    private final HomepeeService homepeeService;
    private final FanCommentService fanCommentService;

    @GetMapping
    public ResponseEntity<HomepeeResponse> find(@PathVariable(name = "homepee-id") Long homepeeId) {
        Homepee homepee = homepeeService.findById(homepeeId);
        List<FanComment> fanComments = fanCommentService.findAllByHomepeeId(homepeeId);

        return ResponseEntity.ok(new HomepeeResponse(homepee, fanComments));
    }
}
