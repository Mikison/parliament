package pl.sonmiike.parliamentlogging;

import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentlogging.contract.LogDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {


    private List<LogDTO> logsMap = new ArrayList<>();


    public void registerService(LogDTO logDTO) {
        logsMap.add(logDTO);
    }


    public List<LogDTO> getServiceLogs() {
        return logsMap;
    }
}
