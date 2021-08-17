package com.j2kb.minipetpee.api.album.domain;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("album")
@Entity
public class AlbumPost extends Post {

}