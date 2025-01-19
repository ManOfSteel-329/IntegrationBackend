package com.funnelsensai.core.repository;

import com.funnelsensai.core.domain.funnel.FunnelListPages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunnelListPagesRepository extends JpaRepository<FunnelListPages, Long> {
}
