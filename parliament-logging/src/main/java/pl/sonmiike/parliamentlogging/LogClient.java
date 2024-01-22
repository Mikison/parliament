package pl.sonmiike.parliamentlogging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sonmiike.parliamentlogging.contract.LogDTO;

@RequiredArgsConstructor
@Component
public class LogClient {



    public  void sendLogs(LogDTO logDTO) {
        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:1111")
                .pathSegment("api", "v1", "sejm", "logs")
                .build()
                .toUriString();
        new RestTemplate().postForObject(uri,
                logDTO,
                LogDTO.class);
    }


    public static LogClient getClient() {
        return new LogClient();
    }


}
