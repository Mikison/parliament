package pl.sonmiike.parliamentupdater.updater.mappers;

import pl.sonmiike.parliamentclient.contract.*;
import pl.sonmiike.parliamentclient.contract.side.VotingOptionsDTO;
import pl.sonmiike.parliamentdata.model.*;


public interface IMap {

    IMapper<ParliamentClubDTO, ParliamentClub> clubs();
    IMapper<ParliamentMemberDTO, ParliamentMembers> members();
    IMapper<ParliamentVotingsDTO, Voting> votings();
    IMapper<VotingOptionsDTO, VotingOptions> votingOptions();
    IMapper<VotesDTO, Votes> votes();



}
