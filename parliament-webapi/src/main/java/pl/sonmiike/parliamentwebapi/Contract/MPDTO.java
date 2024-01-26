package pl.sonmiike.parliamentwebapi.Contract;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MPDTO {

    private boolean active;
    private LocalDate birthDate;
    private String birthLocation;
    private String club;
    private String districtName;
    private String districtNum;
    private String educationLevel;
    private long apiID;
    private String email;
    private String firstLastName;
    private String firstName;
    private String lastFirstName;
    private String lastName;
    private String numberOfVotes;
    private String secondName;
    private String voivodeship;
    private String waiverDesc;
    private String vote;
    private List<VotesDTO> votes;
}
