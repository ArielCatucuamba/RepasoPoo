import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar extends JFrame{
    private JButton regresarButton;
    private JButton buscarPacienteButton;
    private JTextField cedula;
    private JPanel panel1;

    public buscar(){
        setContentPane(panel1);


        regresarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar r2=new Registrar();
                r2.Iniciar();
                dispose();
            }
        });

        buscarPacienteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Buscar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    //Creo mi metodo para buscar
    public void Buscar() throws SQLException {
        //Traigo lo que ingrese mi usuario en su JTextField
        String cedu=cedula.getText();

        //1 Creo mi objeto connection
        Connection conecta=conexion();
        //2 Creo la consulta
        String sql="select * from PACIENTE where cedula=?";
        //3 creo el preparedStatement y le paso la consulta
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        //pongo el ?
        pstmt.setString(1,cedu);
        //creo un ResultSet
        ResultSet rs=pstmt.executeQuery();
        if (rs.next()){
            //En este caso para mostrar la info no necesito traerlo del label como lo hice en el insert
            //ya traigo directa,ente desde la bdd con el rs.getString("nombre_columna")

            String cedula=rs.getString("cedula");
            String historial=rs.getString("n_historial_clinico");
            String nombre=rs.getString("nombre");
            String apellido=rs.getString("apellido");
            String telefono=rs.getString("telefono");
            String edad=rs.getString("edad");
            String descipcion=rs.getString("descripcion_enfermedad");


            //Imprimo en una ventana emergente
            JOptionPane.showMessageDialog(null,"Cedula: "+cedula+" | "+"Historial: "+historial+" | "+
                    "Nombre: "+nombre+" | "+"Apellido: "+apellido+" | "+"Telefono: "+telefono+" | "+"Edad: "+edad+" | "+
                    "Descripcion: "+descipcion);
        }
        else {
            JOptionPane.showMessageDialog(null,"Intentalo de nuevo");
            cedula.setText("");
        }
        //cerrar todo
        conecta.close();
        pstmt.close();
        rs.close();

    }



    //1 Creo el metodo conexion
    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Buscar paciente");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
