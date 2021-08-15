package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindBoardPostsResponse {

    @JsonValue
    private final List<FindBoardPost> boardPostList;
}
