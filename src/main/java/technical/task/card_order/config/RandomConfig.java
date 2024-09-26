package technical.task.card_order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Configuration
public class RandomConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}
