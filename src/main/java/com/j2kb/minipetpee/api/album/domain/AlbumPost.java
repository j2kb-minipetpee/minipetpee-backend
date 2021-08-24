package com.j2kb.minipetpee.api.album.domain;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@DiscriminatorValue("ALBUM")
@Entity
public class AlbumPost extends Post {
}