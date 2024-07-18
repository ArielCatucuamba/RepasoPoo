import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar extends JFrame{
    private JTextField cedula;
    private JButton eliminarRegistroButton;
    private JButton irALoginButton;
    private JButton irARegistrarButton;
    private JPanel panel1;

    public Eliminar()  {

        setContentPane(panel1);
        irALoginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login l9 = null;
                try {
                    l9 = new Login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                l9.Iniciar();
                dispose();
            }
        });
        irARegistrarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar r9=new Registrar();
                r9.Iniciar();
                dispose();
            }
        });
        eliminarRegistroButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Eliminacion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void Eliminacion() throws SQLException {
        String c=cedula.getText();

        Connection conecta=conexion();
        String sql="delete from PACIENTE where cedula=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,c);

        int conteo = pstmt.executeUpdate();
        if (conteo>0){
            JOptionPane.showMessageDialog(null,"Registro eliminado con exito");

        }else {
            JOptionPane.showMessageDialog(null,"Lo siento vuelvalo a intentar");
        }

    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Buscar paciente");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String pasword="123456";
        return DriverManager.getConnection(url,user,pasword);
    }
}
