package pl.sonmiike.parliamentwebapi.Contract;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VotesDTO {

    private LocalDateTime date;
    private long apiId;
    private String firstLastName;
    private String club;
    private String voteTitle;
    private long votingId;
    private String vote;
}
