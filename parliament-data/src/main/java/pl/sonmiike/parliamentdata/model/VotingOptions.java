package pl.sonmiike.parliamentdata.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class VotingOptions {
    private String option;
    private String optionIndex;
    private int votes;

}
