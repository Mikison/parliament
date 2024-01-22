package pl.sonmiike.parliamentupdater;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sonmiike.parliamentlogging.LogClient;
import pl.sonmiike.parliamentlogging.contract.LogDTO;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "pl.sonmiike")
@RequiredArgsConstructor
@Slf4j
public class ParliamentUpdaterApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(ParliamentUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Updater Started!");
        try {
            LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Updater Started!", "INFO"));
        } catch (Exception e) {
            log.error("Error while sending logs to logging service");
        }

    }
}
