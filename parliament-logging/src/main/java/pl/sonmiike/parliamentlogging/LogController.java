package pl.sonmiike.parliamentlogging;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sonmiike.parliamentlogging.contract.LogDTO;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/sejm/logs")
public class LogController {



    private final LogService LogService;


    @GetMapping
    public ResponseEntity<List<LogDTO>> getLogs() {
        return ResponseEntity.ok(LogService.getServiceLogs());
    }

    @PostMapping
    public ResponseEntity<?> registerLogs(@RequestBody LogDTO logResultsDTO) {
        LogService.registerService(logResultsDTO);
        return ResponseEntity.ok().build();
    }
}
