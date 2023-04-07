import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordManager 
{
	private static final int SALT_LENGTH = 32;

    public static String hashPassword(String password) 
    {
        String salt = generateSalt();
        String saltedPassword = salt + password;
        String hashedPassword = hash(saltedPassword);
        return salt + hashedPassword;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        String salt = hashedPassword.substring(0, SALT_LENGTH);
        String saltedPassword = salt + password;
        String computedHash = hash(saltedPassword);
        return hashedPassword.equals(salt + computedHash);
    }

    private static String generateSalt() 
    {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }

    private static String hash(String input) {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes());
            return bytesToHex(hashedBytes);
        } 
        catch (NoSuchAlgorithmException e) 
        {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
