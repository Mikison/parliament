package pl.sonmiike.parliamentupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.VotesDTO;
import pl.sonmiike.parliamentdata.model.Votes;

@Component
public class VotesMapper implements IMapper<VotesDTO, Votes> {
    @Override
    public Votes mapToEntity(VotesDTO votesDTO) {
        return map(votesDTO, new Votes());
    }

    @Override
    public Votes map(VotesDTO votesDTO, Votes votes) {
//        votes.setMpId(votesDTO.getMpID());
        votes.setVote(votesDTO.getVote());
        votes.setDate(votesDTO.getDate());
//        votes.setVotingId(votesDTO.getVotingID());
        votes.setVoteTitle(votesDTO.getTitle());
        return votes;
    }
}
