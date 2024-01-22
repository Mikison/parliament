package pl.sonmiike.parliamentupdater.updater.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.ParliamentClubDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentMemberDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentVotingsDTO;
import pl.sonmiike.parliamentclient.contract.VotesDTO;
import pl.sonmiike.parliamentclient.contract.side.VotingOptionsDTO;
import pl.sonmiike.parliamentdata.model.*;

@Component
@RequiredArgsConstructor
public class Mapper implements IMap {


    private final IMapper<ParliamentClubDTO, ParliamentClub> clubs;
    private final IMapper<ParliamentMemberDTO, ParliamentMembers> members;
    private final IMapper<ParliamentVotingsDTO, Voting> votings;
    private final IMapper<VotingOptionsDTO, VotingOptions> votingOptions;
    private final IMapper<VotesDTO, Votes> votes;

    @Override
    public IMapper<ParliamentClubDTO, ParliamentClub> clubs() {
        return clubs;
    }

    @Override
    public IMapper<ParliamentMemberDTO, ParliamentMembers> members() {
        return members;
    }

    @Override
    public IMapper<ParliamentVotingsDTO, Voting> votings() {
        return votings;
    }

    @Override
    public IMapper<VotingOptionsDTO, VotingOptions> votingOptions() {
        return votingOptions;
    }

    @Override
    public IMapper<VotesDTO, Votes> votes() {
        return votes;
    }
}
