package com.j2kb.minipetpee.global;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     * 1xxx: 회원가입, 로그인 관련 에러
     */

    /**
     * 2xxx: 회원 관련 에러
     */
    EMP2001("회원을 찾을 수 없습니다."),

    /**
     * 3xxx: 홈피 관련 에러
     */
    EMP3001("존재하지 않는 홈피입니다."),


    /**
     * 4xxx: 게시판 관련 에러
     */

    /**
     * 5xxx: 사진첩 관련 에러
     */


    /**
     * 6xxx: 방명록 관련 에러
     */

    EMP6001("방명록 저장에 실패하였습니다.");

    /**
     * 7xxx: 관리 관련 에러
     */


    /**
     * 8xxx: 스타/팬 관련 에러
     */

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
