package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class SendBoardPostList {

    @JsonValue
    private final List<SendBoardPost> boardPostList;

}
