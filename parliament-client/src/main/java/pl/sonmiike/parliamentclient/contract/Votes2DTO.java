package pl.sonmiike.parliamentclient.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Votes2DTO {

    private int mpId;
    private String club;
    private String firstName;
    private String lastName;
    private Optional<Map<String,String>> listVotes = Optional.empty();
    private String secondName;
    private String vote;


}
