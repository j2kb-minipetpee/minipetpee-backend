package com.j2kb.minipetpee.api.setting.domain.repository;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabRepository extends JpaRepository<Tab, Long> {

    Tab findByHomepeeIdAndType(Long homepeeId, Type type);
}
