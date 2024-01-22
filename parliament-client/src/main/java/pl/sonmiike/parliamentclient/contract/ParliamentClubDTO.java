package pl.sonmiike.parliamentclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParliamentClubDTO {
    private String email;
    private String fax;
    @JsonProperty("id")
    private String nameId;
    private int membersCount;
    private String name;
    private String phone;
}
