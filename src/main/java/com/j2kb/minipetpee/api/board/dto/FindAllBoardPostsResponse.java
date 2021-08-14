package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindAllBoardPostsResponse {

    @JsonValue
    private final List<FindBoardPost> boardPostList;

}
