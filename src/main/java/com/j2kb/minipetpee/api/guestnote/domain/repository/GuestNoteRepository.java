package com.j2kb.minipetpee.api.guestnote.domain.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface GuestNoteRepository extends JpaRepository<GuestNote, Long> {

    @Query("select g from GuestNote g join fetch g.member m join fetch g.tab t join t.homepee h where h.id= :homepeeId")
    Slice<GuestNote> findByHomepeeId(@Param("homepeeId") Long homepeeId, Pageable pageable);
}
