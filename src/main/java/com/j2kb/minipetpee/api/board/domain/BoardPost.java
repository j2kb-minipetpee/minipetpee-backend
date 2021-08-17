package com.j2kb.minipetpee.api.board.domain;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("board")
@Entity
public class BoardPost extends Post {

    @Column(nullable = false)
    private String content;
}