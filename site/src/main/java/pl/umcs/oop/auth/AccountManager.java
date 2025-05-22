package pl.umcs.oop.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import pl.umcs.oop.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountManager {
    private DatabaseConnection db;

    public AccountManager(DatabaseConnection db) {
        this.db = db;
        initTable();
    }

    private void initTable() {
        try(Statement stmt = db.getConnection().createStatement()) {
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS users(
                        id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL)
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(String username, String password) throws SQLException {
        String hashedPassword = BCrypt.withDefaults()
                .hashToString(12, password.toCharArray());

        PreparedStatement stmt = db.getConnection().prepareStatement("""
                INSERT INTO users (username, password) VALUES (?, ?);
                """);
        stmt.setString(1, username);
        stmt.setString(2, hashedPassword);
        stmt.executeUpdate();
    }

    public boolean authenticate(String username, String password) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("""
                SELECT * FROM users WHERE username = ?;
                """);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return false;
        }
        String hashedPassword = rs.getString("password");
        return BCrypt.verifyer().verify(password.toCharArray(),
                hashedPassword.toCharArray()).verified;
    }
//
    public Account getAccount(int id) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("""
                SELECT * FROM users WHERE id = ?;
                """);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            int i = rs.getInt("id");
            String username = rs.getString("username");
            return new Account(i, username);
        }
        return null;
    }

    public Account getAccount(String username) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("""
                SELECT * FROM users WHERE username = ?;
                """);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            int i = rs.getInt("id");
            String user = rs.getString("username");
            return new Account(i, user);
        }
        return null;
    }
}
