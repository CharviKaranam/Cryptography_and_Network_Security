//program 7


import java.io.*;
import java.util.*;
import java.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Blowfish {

    public static void main(String args[]) throws IOException {

        try {
            // Generate Blowfish Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Create Cipher object
            Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");

            // Initialize cipher for encryption
            cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);

            // Get Initialization Vector
            byte[] iv = cipherOut.getIV();
            if (iv != null) {
                System.out.println(
                    "Initialization vector of the cipher: "
                    + Base64.getEncoder().encodeToString(iv)
                );
            }

            // File streams
            FileInputStream fin = new FileInputStream("input.txt");
            FileOutputStream fout = new FileOutputStream("output.txt");

            // Cipher output stream
            CipherOutputStream cout =
                    new CipherOutputStream(fout, cipherOut);

            int input;
            while ((input = fin.read()) != -1) {
                cout.write(input);
            }

            fin.close();
            cout.close();

            System.out.println("Input.txt contains: Good");
            System.out.println("The output.txt contains encrypted data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}