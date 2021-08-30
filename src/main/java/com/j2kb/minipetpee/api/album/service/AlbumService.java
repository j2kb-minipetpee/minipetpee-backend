package com.j2kb.minipetpee.api.album.service;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostCommentRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.global.repository.CommentRepository;
import com.j2kb.minipetpee.global.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class AlbumService {

    private final PostRepository postRepository;
    private final TabRepository tabRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public AlbumPost saveAlbumPost(Long homepeeId, SaveAlbumPostRequest saveAlbumPost) {

        Tab tab = findTab(homepeeId);

        //AlbumPost 생성
        AlbumPost albumPost = AlbumPost.builder()
                .title(saveAlbumPost.getTitle())
                .tab(tab)
                .build();

        //string url 값 Image 객체로 변환
        saveAlbumPost.getImages()
                .stream()
                .map(Image::new)
                .forEach(image -> albumPost.setImages(image));

        return postRepository.save(albumPost);
    }

    @Transactional(readOnly = true)
    public Page<AlbumPost> findAlbumPosts(Long homepeeId, Pageable pageable) {
        Tab tab = findTab(homepeeId);

        //tab id로 album 조회
        return postRepository.findAllByTabId(tab.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public AlbumPost findAlbumPost(Long homepeeId, UpdateAlbumPostRequest updateAlbumPost) {

        Tab tab = findTab(homepeeId);
        AlbumPost albumPost = findAlbumPost(updateAlbumPost.getId(), tab.getId());
        return albumPost;
    }

    public void updateAlbumPost(
            AlbumPost albumPost, UpdateAlbumPostRequest updateAlbumPost,
            List<Image> addFileList, List<Image> deleteImage
    ) {
        //Post 와 Image 연관관계 설정 및 제거
        albumPost.updateAlbum(updateAlbumPost, addFileList, deleteImage);
    }

    public Comment saveAlbumPostComment(Long homepeeId, Long postId, SaveAlbumPostCommentRequest albumComment) {
        Tab tab = findTab(homepeeId);
        AlbumPost albumPost = findAlbumPost(postId, tab.getId());

        //댓글 쓴 멤버 찾기
        Member member = memberRepository.findById(albumComment.getMemberId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP2001));

        //댓글 객체 생성
        Comment comment = Comment.builder()
                .content(albumComment.getContent())
                .member(member)
                .build();

        albumPost.setComments(comment);
        return commentRepository.save(comment);
    }

    public void deleteAlbumPost(Long homepeeId, Long postId) {
        Tab tab = findTab(homepeeId);
        AlbumPost albumPost = findAlbumPost(postId, tab.getId());

        //게시글 삭제
        postRepository.delete(albumPost);
    }

    public void deleteAlbumPostComment(Long homepeeId, Long postId, Long commentId) {
        Tab tab = findTab(homepeeId);
        AlbumPost albumPost = findAlbumPost(postId, tab.getId());

        //댓글 찾기
        Comment comment = commentRepository.findByIdAndPostId(commentId, albumPost.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5003));
        //댓글 삭제
        commentRepository.delete(comment);
    }

    public Map<Long, Page<Comment>> findAlbumPostComments(Page<AlbumPost> albumPosts, Pageable pageable) {
        Map<Long, Page<Comment>> comments = new HashMap<>();

        albumPosts.getContent()
                .forEach(index -> {
                    Page<Comment> postComment = commentRepository.findByPostId(index.getId(), pageable);
                    comments.put(index.getId(), postComment);
                });
        return comments;
    }

    //post Id로 album 찾고 albumId로 comment 찾기
    public Page<Comment> findAlbumPostCommentsById(Long homepeeId, Long postId, Pageable pageable) {
        Tab tab = findTab(homepeeId);
        AlbumPost albumPost = findAlbumPost(postId, tab.getId());

        return commentRepository.findByPostId(albumPost.getId(),pageable);
    }

    //homepeeId 로 tab 찾기
    public Tab findTab(Long homepeeId) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //album 상태가 공개인지 비공개인지 체크 -> 비공개 일 때, 자신의 홈피인 경우 볼 수 있어야 해서 spring security로 처리 필요**
        if(!tab.isVisible())
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP5001);
        return tab;
    }

    //postId, tabId로 albumPost 찾기
    public AlbumPost findAlbumPost(Long postId, Long tabId) {
        AlbumPost albumPost = postRepository.findByIdAndTabId(postId, tabId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));
        return albumPost;
    }
}
