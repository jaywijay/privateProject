import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddPasswordToPDF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file input: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Masukkan nama file output: ");
        String outputFilePath = scanner.nextLine();

        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        addPasswordToPDF(inputFilePath, outputFilePath, password);
    }

    public static void addPasswordToPDF(String inputFilePath, String outputFilePath, String password) {
        try {
            // Membaca file PDF input
            PDDocument document = PDDocument.load(new File(inputFilePath));

            // Mengatur password pada dokumen PDF
            AccessPermission accessPermission = new AccessPermission();
            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(password, password, accessPermission);
            document.protect(protectionPolicy);

            // Menyimpan dokumen dengan password
            document.save(outputFilePath);
            document.close();

            System.out.println("Password berhasil ditambahkan pada file PDF.");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
