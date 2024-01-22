package pl.sonmiike.parliamentclient.contract.side;

import lombok.Data;

@Data
public class VotingOptionsDTO {

    private String option;
    private String optionIndex;
    private int votes;
}
