package com.j2kb.minipetpee.api.homepee.service;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HomepeeService {
    private final HomepeeRepository homepeeRepository;

    @Transactional
    public Homepee findById(Long id) {
        final Homepee homepee = homepeeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001));
        homepee.increaseVisitCount();

        return homepee;
    }
}
