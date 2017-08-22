package com.hawkeye.pitstop.repositories;

import com.hawkeye.pitstop.entities.PitStopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path="pitstops", collectionResourceRel="pitstops")
public interface PitStopRepository extends JpaRepository<PitStopEntity, Long> {
}
