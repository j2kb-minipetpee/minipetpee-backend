package com.j2kb.minipetpee.api.guestnote.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface GuestNoteRepository extends JpaRepository<GuestNote, Long> {

    @EntityGraph(value = "GuestNote.member")
    @Query("select g from GuestNote g join g.tab t join t.homepee h where h.id = :homepeeId and t.type = 'GUEST'")
    Slice<GuestNote> findAllByHomepeeId(@Param("homepeeId") Long homepeeId, Pageable pageable);
}
