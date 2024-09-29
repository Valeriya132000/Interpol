package ru.interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.interpol.domain.entity.DescriptionEntity;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {
    //
}
