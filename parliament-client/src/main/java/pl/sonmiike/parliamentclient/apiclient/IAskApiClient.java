package pl.sonmiike.parliamentclient.apiclient;

import pl.sonmiike.parliamentclient.contract.ParliamentClubDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentMemberDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentVotingsDTO;
import pl.sonmiike.parliamentclient.contract.VotesDTO;

import java.util.List;

public interface IAskApiClient {

    List<ParliamentClubDTO> getClubs();
    List<ParliamentMemberDTO> getMPs();
    List<ParliamentVotingsDTO> getVotings();

    List<VotesDTO> getMPVotings(long mpId);

}
