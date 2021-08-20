package com.j2kb.minipetpee.api.guestnote.domain.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestNoteRepository extends JpaRepository<GuestNote, Long> {

    @Query("select g from GuestNote g join fetch g.member m join fetch g.tab t join t.homepee h where h.id= :homepeeId")
    List<GuestNote> findByHomepeeId(@Param("homepeeId") Long homepeeId);
}
