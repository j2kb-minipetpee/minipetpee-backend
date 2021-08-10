package com.j2kb.minipetpee.domain.posts;

import com.j2kb.minipetpee.domain.Image;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("board")
@Entity
public class BoardPost extends Post{

    @Column(nullable = false)
    private String content;
}