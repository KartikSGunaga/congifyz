package task03;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FileEncryptionDecryption {
    static Scanner scanner= new Scanner(System.in);
    private boolean fileExists(String path){
        File file = new File(path);

        return file.exists();
    }

    public String[] readFile(String path) throws Exception {
        File file = new File(path);
        Scanner scFile = new Scanner(file);
        List<String> lines = new ArrayList<>();

        while (scFile.hasNextLine()) {
            String line = scFile.nextLine();
            lines.add(line);
        }

        scFile.close();

        return lines.toArray(new String[0]);
    }

    public String encryptFile(String textToEncrypt){
        String encryptedText = null;
        try{
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(textToEncrypt.getBytes("UTF8"));

            encryptedText = new String(textEncrypted);

        }catch(Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }

        return encryptedText;
    }

    public String decryptFile(String textToDecrypt) {
        String decryptedText = null;

        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(textToDecrypt);
            byte[] decryptedBytes = desCipher.doFinal(encryptedBytes);

            decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Exception during decryption");
            e.printStackTrace();
        }

        return decryptedText;
    }

    public void writeToFile(String path, String suffix, String content) {
        try {
            Path filePath = Paths.get(path);
            String fileName = filePath.getFileName().toString();

            String fileExtension = "";
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex != -1) {
                fileExtension = fileName.substring(dotIndex);
                fileName = fileName.substring(0, dotIndex);
            }

            String newFileName =  fileName + suffix + fileExtension;

            Path newFilePath = filePath.resolveSibling(newFileName);

            Files.write(newFilePath, content.getBytes());

            System.out.println("\nFile created: " + newFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to File encryptor decryptor!");
        FileEncryptionDecryption fed = new FileEncryptionDecryption();

        String textToFile;

        System.out.println("\nEnter the file path: ");
        String path = scanner.nextLine();
        if(fed.fileExists(path)){
            System.out.println("\nPress 'E' to encrypt; 'D' to decrypt: ");
            String operation = scanner.next();

            String fileContent = Arrays.toString(fed.readFile(path));

            if(operation.equals("E")){
                textToFile = fed.encryptFile(fileContent);
                fed.writeToFile(path, "Encrypted", textToFile);
            }else {
                textToFile = fed.decryptFile(fileContent);
                fed.writeToFile(path, "Decrypted", textToFile);
            }

        }
        else{
            System.out.println("File doesn't exist!");
        }
        System.out.println("Thank you");
    }
}
