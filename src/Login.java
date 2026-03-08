import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {

    private static final String myPepper = "MyTestPepper";

    public static void main(String[] args) {
        String usernameInput = "MyTestUser";
        String passwordInput = "MyTestPassword";
        String filePath = "AccountData.txt";

        boolean success = verifyUserLoginSuccess(usernameInput, passwordInput, filePath, ",");

        System.out.println(success);
    }

    public static String hashPassword(String salt, String password) {
        try {
            String saltPass = salt + password + myPepper;

            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] hash = digester.digest(saltPass.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyUserLoginSuccess(String usernameInput, String passwordInput, String filePath, String delimiter) {
        String currentLine;
        String data[];

        try {
            FileReader filereader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(filereader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                data = currentLine.split(delimiter);
                String username = data[0];
                String salt = data[1];
                String password = data[2];

                String computedHashPass = hashPassword(salt, passwordInput);

                if (username.equals(usernameInput) && password.equals(computedHashPass)){
                    return true;
                }

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}