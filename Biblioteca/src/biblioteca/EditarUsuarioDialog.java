package biblioteca;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditarUsuarioDialog extends JDialog {

    private JTextField txtNome, txtEmail, txtTelefone, txtTipo;
    private JButton btnSalvar, btnDeletar;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public EditarUsuarioDialog(Frame parent, Usuario usuario, UsuarioDAO usuarioDAO) {
        super(parent, "Editar Usuário", true);
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;

        setLayout(new GridLayout(6, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        add(new JLabel("Nome:"));
        txtNome = new JTextField(usuario.getNome());
        add(txtNome);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(usuario.getEmail());
        add(txtEmail);

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField(usuario.getTelefone());
        add(txtTelefone);

        add(new JLabel("Tipo:"));
        txtTipo = new JTextField(usuario.getTipo_usuario());
        add(txtTipo);

        btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> salvarAlteracoes());

        btnDeletar = new JButton("Deletar Usuário");
        btnDeletar.setForeground(Color.RED);
        btnDeletar.addActionListener(e -> deletarUsuario());

        add(btnSalvar);
        add(btnDeletar);
    }

    private void salvarAlteracoes() {
        usuario.setNome(txtNome.getText().trim());
        usuario.setEmail(txtEmail.getText().trim());
        usuario.setTelefone(txtTelefone.getText().trim());
        usuario.setTipo_usuario(txtTipo.getText().trim());

        try {
            usuarioDAO.atualizarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
            dispose(); // Fecha a janela
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário:\n" + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarUsuario() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este usuário?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                usuarioDAO.deletarUsuario(usuario.getId());
                JOptionPane.showMessageDialog(this, "Usuário deletado com sucesso!");
                dispose(); // Fecha a janela
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao deletar usuário:\n" + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
