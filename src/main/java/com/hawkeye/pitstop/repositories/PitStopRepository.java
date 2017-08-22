package com.hawkeye.pitstop.repositories;

import com.hawkeye.pitstop.entities.PitStopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitStopRepository extends JpaRepository<PitStopEntity, Long> {
}
