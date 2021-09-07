package com.j2kb.minipetpee.api.board.service;

import com.j2kb.minipetpee.api.board.controller.dto.request.SaveBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.request.UpdateBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.response.BoardPageResult;
import com.j2kb.minipetpee.api.board.domain.BoardPost;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService {

    public final PostRepository postRepository;
    public final TabRepository tabRepository;
    public final CommentRepository commentRepository;

    //게시글 저장
    @Transactional
    public Long saveBoardPost(Long homepeeId, SaveBoardPostRequest request) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        BoardPost board = request.toEntity(tab);

        //사진 있을 경우에만 image 객체 저장
        if(!Objects.isNull(request.getImage())) {
            Image image = new Image(request.getImage());
            board.setImages(image);
        }

        BoardPost savedBoardPost = postRepository.save(board);
        return savedBoardPost.getId();
    }

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public Page<Post> findBoardPosts(Long homepeeId, JwtAuthenticationPrincipal principal, Pageable pageable) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        if(!tab.isVisible() && (!homepeeId.equals(principal.getHomepeeId()) || !Objects.isNull(principal)))
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP4006);
        return postRepository.findAllByTabId(tab.getId(), pageable);
    }

    //게시글 하나 조회
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BoardPageResult findBoardPost(Long homepeeId, Long postId,  JwtAuthenticationPrincipal principal, PageRequest pageRequest) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        if(!tab.isVisible() && (!homepeeId.equals(principal.getHomepeeId()) || !Objects.isNull(principal)))
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP4006);

        BoardPost boardPost = (BoardPost) postRepository.findByIdAndTabId(postId, tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP4001));
        //게시글 조회할 때, viewCount + 1
        boardPost.updateViewCount();

        //댓글 조회
        Page<Comment> postCommet = commentRepository.findByPostId(boardPost.getId(), pageRequest);

        return new BoardPageResult(boardPost, postCommet);
    }

    //게시글 수정
    @Transactional
    public void updateBoardPost(Long homepeeId, Long postId, UpdateBoardPostRequest request) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        BoardPost boardPost = (BoardPost) postRepository.findByIdAndTabId(postId, tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP4001));

        List<Image> images = new ArrayList<>();

        if(request.getImage().getId() != null && !request.getImage().getUrl().equals(null)
                && !request.getImage().getUrl().equals("undefined")) {
            images.add(new Image(request.getImage().getUrl()));
        }

        boardPost.updatePostTitle(request.getTitle());
        boardPost.updatePostContent(request.getContent());
        boardPost.updatePostImages(images);
        boardPost.updateUpdatedAt();
    }

    @Transactional
    public void deleteBoardPost(Long homepeeId, Long postId) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        BoardPost post = (BoardPost) postRepository.findByIdAndTabId(postId, tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP4001));
        postRepository.delete(post);
    }

}
