package pl.sonmiike.parliamentdata.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sonmiike.parliamentdata.model.ParliamentMembers;
import pl.sonmiike.parliamentdata.model.Votes;

import java.time.LocalDateTime;
import java.util.List;

public interface VotesRepository extends JpaRepository<Votes, Long> {


    List<Votes> findAllByParliamentMember(ParliamentMembers parliamentMember);
//
//
//    @Query("SELECT v FROM Votes v ORDER BY v.mpId ASC, v.date ASC")
//    List<Votes> findAllSorted();


    List<Votes> findAllByVotingId(long votingId);
    List<Votes> findAllByDate(LocalDateTime date);

    @Transactional
    void deleteAllByVotingId(long votingId);

    @Transactional
    void deleteAllByParliamentMember(ParliamentMembers parliamentMember);

}
