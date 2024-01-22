package pl.sonmiike.parliamentdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sonmiike.parliamentdata.model.ParliamentMembers;

import java.util.List;
import java.util.Optional;

public interface ParliamentMembersRepository extends JpaRepository<ParliamentMembers, Long> {

    Optional<ParliamentMembers> findByFirstNameAndLastName(String firstName, String lastName);
    List<ParliamentMembers> findAllByClub(String club);

    Optional<ParliamentMembers> findByApiID(long apiID);
}
