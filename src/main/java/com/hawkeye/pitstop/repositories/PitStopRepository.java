package com.hawkeye.pitstop.repositories;

import com.hawkeye.pitstop.entities.PitStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitStopRepository extends JpaRepository<PitStop, Long> {
}
