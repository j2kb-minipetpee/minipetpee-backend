package com.j2kb.minipetpee.api.board.domain;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("BOARD")
@Entity
public class BoardPost extends Post {

    @Column
    private String content;
}