/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author Fixiu
 */
public class Login extends javax.swing.JFrame {
    
    public static String user= "";
    String pass="";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setSize(400,550);
        setResizable(false);
        setTitle("Acceso al sistema");
        setLocationRelativeTo(null);
        
        ImageIcon wallpaper= new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel1_Wallpaper.getWidth(),
                jLabel1_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel1_Wallpaper.setIcon(icono);
        this.repaint();
        
        
        ImageIcon wallpaper_logo= new ImageIcon("src/images/DS.png");
        Icon icono_logo = new ImageIcon(wallpaper_logo.getImage().getScaledInstance
        (jLabel_Logo.getWidth() , jLabel_Logo.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Logo.setIcon(icono_logo);
        this.repaint();
    }
    
    
    @Override
    public Image getIconImage()
    {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Logo = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jButton_Iniciar = new javax.swing.JButton();
        jLabel_Footer = new javax.swing.JLabel();
        jLabel1_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 270, 270));

        txt_user.setBackground(new java.awt.Color(153, 153, 255));
        txt_user.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_user.setForeground(new java.awt.Color(255, 255, 255));
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 330, 210, -1));

        txt_password.setBackground(new java.awt.Color(153, 153, 255));
        txt_password.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 370, 210, -1));

        jButton_Iniciar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Iniciar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_Iniciar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Iniciar.setText("Iniciar");
        jButton_Iniciar.setBorder(null);
        jButton_Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IniciarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 420, 210, 35));

        jLabel_Footer.setText("Creado por FIXIU");
        getContentPane().add(jLabel_Footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, -1, -1));

        jLabel1_Wallpaper.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(jLabel1_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IniciarActionPerformed
            
        user = txt_user.getText().trim();
        pass= txt_password.getText().trim();
        
        if (!user.equals("") || !pass.equals("") ) {
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst=cn.prepareStatement(
                    "select rol, estatus from usuarios where username = '" +user
                        + "' and password = '" + pass + "'");
                
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    
                    String rol= rs.getString("rol");    
                    String estatus= rs.getString("estatus");
                    
                    if (rol.equalsIgnoreCase("Administrador")&& estatus.equalsIgnoreCase("Activo")) {
                        dispose();
                        new Administrador().setVisible(true);
                    }else if (rol.equalsIgnoreCase("Cocinero")&& estatus.equalsIgnoreCase("Activo")) {
                        dispose();
                        new Cocinero().setVisible(true);
                    }else if (rol.equalsIgnoreCase("Recepcionista")&& estatus.equalsIgnoreCase("Activo")) {
                        dispose();
                        new Recepcionista().setVisible(true);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null,"Datos de acceso incorrectos");
                    txt_user.setText("");
                    txt_password.setText("");
                }
                
            } catch (SQLException e) {
                System.err.println("Error en el boton de acceder."+e);
                JOptionPane.showMessageDialog(null, "!!Error al iniciar sesion !!, contactar al administrador");
            }
            
            
        }else {
            JOptionPane.showMessageDialog(null,"Debes llenar todos los campos.");
        }
        
    }//GEN-LAST:event_jButton_IniciarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iniciar;
    private javax.swing.JLabel jLabel1_Wallpaper;
    private javax.swing.JLabel jLabel_Footer;
    private javax.swing.JLabel jLabel_Logo;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}