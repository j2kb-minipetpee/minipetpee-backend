package com.j2kb.minipetpee.global.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id", nullable = false)
    private Tab tab;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, Tab tab) {
        this.title = title;
        this.tab = tab;
    }

    public void setImages(Image image) {
        this.getImages().add(image);
        image.setPost(this);
    }

    public Homepee homepee() {
        if (Objects.isNull(tab)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP9001);
        }
        if (Objects.isNull(tab.getHomepee())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001);
        }
        return this.getTab().getHomepee();
    }

    public Long homepeeId() {
        return this.homepee().getId();
    }

    //여기서 size()가 0보다 큰게 아니면 null 보내는게 맞지 않을까요?
    public Long imageId() {
        if (Objects.isNull(images)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP4001);
        }
        if (images.size() > 0) {
            return images.get(0).getId();
        }
        return null;
    }

    //여기도 null 보내는게 나을까요?
    public String imageUrl() {
        if (Objects.isNull(images)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP4001);
        }
        if (images.size() > 0) {
            return images.get(0).getUrl();
        }
        return "";
    }

    public Member member() {
        if (Objects.isNull(tab)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001);
        }
        if (Objects.isNull(tab.getHomepee())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001);
        }
        if (Objects.isNull(tab.getHomepee().getMember())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return this.getTab().getHomepee().getMember();
    }

    public String memberName() {
        return this.member().getProfile().getName();
    }

    public Profile profile() {
        if (Objects.isNull(member().getProfile())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2002);
        }
        return this.member().getProfile();
    }
}