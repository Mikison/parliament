package pl.sonmiike.parliamentclient;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sonmiike.parliamentclient.apiclient.AskApiClient;

@Controller
@RequiredArgsConstructor
public class controller {

    private final AskApiClient askApiClient;


    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) int id) {
        return ResponseEntity.ok(askApiClient.getMPVotings(id));
    }
}
