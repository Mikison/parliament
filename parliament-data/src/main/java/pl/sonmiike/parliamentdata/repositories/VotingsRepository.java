package pl.sonmiike.parliamentdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sonmiike.parliamentdata.model.Voting;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VotingsRepository extends JpaRepository<Voting, Long> {


    Optional<Voting> findByDate(LocalDateTime date);
}
