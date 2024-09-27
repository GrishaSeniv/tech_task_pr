package technical.task.card_order.util;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class Utils {
    public static String getFLN(String firstName, String lastName, String surname) {
        StringBuilder flnBuilder = new StringBuilder();

        if (firstName != null && !firstName.isEmpty()) {
            flnBuilder.append(firstName.trim()).append(" ");
        }

        if (lastName != null && !lastName.isEmpty()) {
            flnBuilder.append(lastName.trim()).append(" ");
        }

        if (surname != null && !surname.isEmpty()) {
            flnBuilder.append(surname.trim());
        }

        return flnBuilder.toString().trim();
    }
}
