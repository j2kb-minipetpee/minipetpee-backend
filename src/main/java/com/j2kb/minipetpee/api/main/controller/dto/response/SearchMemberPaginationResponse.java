package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class SearchMemberPaginationResponse {

    private final List<SearchMemberResponse> content;
    private final PageResponse page;

    public SearchMemberPaginationResponse(Page<Member> searchMembers) {
        if (Objects.isNull(searchMembers)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.content = searchMembers.stream()
                .map(SearchMemberResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(searchMembers);
    }
}
