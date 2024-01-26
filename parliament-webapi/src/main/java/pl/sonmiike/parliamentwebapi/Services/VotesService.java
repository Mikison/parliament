package pl.sonmiike.parliamentwebapi.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentdata.model.Votes;
import pl.sonmiike.parliamentdata.repositories.Databases;
import pl.sonmiike.parliamentwebapi.Contract.VotesDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotesService implements IVotesService {

    private final Databases databases;
    @Override
    public List<VotesDTO> getMPVotes(long id) {
        return databases.getMPVotings().findAllByParliamentMember(databases.getMPs().findByApiID(id).orElse(null)).stream().map(this::mapFromVotes).toList();
    }

    @Override
    public List<VotesDTO> getVotingParticipants(LocalDateTime dateTime) {
        var votings = databases.getMPVotings().findAllByDate(dateTime);
        return votings.stream().map(this::mapFromVotes).toList();
    }

    public  VotesDTO mapFromVotes(Votes votes) {
        VotesDTO votesDTO = new VotesDTO();
        votesDTO.setVotingId(votes.getVoting().getId());
        votesDTO.setVote(votes.getVote());
        votesDTO.setFirstLastName(votes.getParliamentMember().getFirstLastName());
        votesDTO.setClub(votes.getParliamentMember().getParliamentClub().getNameId());
        votesDTO.setApiId(votes.getParliamentMember().getApiID());
        votesDTO.setVoteTitle(votes.getVoteTitle());
        votesDTO.setDate(votes.getDate());
        return votesDTO;
    }
}
