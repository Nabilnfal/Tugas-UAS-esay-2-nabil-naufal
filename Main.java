import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/toko";
        String user = "root";
        String password = "";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Kode Barang: ");
        String kode = scanner.nextLine();

        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();

        System.out.print("Harga Barang: ");
        int harga = scanner.nextInt();

        System.out.print("Stok Barang: ");
        int stok = scanner.nextInt();

        String sql = "INSERT INTO barang (kode, nama, harga, stok) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, kode);
            stmt.setString(2, nama);
            stmt.setInt(3, harga);
            stmt.setInt(4, stok);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Data barang berhasil ditambahkan.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Kode barang sudah ada. Data gagal ditambahkan.");
        } catch (SQLException e) {
            System.out.println("❌ Error SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        scanner.close();
    }
}
