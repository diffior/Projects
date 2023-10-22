package Receiver;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Arrays;
import java.util.Scanner;

public class receiver {
    private static final String IV = "AAAAAAAAAAAAAAAA";

    public static void main(String[] args) throws Exception {
        // Step 2: Read information on keys and generate Ky-
        PrivateKey yPrivKey = readPrivKeyFromFile("Receiver/YPrivate.key");

        if (yPrivKey == null) {
            System.err.println("Failed to read the private key.");
            return;
        }

        // Step 3: Input the name of the message file
        System.out.println("Input the name of the message file:");
        Scanner scanner = new Scanner(System.in);
        String messageFileName = scanner.nextLine();

        // Step 4: RSA decryption to get Kxy
        byte[] kxy = decryptRSA("Receiver/kxy.rsacipher", yPrivKey);

        // Display Kxy in Hexadecimal bytes
        System.out.println("Kxy in Hexadecimal: ");
        convertHex(kxy);

        // Step 5: AES Decryption to get M
        byte[] m = decryptAES("Receiver/message.aescipher", kxy);

        // Step 6: Calculate the SHA256 hash of (Kxy || M || Kxy)
        byte[] computedHash = computeHash(m, kxy);
        byte[] receivedHash = readMessage("Receiver/message.khmac");

        // Display both keyed hash MACs in Hexadecimal
        System.out.println("Computed Hash in Hexadecimal:");
        convertHex(computedHash);
        System.out.println("Received Hash in Hexadecimal:");
        convertHex(receivedHash);

        // Check hash match and decide to save the file or delete it
        if (Arrays.equals(computedHash, receivedHash)) {
            // Write M to the specified file
            try (FileOutputStream fos = new FileOutputStream(messageFileName)) {
                fos.write(m);
            }

            System.out.println("Authentication passed!");
        } else {
            System.out.println("Authentication failed! Please try again.");
            // Delete the input message file if authentication fails.
            Files.deleteIfExists(Paths.get(messageFileName));
        }
    }

    private static PrivateKey readPrivKeyFromFile(String keyFileName) throws Exception {
        try (FileInputStream fis = new FileInputStream(keyFileName);
             ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis))) {

            BigInteger modulus = (BigInteger) ois.readObject();
            BigInteger privateExponent = (BigInteger) ois.readObject();

            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Error reading private key from file", e);
        }
    }

    private static void convertHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString().trim());
    }

    private static byte[] computeHash(byte[] m, byte[] kxy) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(kxy);
        outputStream.write(m);
        outputStream.write(kxy);
        byte[] data = outputStream.toByteArray();
        return md.digest(data);
    }

    private static byte[] decryptRSA(String file, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedData = readMessage(file);
        return cipher.doFinal(encryptedData);
    }

    private static byte[] decryptAES(String file, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding", "SunJCE");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes()));

        byte[] encryptedData = readMessage(file);
        return cipher.doFinal(encryptedData);
    }

    private static byte[] readMessage(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }
}
