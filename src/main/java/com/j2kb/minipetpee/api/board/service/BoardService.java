package com.j2kb.minipetpee.api.board.service;

import com.j2kb.minipetpee.api.board.controller.dto.request.SaveBoardPostRequest;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    public final PostRepository postRepository;
    public final TabRepository tabRepository;
    public final CommentRepository commentRepository;

    //게시글 저장
    @Transactional
    public Long saveBoardPost(Long homepeeId, SaveBoardPostRequest boardPostRequest) {
        Tab tab = findTabByHomepeeId(homepeeId);

        Image image = new Image(boardPostRequest.getImage());
        BoardPost board = boardPostRequest.toEntity(tab);
        board.setImages(image);

        BoardPost savedBoardPost = postRepository.save(board);
        return savedBoardPost.getId();
    }

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public Page<Post> findBoardPosts(Long homepeeId, Pageable pageable) {
        Tab tab = findTabByHomepeeId(homepeeId);
        return postRepository.findAllByTabId(tab.getId(), pageable);
    }

    //게시글 하나 조회
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Post findBoardPost(Long homepeeId, Long postId) {
        Tab tab = findTabByHomepeeId(homepeeId);
        Post post = findBoardPostByPostIdAndTabId(postId, tab.getId());
        //게시글 조회할 때, viewCount + 1
        ((BoardPost) post).updateViewCount();
        return post;
    }

    @Transactional(readOnly = true)
    public Page<Comment> findBoardPostComments(Long boardPostId, PageRequest pageRequest) {
        return commentRepository.findByPostId(boardPostId, pageRequest);
    }

    //homepeeId 로 tab 찾기
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public Tab findTabByHomepeeId(Long homepeeId) {
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.BOARD)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));
        //tab 공개인지 비공개 인지 확인 -> 홈피 주인인 경우는 아래의 로직 안들어가도록 추가하기!!
        if(!tab.isVisible())
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP4006);
        return tab;
    }

    //postId, tabId로 boardPost 찾기
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public Post findBoardPostByPostIdAndTabId(Long postId, Long tabId) {
        return postRepository.findByIdAndTabId(postId, tabId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP4001));
    }

}
