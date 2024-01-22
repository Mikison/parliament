package pl.sonmiike.parliamentwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.sonmiike")
public class ParliamentWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParliamentWebapiApplication.class, args);
    }

}
