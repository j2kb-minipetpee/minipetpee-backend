package com.j2kb.minipetpee.domain.posts;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("board")
@Entity
public class BoardPost extends Post{

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private String content;
}