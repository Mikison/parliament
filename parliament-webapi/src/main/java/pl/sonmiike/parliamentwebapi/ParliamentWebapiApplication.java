package pl.sonmiike.parliamentwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = "pl.sonmiike")
public class ParliamentWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParliamentWebapiApplication.class, args);
    }

}
