package com.j2kb.minipetpee.api.guestnote.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface GuestNoteRepository extends JpaRepository<GuestNote, Long> {

    @EntityGraph(value = "GuestNote.member")
    @Query("select g from GuestNote g join g.tab t where t.id = :tabId")
    Page<GuestNote> findAllByTabId(@Param("tabId") Long tabId, Pageable pageable);
}
