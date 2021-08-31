package com.j2kb.minipetpee.global.domain;

import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.*;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void setImages(Image image) {
        this.getImages().add(image);
        image.setPost(this);
    }

    public void setComments(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Post(String title, Tab tab, List<Image> images) {
        this.title = title;
        this.tab = tab;
        this.images = images;
    }

    //사진첩 게시글 수정
    public void updateAlbum(UpdateAlbumPostRequest updateAlbumPost, List<Image> sendImages) {
        this.title = updateAlbumPost.getTitle();
        //원래 저장된 사진들 모두 제거
        while(images.size() > 0) {
            images.remove(0);
        }
        //받아온 사진들 추가
        for (Image image : sendImages) {
            this.setImages(image);
        }
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