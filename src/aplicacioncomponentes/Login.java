package aplicacioncomponentes;

import com.formdev.flatlaf.*;
import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.*;

public class Login extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    public static Connection con1 = null;
    public Login() {
        FlatIntelliJLaf.setup();
        
        initComponents();
        
        URL imageCiudadURL = getClass().getResource("/recursos/ciudad.jpg");
        ImageIcon ciudadIcon = new ImageIcon(imageCiudadURL);
        ImageIcon ciudadIconEscalado = new ImageIcon(ciudadIcon.getImage().getScaledInstance(300, 500, java.awt.Image.SCALE_SMOOTH));
        imagenCiudad.setIcon(ciudadIconEscalado);
        
        URL imageLogoETG = getClass().getResource("/recursos/logoETG.png");
        ImageIcon logoIconETG = new ImageIcon(imageLogoETG);
        ImageIcon logoIconEscaladoETG = new ImageIcon(logoIconETG.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
        logoETG.setIcon(logoIconEscaladoETG);
        
        URL imageLogoSimple = getClass().getResource("/recursos/logo.png");
        ImageIcon logoSimple = new ImageIcon(imageLogoSimple);
        ImageIcon logoSimpleEscalado = new ImageIcon(logoSimple.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        sbgLogo.setIcon(logoSimpleEscalado);
        
        URL imageLogoURL = getClass().getResource("/recursos/logo.png");
        ImageIcon logoIcon = new ImageIcon(imageLogoURL);
        setIconImage(logoIcon.getImage());
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        logoETG = new javax.swing.JLabel();
        institutoLabel = new javax.swing.JLabel();
        imagenCiudad = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        exitTxt = new javax.swing.JLabel();
        sbgLogo = new javax.swing.JLabel();
        iniciarSesionLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        passLabel = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        loginBtn = new javax.swing.JPanel();
        loginBtnLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoETG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.add(logoETG, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 230, 150));

        institutoLabel.setBackground(new java.awt.Color(255, 255, 255));
        institutoLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        institutoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        institutoLabel.setText("Enrique Tierno Galván");
        bg.add(institutoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 290, 20));

        imagenCiudad.setBackground(new java.awt.Color(0, 134, 190));
        imagenCiudad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ciudad.jpg"))); // NOI18N
        bg.add(imagenCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 0, 300, 500));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitTxt.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTxt.setText("X");
        exitTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exitTxt.setPreferredSize(new java.awt.Dimension(40, 40));
        exitTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 40));

        sbgLogo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        sbgLogo.setText(" SBG");
        sbgLogo.setToolTipText("");
        bg.add(sbgLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        iniciarSesionLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        iniciarSesionLabel.setText("INICIAR SESIÓN");
        bg.add(iniciarSesionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        userLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        userLabel.setText("USUARIO");
        bg.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        userTxt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        userTxt.setForeground(new java.awt.Color(153, 153, 153));
        userTxt.setText("Ingrese su nombre de usuario");
        userTxt.setBorder(null);
        userTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userTxtFocusLost(evt);
            }
        });
        bg.add(userTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 410, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 410, 20));

        passLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        passLabel.setText("CONTRASEÑA");
        bg.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        passTxt.setForeground(new java.awt.Color(204, 204, 204));
        passTxt.setText("********");
        passTxt.setBorder(null);
        passTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passTxtFocusLost(evt);
            }
        });
        bg.add(passTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 410, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 410, 20));

        loginBtn.setBackground(new java.awt.Color(75, 120, 156));

        loginBtnLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        loginBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginBtnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBtnLabel.setText("ENTRAR");
        loginBtnLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtnLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtnLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout loginBtnLayout = new javax.swing.GroupLayout(loginBtn);
        loginBtn.setLayout(loginBtnLayout);
        loginBtnLayout.setHorizontalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginBtnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        loginBtnLayout.setVerticalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginBtnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        bg.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void exitTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitTxtMouseClicked

    private void exitTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseEntered
        exitBtn.setBackground(Color.red);
        exitTxt.setForeground(Color.white);
    }//GEN-LAST:event_exitTxtMouseEntered

    private void exitTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseExited
        exitBtn.setBackground(Color.white);
        exitTxt.setForeground(Color.black);
    }//GEN-LAST:event_exitTxtMouseExited

    private void loginBtnLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnLabelMouseEntered
        loginBtn.setBackground(new Color(85, 133, 173));
    }//GEN-LAST:event_loginBtnLabelMouseEntered

    private void loginBtnLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnLabelMouseExited
        loginBtn.setBackground(new Color(75,120,156));
    }//GEN-LAST:event_loginBtnLabelMouseExited

    
    private void loginBtnLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnLabelMouseClicked
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        String usuario = userTxt.getText();
        char[] claveChars = passTxt.getPassword();
        String clave = new String(claveChars);
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con1 = DriverManager.getConnection(bbdd, usuario, clave);
            if (con1 != null) {
                JOptionPane.showMessageDialog(null, "Conexión exitosa", "Login", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                VentanaPrincipal vp = new VentanaPrincipal();
                vp.setVisible(true);
            } 
        }
        catch (SQLException e) {
            if (e.getSQLState().equals("08001")) {
                // Código de estado SQL para problemas de conexión, por ejemplo, la base de datos no está encendida
                JOptionPane.showMessageDialog(null, "La base de de datos esta fuera de servicio o no se ha inicializado", "Login", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSQLState().equals("28000")) {
                // Código de estado SQL para problemas de autenticación, por ejemplo, usuario o contraseña incorrectos
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Login", JOptionPane.ERROR_MESSAGE);
            } 
        }catch(Exception e){
            e.printStackTrace();    
            JOptionPane.showMessageDialog(null, "Error inesperado", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginBtnLabelMouseClicked

    
    private void passTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passTxtFocusLost
        if (userTxt.getText().equals("Ingrese su nombre de usuario")) {
            userTxt.setText("");
            userTxt.setForeground(Color.black);
        }
        if (String.valueOf(passTxt.getPassword()).isEmpty()) {
            passTxt.setText("********");
            passTxt.setForeground(Color.gray);
        }
    }//GEN-LAST:event_passTxtFocusLost

    private void userTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userTxtFocusLost
        if (String.valueOf(passTxt.getPassword()).equals("********")) {
            passTxt.setText("");
            passTxt.setForeground(Color.black);
        }
        if (userTxt.getText().isEmpty()) {
            userTxt.setText("Ingrese su nombre de usuario");
            userTxt.setForeground(Color.gray);
        }
    }//GEN-LAST:event_userTxtFocusLost

    private void userTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userTxtFocusGained
        if (userTxt.getText().equals("Ingrese su nombre de usuario")) {
            userTxt.setText("");
            userTxt.setForeground(Color.black);
        }
    }//GEN-LAST:event_userTxtFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JLabel exitTxt;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imagenCiudad;
    private javax.swing.JLabel iniciarSesionLabel;
    private javax.swing.JLabel institutoLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel loginBtn;
    private javax.swing.JLabel loginBtnLabel;
    private javax.swing.JLabel logoETG;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JLabel sbgLogo;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
