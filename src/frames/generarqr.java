package frames;

import clases.QRCreator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import static principal.principal.Arduino;

public class generarqr extends javax.swing.JFrame {

    String fechaHora = "";

    final String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat fech = new SimpleDateFormat("dd / MM / YYYY");
        return fech.format(fecha);
    }

    Color colorp = Color.white, colors = Color.black;
    BufferedImage iconoSys = null;

    public generarqr() {
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/IconoCalavera.png")).getImage());
        initComponents();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(generarqr.class.getName()).log(Level.SEVERE, null, ex);
        }
        ultimoregistro();
        lbFecha.setText(fechaActual());
        this.setLocationRelativeTo(null);
    }

    void ingresar() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/bdestacionamiento", "root", "");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            fechaHora = dateFormat.format(date);
            System.out.print(dateFormat.format(date));
            Statement stat = conexion.createStatement();
            String sql = "INSERT INTO vehiculos (folio,horaentrada,estado) VALUES ('" + txtFolio.getText() + "','"
                    + fechaHora + "','Disponible')";
            stat.executeUpdate(sql);
            //JOptionPane.showMessageDialog(null, "El vehiculo se registro exitosamente");

        } catch (SQLException ex) {
            Logger.getLogger(generarqr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ultimoregistro() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/bdestacionamiento", "root", "");
            Statement stat = conexion.createStatement();
            ResultSet rs = stat.executeQuery("SELECT folio FROM vehiculos ORDER BY folio DESC LIMIT 1");
            String fol;
            while (rs.next()) {
                fol = rs.getString("folio");
                int folio = Integer.parseInt(fol);
                int foli = folio + 1;
                txtFolio.setText("" + foli);
                crearqr();
            }

        } catch (SQLException ex) {
            Logger.getLogger(generarqr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void crearqr() {
        if (this.txtFolio.getText().length() > 0) {
            iconoSys = qr.createQR(this.txtFolio.getText(), 345, colorp, colors);
            ImageIcon QR = new ImageIcon(iconoSys);
            this.lbQR.setIcon(QR);
            //iconoSys = qr.createQR(this.texto.getText(), 500);
        } else {
            iconoSys = null;
            this.lbQR.setIcon(null);
        }
    }

    QRCreator qr = new QRCreator();

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "hh:mm:ss a";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            hora.setText(String.format(formato.format(sistemaHora), now));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        lbQR = new javax.swing.JLabel();
        hora = new org.edisoncor.gui.label.LabelMetric();
        btnImprimir = new javax.swing.JButton();
        lbFecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada EL BROMAS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BIENVENIDO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 22, 276, 32));

        txtFolio.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        txtFolio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFolioKeyReleased(evt);
            }
        });
        jPanel1.add(txtFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 101, 343, 34));
        jPanel1.add(lbQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 153, 343, 294));

        hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hora.setText("HORA");
        hora.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jPanel1.add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 60, 140, -1));

        btnImprimir.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Abrir.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 453, 127, 39));

        lbFecha.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        lbFecha.setForeground(new java.awt.Color(255, 255, 255));
        lbFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 60, 185, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Flames.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Timer tiempo = new Timer(100, new generarqr.hora());
        tiempo.start();
    }//GEN-LAST:event_formWindowOpened

    private void txtFolioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioKeyReleased
        crearqr();
    }//GEN-LAST:event_txtFolioKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        ingresar();
        ultimoregistro();
        try {
            Arduino.sendData("1");
        } catch (Exception ex) {
            Logger.getLogger(generarqr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(generarqr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(generarqr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(generarqr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(generarqr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new generarqr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private org.edisoncor.gui.label.LabelMetric hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbQR;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
