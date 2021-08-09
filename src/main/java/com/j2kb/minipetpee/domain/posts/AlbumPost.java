package com.j2kb.minipetpee.domain.posts;

import com.j2kb.minipetpee.domain.Image;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@DiscriminatorValue("album")
@Entity
public class AlbumPost extends Post{

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<>();
}