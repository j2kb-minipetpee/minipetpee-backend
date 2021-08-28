package com.j2kb.minipetpee.global.domain;

import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.*;

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
        if(!Objects.isNull(images))
            this.images = images;
    }

    //사진첩 게시글 수정
    public void updateAlbum(UpdateAlbumPostRequest updateAlbumPost, List<Image> addFileList, List<Image> deleteImage) {
        this.title = updateAlbumPost.getTitle();
        if(!Objects.isNull(updateAlbumPost.getImages())) {
            deleteImage.forEach(image -> this.images.remove(image));
            addFileList.forEach(image -> this.setImages(image));
        }
    }
}