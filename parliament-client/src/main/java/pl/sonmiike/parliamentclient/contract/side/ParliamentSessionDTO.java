package pl.sonmiike.parliamentclient.contract.side;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ParliamentSessionDTO {

    private String title;
    private LocalDateTime startDateTime;

}
