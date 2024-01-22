package pl.sonmiike.parliamentclient.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.sonmiike.parliamentclient.contract.side.VotingOptionsDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParliamentVotingsDTO {

    private int abstain;
    private LocalDateTime date;
    private String description;
    private String kind;
    private int no;
    private int notParticipating;
    private int sitting;
    private int sittingDay;
    private int term;
    private String title;
    private Optional<String> topic = Optional.empty();
    private int votingNumber;
    private int totalVoted;
    private Optional<List<VotingOptionsDTO>> votingOptions = Optional.empty();
    private int yes;
}
