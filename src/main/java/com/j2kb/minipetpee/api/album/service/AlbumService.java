package com.j2kb.minipetpee.api.album.service;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.response.AlbumPageResult;
import com.j2kb.minipetpee.api.album.controller.dto.response.AlbumResult;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.api.comment.repository.CommentRepository;
import com.j2kb.minipetpee.global.repository.PostRepository;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final PostRepository postRepository;
    private final TabRepository tabRepository;
    private final CommentRepository commentRepository;

    //게시물 저장
    @Transactional
    public AlbumPost saveAlbumPost(Long homepeeId, SaveAlbumPostRequest albumPostRequest) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //AlbumPost 생성
        AlbumPost albumPost = albumPostRequest.toEntity(tab);

        //string url 값 Image 객체로 변환
        albumPostRequest.getImages()
                .stream()
                .map(Image::new)
                .forEach(image -> albumPost.setImages(image));

        return postRepository.save(albumPost);
    }

    //갤러리 목록 조회
    @Transactional(readOnly = true)
    public AlbumPageResult findAlbumPosts(Long homepeeId, JwtAuthenticationPrincipal principal, Pageable pageablePost, Pageable pageableComment) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //tab 공개인지 비공개 인지 확인
        if(!tab.isVisible() && (!homepeeId.equals(principal.getHomepeeId()) || Objects.isNull(principal))) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP5001);
        }

        List<AlbumResult> albumResults = new ArrayList<>();
        //tab id로 album 조회
        Page<Post> albumPosts = postRepository.findAllByTabId(tab.getId(), pageablePost);
        //갤러리 포스트의 댓글 조회
        for (Post albumPost : albumPosts) {
            Page<Comment> postComment = commentRepository.findByPostId(albumPost.getId(), pageableComment);
            albumResults.add(new AlbumResult(albumPost, postComment));
        }

        return new AlbumPageResult(albumResults, albumPosts);
    }

    //게시글 수정
    @Transactional
    public void updateAlbumPost(Long homepeeId, UpdateAlbumPostRequest albumPostRequest) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));
        Post albumPost = postRepository.findByIdAndTabId(albumPostRequest.getId(), tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        //전달된 이미지 Image 객체로 변경
        List<Image> images = albumPostRequest.toEntity();

        albumPost.updatePostTitle(albumPostRequest.getTitle());
        albumPost.updatePostImages(images);
    }

    //게시글 삭제
    @Transactional
    public void deleteAlbumPost(Long homepeeId, Long postId) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));
        Post albumPost = postRepository.findByIdAndTabId(postId, tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        //게시글 삭제
        postRepository.delete(albumPost);
    }
}
