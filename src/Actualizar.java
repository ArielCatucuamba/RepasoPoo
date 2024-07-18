import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Actualizar extends JFrame {
    private JButton registrarButton;
    private JButton loginButton;
    private JTextField columna;
    private JTextField nvalor;
    private JTextField cedula;
    private JPanel panel1;
    private JButton actualizarRegistroButton;

    public Actualizar() {
        setContentPane(panel1);

        registrarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar r5=new Registrar();
                r5.Iniciar();
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login l8 = null;
                try {
                    l8 = new Login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                l8.Iniciar();
             dispose();

            }
        });


        actualizarRegistroButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizacion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public void actualizacion() throws SQLException {{
        //Traigo mi Jtextfiel
        String c=columna.getText();
        String valor=nvalor.getText();
        String ced=cedula.getText();

        //1 creo el objeto conecction
        Connection conecta=conexion();

        //2 hago mi consulta
        String sql = "UPDATE PACIENTE SET " + c + " = ? WHERE cedula = ?";
        //3 creo el pstmt
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,valor);
        pstmt.setString(2,ced);

        //4 uso el executeUpdate
        int  fials=pstmt.executeUpdate();
        if (fials>0){
            JOptionPane.showMessageDialog(null,"Regitro modificado con exito");
        }else {
            JOptionPane.showMessageDialog(null,"Intentelo de nuevo");
        }
        conecta.close();
        pstmt.close();}
    }


    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }

    public void Iniciar(){
        setVisible(true);
        setTitle("Actualizar registro");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
