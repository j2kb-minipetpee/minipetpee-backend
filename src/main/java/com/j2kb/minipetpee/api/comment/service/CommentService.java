package com.j2kb.minipetpee.api.comment.service;

import com.j2kb.minipetpee.api.comment.controller.dto.request.SavePostCommentRequest;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.api.comment.repository.CommentRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.global.repository.PostRepository;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    public final CommentRepository commentRepository;
    public final PostRepository postRepository;
    public final MemberRepository memberRepository;

    //댓글 조회 (더보기 눌렀을 때 호출)
    @Transactional(readOnly = true)
    public Page<Comment> findPostComments(Long postId, Pageable pageable) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        return commentRepository.findByPostId(post.getId(),pageable);
    }

    //게시물의 댓글 저장
    @Transactional
    public Comment savePostComment(Long postId, Long memberId, SavePostCommentRequest commentRequest) {

        //post 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        //댓글 쓴 멤버 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP2001));

        //댓글 객체 생성
        Comment comment = commentRequest.toEntity(member);

        post.setComments(comment);
        return commentRepository.save(comment);
    }

    //게시글 댓글 삭제
    @Transactional
    public void deletePostComment(Long postId, Long currentUserId, Long commentId) {
        //post 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        //댓글 찾기
        Comment comment = commentRepository.findByIdAndPostId(commentId, post.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP10003));

        // 댓글 작성자만 삭제 가능
        if (!comment.memberId().equals(currentUserId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP10004);
        }

        //댓글 삭제
        commentRepository.delete(comment);
    }

}
