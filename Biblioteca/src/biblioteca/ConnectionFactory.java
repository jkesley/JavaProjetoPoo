package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configurações da conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Retorna uma conexão com o banco de dados MySQL
     * 
     * @return Connection ativa
     * @throws SQLException caso ocorra erro na conexão
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
