package pl.sonmiike.parliamentlogging.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private  LocalDateTime logDate;
    private String logMessage;

    private String logLevel;

}
