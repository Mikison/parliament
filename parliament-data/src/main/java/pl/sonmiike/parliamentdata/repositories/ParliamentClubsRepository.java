package pl.sonmiike.parliamentdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sonmiike.parliamentdata.model.ParliamentClub;

import java.util.Optional;

public interface ParliamentClubsRepository extends JpaRepository<ParliamentClub, Long> {

    Optional<ParliamentClub> findByName(String name);
    Optional<ParliamentClub> findByNameId(String name);
}
