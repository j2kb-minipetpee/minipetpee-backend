package com.j2kb.minipetpee.api.star.domain;

public enum Relationship {
    NONE, // 게스트 (로그인X)
    SELF, // 본인
    STAR, // 팔로잉
    UNSTAR // 팔로잉X
}
