import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;
import java.awt.event.*;

public class Login {
    private static String jausuarioBueno = "Jimmy Arias";
    private static String jacontrasenaBuena = "1234";
    private static int jaintentos = 3;
    static String jacontrasenaEncriptada = jaencriptarContraseña(jacontrasenaBuena);

    /**
     *Se crea la ventana y se establece sus parámetros @param args
     */
    public void main(String[] args) {      
        JFrame jaframe = new JFrame("Acceso");
        jaframe.setSize(300, 150);
        jaframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        jaframe.add(panel);
        placeComponents(panel);

        jaframe.setVisible(true);
    }


    /**
     *Se modifica los campos del panel @param japanel
     */
    private static void placeComponents(JPanel japanel) {
        japanel.setLayout(null);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setBounds(10, 10, 80, 25);
        japanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        japanel.add(userText);

        JLabel jaetiquetaDeContraseña = new JLabel("Contraseña");
        jaetiquetaDeContraseña.setBounds(10, 40, 80, 25);
        japanel.add(jaetiquetaDeContraseña);

        JPasswordField jatextoDeLaContraseña = new JPasswordField(20);
        jatextoDeLaContraseña.setBounds(100, 40, 160, 25);
        japanel.add(jatextoDeLaContraseña);

        JButton jabotonDeLogueo = new JButton("Ingresar");
        jabotonDeLogueo.setBounds(10, 80, 80, 25);
        japanel.add(jabotonDeLogueo);

        jabotonDeLogueo.addActionListener(new ActionListener() {
        /**
     * Se crea el boton de evento para el ingreso y se verifica si es correcto o no
     */
            public void actionPerformed(ActionEvent e) {
                String jausuarioIngresado = userText.getText();
                String jacontrasenaIngresada = new String(jatextoDeLaContraseña.getPassword());

                if (jausuarioIngresado.equals(jausuarioBueno) && jacontrasenaIngresada.equals(jacontrasenaBuena)) {
                    JOptionPane.showMessageDialog(japanel, "Bienvenido!, La contraseña encriptada se refleja en la terminal : ");
                    System.out.println(jacontrasenaEncriptada);
                    System.exit(0);
                } else {
                    jaintentos--;
                    if (jaintentos == 0) {
                        JOptionPane.showMessageDialog(japanel, "Se excedio el numero de intentos, destruyendo el programa :c");
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(japanel,"Usuario o contraseña incorrectos te quedan : " + jaintentos);
                    }
                }
            }
        });
    }
      /**
     * Encripta la contraseña utilizando la notacion hexadecimal
     */

public static String jaencriptarContraseña(String jaContrasenaBuena) {
    try {
        MessageDigest jamd = MessageDigest.getInstance("SHA-256");
        jamd.update(jaContrasenaBuena.getBytes());
        byte[] jabytes = jamd.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : jabytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}
}
