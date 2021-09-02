package com.j2kb.minipetpee.api.setting.repository;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TabRepository extends JpaRepository<Tab, Long> {

    Optional<Tab> findByHomepeeIdAndType(Long homepeeId, Type type);
    List<Tab> findAllByHomepeeId(Long homepeeId);
}
