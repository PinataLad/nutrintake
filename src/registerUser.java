import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

public class registerUser {

    private static final String filePath = "AccountData.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new username: ");
        String username = scanner.nextLine();
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();

        String salt = generateSalt();

        String hashedPassword = Login.hashPassword(salt, password);

        saveUser(username, salt, hashedPassword);

        System.out.println("User " + username + " has been registered");

        scanner.close();
    }

    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        StringBuilder hexString = new StringBuilder();

        for (byte b : saltBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public static void saveUser(String username, String salt, String hashedPassword) {
        try{
            FileWriter mywriter = new FileWriter(filePath, true);

            mywriter.write(username + "," + salt + "," + hashedPassword + "\n");

            mywriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
