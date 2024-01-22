package pl.sonmiike.parliamentclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.sonmiike.parliamentclient.apiclient.AskApiClient;

@Controller
@RequiredArgsConstructor
public class cotnroller {

    private final AskApiClient askApiClient;


}
