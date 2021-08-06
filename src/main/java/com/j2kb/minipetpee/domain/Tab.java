package com.j2kb.minipetpee.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tab_id")
    private int id;

    @Column(nullable = false)
    private Type type;

    @ColumnDefault("1")
    private boolean visible;

    @ManyTo
    @JoinColumn(name = "hompee_id")
    private Homepee homepee;
}
