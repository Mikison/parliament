package pl.sonmiike.parliamentwebapi.Services;

import pl.sonmiike.parliamentwebapi.Contract.VotesDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IVotesService {

    List<VotesDTO> getMPVotes(long id);
    List<VotesDTO> getVotingParticipants(LocalDateTime dateTime);
}
