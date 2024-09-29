package ru.interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.interpol.domain.entity.InterpolArchiveEntity;

import java.time.LocalDate;
import java.util.List;

public interface InterpolArchiveRepository extends JpaRepository<InterpolArchiveEntity, Long> {
    @Query(value = "select * from interpol_archive i where i.criminal_person ->> 'firstName' like :firstName or " +
            "i.criminal_person ->> 'lastName' like :lastName or " +
            "i.criminal_person ->> 'nickName' like :nickName or " +
            "CAST(i.criminal_person ->> 'growth' as integer) = :growth or " +
            "i.criminal_person ->> 'hairColor' like :hairColor or " +
            "i.criminal_person ->> 'eyeColor' like :eyeColor or " +
            "i.criminal_person ->> 'specialSings' like :specialSings or " +
            "i.criminal_person ->> 'nationality' like :nationality or " +
            "CAST(i.criminal_person ->> 'dateBirth' as date) = :dateBirth or " +
            "i.criminal_person ->> 'placeBirth' like :placeBirth or " +
            "i.criminal_person ->> 'lastResidence' like :lastResidence or " +
            "i.criminal_person ->> 'languages' like :languages or " +
            "i.criminal_person ->> 'criminalProfession' like :criminalProfession", nativeQuery = true)
    List<InterpolArchiveEntity> searchCriminalPerson(@Param("firstName") String firstName,
                                                     @Param("lastName") String lastName,
                                                     @Param("nickName") String nickName,
                                                     @Param("growth") Integer growth,
                                                     @Param("hairColor") String hairColor,
                                                     @Param("eyeColor") String eyeColor,
                                                     @Param("specialSings") String specialSings,
                                                     @Param("nationality") String nationality,
                                                     @Param("dateBirth") LocalDate dateBirth,
                                                     @Param("placeBirth") String placeBirth,
                                                     @Param("lastResidence") String lastResidence,
                                                     @Param("languages") String languages,
                                                     @Param("criminalProfession") String criminalProfession);
}
