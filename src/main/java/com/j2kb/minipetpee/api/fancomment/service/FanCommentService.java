package com.j2kb.minipetpee.api.fancomment.service;

import com.j2kb.minipetpee.api.fancomment.controller.dto.SaveFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.controller.dto.UpdateFanCommentRequest;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.fancomment.repository.FanCommentRepository;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FanCommentService {

    private final FanCommentRepository fanCommentRepository;
    private final HomepeeRepository homepeeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FanComment saveFanComment(Long homepeeId, Long currentUserId, SaveFanCommentRequest request) {
        Homepee homepee = homepeeRepository.findById(homepeeId)
            .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP3001));

        Member member = memberRepository.findById(currentUserId)
            .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP2001));

        // TODO: 홈피 계정 주인의 팬인지 확인

        FanComment fanComment = new FanComment(member, homepee, request.getContent());
        fanCommentRepository.save(fanComment);

        return fanComment;
    }

    // TODO: pagination
    @Transactional(readOnly = true)
    public List<FanComment> findAllByHomepeeId(Long homepeeId) {
        return fanCommentRepository.findAllByHomepeeId(homepeeId);
    }

    @Transactional
    public void updateFanComment(Long currentUserId, UpdateFanCommentRequest request) {
        if (!currentUserId.equals(request.getMemberId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP11007);
        }

        FanComment fanComment = fanCommentRepository.findById(request.getId())
            .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP11005));

        fanComment.changeContent(request.getContent());
    }

    @Transactional
    public void deleteFanComment(Long currentUserId, Long fanCommentId) {
        FanComment fanComment = fanCommentRepository.findById(fanCommentId)
            .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP11005));

        if (!currentUserId.equals(fanComment.memberId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP11008);
        }

        fanCommentRepository.delete(fanComment);
    }
}
