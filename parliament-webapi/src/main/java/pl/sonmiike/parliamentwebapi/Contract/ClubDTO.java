package pl.sonmiike.parliamentwebapi.Contract;


import lombok.Data;

import java.util.List;

@Data
public class ClubDTO {
    private long id;
    private String nameId;
    private String name;
    private int membersCount;
    private String email;
    private List<MPDTO> members;
}

