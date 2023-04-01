package banking.common;

import java.util.Random;

public class RandomCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private int codeLength;

    public RandomCodeGenerator(int codeLength) {
        this.codeLength = codeLength;
    }

    public String generateRandomCode() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.codeLength; i++) {
            int index = rand.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
