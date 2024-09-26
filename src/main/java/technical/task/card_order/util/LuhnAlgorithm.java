package technical.task.card_order.util;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class LuhnAlgorithm {
    public static int calculateLuhnChecksum(String cardNumberWithoutCheckDigit) {
        int sum = 0;
        boolean doubleDigit = true;

        for (int i = cardNumberWithoutCheckDigit.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumberWithoutCheckDigit.charAt(i));
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (10 - (sum % 10)) % 10;
    }
}
