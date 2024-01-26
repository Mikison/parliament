package pl.sonmiike.parliamentwebapi.Contract;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VotingsDTO{

    private long id;
    private LocalDateTime date;
    private int sitting;
    private int sittingDay;
    private String title;
    private String topic;
    private int yes;
    private int no;
    private int abstain;
    private int totalVoted;
    private String kind;

    private List<VotesDTO> participants;

}
