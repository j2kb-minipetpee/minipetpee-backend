package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class AlbumResult {
    private final Post albumPost;
    private final Page<Comment> albumComment;

    public AlbumResult(Post albumPost,Page<Comment> albumComment) {
        this.albumPost = albumPost;
        this.albumComment = albumComment;
    }
}
