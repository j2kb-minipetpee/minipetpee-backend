package com.j2kb.minipetpee.api.album.service;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.global.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class AlbumService {

    private final PostRepository postRepository;
    private final TabRepository tabRepository;

    public AlbumPost saveAlbumPost(Long homepeeId, SaveAlbumPostRequest saveAlbumPost) {

        //ALBUM tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //AlbumPost 생성
        AlbumPost albumPost = AlbumPost.builder()
                .title(saveAlbumPost.getTitle())
                .tab(tab)
                .build();

        //string url 값 Image 객체로 변환
        saveAlbumPost.getImages()
                .stream()
                .map(Image::new)
                .forEach(image -> albumPost.setImages(image));

        return postRepository.save(albumPost);
    }

    @Transactional(readOnly = true)
    public Page<AlbumPost> findAlbumPosts(Long homepeeId, Pageable pageable) {
        //ALBUM tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //album 상태가 공개인지 비공개인지 체크 -> 비공개 일 때, 자신의 홈피인 경우 볼 수 있어야 해서 spring security로 처리 필요**
        if(!tab.isVisible())
            new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP5001);

        //tab id로 album 조회
        return postRepository.findAllByTabId(tab.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public AlbumPost findAlbumPost(Long homepeeId, UpdateAlbumPostRequest updateAlbumPost) {
        //ALBUM tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.ALBUM)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP9001));

        //게시글 찾기
        AlbumPost albumPost = postRepository.findByIdAndTabId(updateAlbumPost.getId(), tab.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP5002));

        return albumPost;
    }

    public void updateAlbumPost(
            AlbumPost albumPost, UpdateAlbumPostRequest updateAlbumPost,
            List<Image> addFileList, List<Image> deleteImage
    ) {
        //Post 와 Image 연관관계 설정 및 제거
        albumPost.updateAlbum(updateAlbumPost, addFileList, deleteImage);
    }
}