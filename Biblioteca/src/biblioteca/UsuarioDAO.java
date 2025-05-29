package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // CREATE
    public void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, email, telefone, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, usuario.getTipo_usuario());

            pstmt.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    // READ
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    // READ by ID
    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return usuario;
    }

    // UPDATE
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, tipo_usuario = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getTipo_usuario());
            stmt.setInt(5, usuario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Usuário com ID não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // DELETE
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário deletado com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
