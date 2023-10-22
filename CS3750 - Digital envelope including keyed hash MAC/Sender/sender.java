package Sender;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;


public class sender {

    private static final String IV = "AAAAAAAAAAAAAAAA";

    public static void main(String[] args) throws Exception {

        String symmetricKeyFileName = "Sender/symmetric.key"; 
        String YPublicKeyFile = "Sender/YPublic.key"; 

        PublicKey yPubKey = readPubKeyFromFile(YPublicKeyFile);
        String symmetricKey = readSymmetricKeyFromFile(symmetricKeyFileName);

        Scanner scn = new Scanner(System.in);
        System.out.println("Input the name of the message file:");
        String messageFileName = scn.nextLine();

        try (FileOutputStream out = new FileOutputStream("Sender/message.kmk")) {
            out.write(symmetricKey.getBytes(StandardCharsets.UTF_8));
            out.write(readMessage(messageFileName));
            out.write(symmetricKey.getBytes(StandardCharsets.UTF_8));
        }

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        try (FileInputStream in = new FileInputStream("Sender/message.kmk")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                sha256.update(buffer, 0, bytesRead);
            }
        }

        byte[] mac = sha256.digest();
        System.out.println("SHA-256 MAC in Hexadecimal: " + bytesToHex(mac));

        System.out.println("Do you want to invert the 1st byte in SHA256(Kxy||M||Kxy)? (Y or N)");
        String choice = scn.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            mac[0] = (byte) ~mac[0];
        }

        try (FileOutputStream out = new FileOutputStream("Sender/message.khmac")) {
            out.write(mac);
            System.out.println("MAC saved to Sender/message.khmac");
        }

        Cipher aesCipher = Cipher.getInstance("AES/CFB8/NoPadding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(symmetricKey.getBytes(StandardCharsets.UTF_8), "AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] encryptedMessage = aesCipher.doFinal(readMessage(messageFileName));
        writeToFile("Sender/message.aescipher", encryptedMessage);
        System.out.println("Encrypted message saved to Sender/message.aescipher");

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, yPubKey);
        byte[] encryptedSymmetricKey = rsaCipher.doFinal(symmetricKey.getBytes(StandardCharsets.UTF_8));
        writeToFile("Sender/kxy.rsacipher", encryptedSymmetricKey);
        System.out.println("Encrypted symmetric key saved to Sender/kxy.rsacipher");
    }

    private static byte[] readMessage(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("Sender/" + fileName));
    }

    private static void writeToFile(String fileName, byte[] content) throws IOException {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(content);
        }
    }

    public static PublicKey readPubKeyFromFile(String keyFileName) throws Exception {
        try (FileInputStream in = new FileInputStream(keyFileName);
             ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in))) {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(keySpec);
        }
    }

    public static String readSymmetricKeyFromFile(String keyFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(keyFileName))) {
            return br.readLine();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim(); // Removing trailing space.
    }
}
