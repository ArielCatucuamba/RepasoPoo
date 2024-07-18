import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar extends JFrame{
    private JButton ingresarRegistrosButton;
    private JTextField historial;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField teléfono;
    private JTextField edad;
    private JTextField descripción;
    private JButton buscarPacienteButton;
    private JPanel panel1;
    private JTextField cedula;

    public Registrar(){
        setContentPane(panel1);
        ingresarRegistrosButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Registrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        buscarPacienteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buscar b1=new buscar();
                b1.Iniciar();
                dispose();
            }
        });
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Ingreso de pacientes");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    //2 creo mi metodo registro
    public void Registrar() throws SQLException {
        // 1 creo mi conexion
        Connection conecta=conexion();
        //2 traigo lo que se ingrese en mis JTextField
        String cedu=cedula.getText();
        String h =historial.getText();
        String n = nombre.getText();
        String a=apellido.getText();
        String t=teléfono.getText();
        String e=edad.getText();
        String d=descripción.getText();

        //3 Creo mi consulta
        String sql="INSERT INTO PACIENTE(cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) VALUES(?,?,?,?,?,?,?)";
        //creo mi Prepared Statemente
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        //digo que va en cada ?
        // si es String uso un setString y si es de otro tipo hago una transformacion (debe ser del tipo que sea en la base de datos)
        pstmt.setString(1,cedu);
        pstmt.setInt(2,Integer.parseInt(h));
        pstmt.setString(3,n);
        pstmt.setString(4,a);
        pstmt.setString(5,t);
        pstmt.setInt(6,Integer.parseInt(e));
        pstmt.setString(7,d);

        //guardo el executeUpdate en una variable
        int rowsAffected =pstmt.executeUpdate();
        if (rowsAffected>0){
            JOptionPane.showMessageDialog(null,"Imgreso de datos exitoso");
        }
        else {
            JOptionPane.showMessageDialog(null,"Reintentelo otra vez");
        }
        //cierro todo
        conecta.close();
        pstmt.close();
    }

    //1 creo mi metodo conexion
    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
}
