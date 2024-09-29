package ru.interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.interpol.domain.entity.DeveloperEntity;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    //
}
