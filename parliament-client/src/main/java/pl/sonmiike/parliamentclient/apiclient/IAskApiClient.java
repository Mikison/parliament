package pl.sonmiike.parliamentclient.apiclient;

import pl.sonmiike.parliamentclient.contract.*;

import java.util.List;

public interface IAskApiClient {

    List<ParliamentClubDTO> getClubs();
    List<ParliamentMemberDTO> getMPs();
    List<ParliamentVotingsDTO> getVotings();

    List<VotesDTO> getMPVotings(long mpId);


}
