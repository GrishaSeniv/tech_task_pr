package technical.task.card_order.util;

import org.springframework.stereotype.Component;

import java.util.Random;

import static technical.task.card_order.util.LuhnAlgorithm.calculateLuhnChecksum;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Component
public class CardNumberGenerator {
    private final Random random;

    public CardNumberGenerator(Random random) {
        this.random = random;
    }

    public String generateUniqueCardNumber(String bin) {
        String cardNumber;
        String randomNumber = generateCardSuffix();
        cardNumber = bin + randomNumber;
        cardNumber += calculateLuhnChecksum(cardNumber);
        return cardNumber;
    }

    private String generateCardSuffix() {
        return String.format("%09d", random.nextInt(1000000000));
    }
}
