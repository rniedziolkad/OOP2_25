package pl.umcs.oop.powtorzeniegui.server;

import javafx.scene.paint.Color;
import pl.umcs.oop.powtorzeniegui.Dot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final static List<ClientThread> clientList = new CopyOnWriteArrayList<>();
    private static Connection dbConnection;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serwer wystartował");
            connectToDatabase();
            while (true) {
                System.out.println("Oczekiwanie na połączenie...");
                Socket accepted = serverSocket.accept();
                System.out.println("Połączono: "+accepted);

                ClientThread ct = new ClientThread(accepted);
                ct.setDaemon(true);
                clientList.add(ct);
                ct.start();

                System.out.println("Rozpoczynam wesyłanie danych z bazy");
                for (Dot dot : getSavedDots()) {
                    ct.send(dot.toMessage());
                }
                System.out.println("Wysyłanie zakończone!");
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Dot> getSavedDots() {
        List<Dot> savedDots = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dot";
            Statement stmt = dbConnection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double radius = rs.getDouble("radius");
                Color color = Color.valueOf(rs.getString("color"));

                savedDots.add(new Dot(x, y, color, radius));
            }
        } catch (SQLException e) {
            System.err.println("Błąd odczytu: "+e.getMessage());
        }

        return savedDots;
    }

    private static void connectToDatabase() throws SQLException {
        // Wymaga dodania zależniości w pom.xml: sqlite-jdbc od org.xerial
        dbConnection = DriverManager.getConnection("jdbc:sqlite:server.db");
        System.out.println("Połączonno z bazą danych");
        Statement stmt = dbConnection.createStatement();
        String create = """
                CREATE TABLE IF NOT EXISTS dot(
                id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                x INTEGER NOT NULL,
                y INTEGER NOT NULL,
                color TEXT NOT NULL,
                radius INTEGER NOT NULL
                );
                """;
        String clear = "DROP TABLE IF EXISTS dot;";

        stmt.executeUpdate(clear);
        System.out.println("Usunięto tabelę");
        stmt.executeUpdate(create);
        System.out.println("Utworzono tabelę");
    }

    public static void saveDot(Dot dot) {
        try {
            String sql = "INSERT INTO dot(x, y, color, radius) VALUES (?, ?, ?, ?);";
            PreparedStatement stmt = dbConnection.prepareStatement(sql);
            stmt.setDouble(1, dot.x());
            stmt.setDouble(2, dot.y());
            stmt.setString(3, dot.color().toString());
            stmt.setDouble(4, dot.radius());

            stmt.executeUpdate();
            System.out.println("Zapisano do bazy: "+dot.toMessage());
        } catch (SQLException e) {
            System.err.println("Bład zapisu do bazy: "+e.getMessage());
        }
    }

    public static void broadcast(String message) {
        Dot dot = Dot.fromMessage(message);
        saveDot(dot);
        for (ClientThread client : clientList) {
            client.send(message);
        }
    }

    public static void removeClient(ClientThread client) {
        clientList.remove(client);
    }
}
