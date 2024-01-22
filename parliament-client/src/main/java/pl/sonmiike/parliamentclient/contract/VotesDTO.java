package pl.sonmiike.parliamentclient.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VotesDTO {

    private long mpID;
    private long votingID;
    private LocalDateTime date;
    private String description;
    private String kind;
    private Optional<Map<String,String>> listVotes = Optional.empty();
    private String title;
    private Optional<String> topic = Optional.empty();
    private String vote;
    private int votingNumber;
}
