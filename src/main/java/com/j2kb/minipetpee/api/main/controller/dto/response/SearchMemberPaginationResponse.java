package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.controller.dto.response.PageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class SearchMemberPaginationResponse {

    private List<SearchMemberResponse> content;
    private final PageResponse page;

    public SearchMemberPaginationResponse(Page<Member> searchMembers) {
        if (!Objects.isNull(searchMembers)) {
            this.content = searchMembers
                    .stream()
                    .map(SearchMemberResponse::new)
                    .collect(Collectors.toList());
        }
        this.page = new PageResponse(searchMembers);
    }
}
