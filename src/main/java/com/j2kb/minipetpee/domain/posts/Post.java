package com.j2kb.minipetpee.domain.posts;

import com.j2kb.minipetpee.domain.BaseTimeEntity;
import com.j2kb.minipetpee.domain.Image;
import com.j2kb.minipetpee.domain.Tab;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    private Tab tab;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;
}