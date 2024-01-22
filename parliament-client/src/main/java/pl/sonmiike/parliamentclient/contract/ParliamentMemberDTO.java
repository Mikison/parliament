package pl.sonmiike.parliamentclient.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParliamentMemberDTO {

    private boolean active;
    private LocalDate birthDate;
    private String birthLocation;
    private String club;
    private String districtName;
    private String districtNum;
    private String educationLevel;
    private String email;
    private String firstLastName;
    private String firstName;
    @JsonProperty("id")
    private long apiId;
    private String lastFirstName;
    private String lastName;
    private String numberOfVotes;
    private String secondName;
    private String voivodeship;
    private Optional<String> waiverDesc = Optional.empty();
    private Optional<List<VotesDTO>> votes = Optional.empty();


}
