package pl.itkurnik.skirental.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

@Configuration
public class TimeConfiguration {

    @Bean
    public ZoneId zoneId() {
        return ZoneId.systemDefault();
    }
}
