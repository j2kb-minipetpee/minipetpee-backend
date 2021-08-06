package com.j2kb.minipetpee.domain;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Column(nullable = false)
    private String url;

    @ManyTo(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
