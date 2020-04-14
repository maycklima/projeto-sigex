/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static interfaces.Main.jTableEventos;
import static interfaces.Main.linhaEvento;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sql.EventoDAO;

/**
 *
 * @author mayck
 */
public class ModeloCertificado extends javax.swing.JDialog {

    /**
     * Creates new form ModeloCertificado
     */
    public ModeloCertificado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    public void salvarModelo()
    {
        try
            {

                instrucao = conexao.prepareStatement("UPDATE evento set modelo_certificado = ? where id_evento = " + linhaEvento);//isso so funciona pra varchar
                System.out.println(linhaEvento);
                instrucao.setString(1, txtAreaModelo.getText());
                instrucao.executeUpdate();                
                instrucao.close();

                Main.labelMessage.setText("Modelo salvo");
                Main.labelMessage.setVisible(true);
                Main.labelMessage.setBackground(new Color(0,204,51));
                dispose();

                
            }catch( SQLException ex )
            {           
                if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
                {
                    Main.labelMessage.setVisible(true);
                    Main.labelMessage.setBackground(new Color(230,0,0));
                    Main.labelMessage.setText("Erro ao salvar modelo");
                }else{
                    JOptionPane.showMessageDialog(null, ex);                
                }
                
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnParticipante = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaModelo = new javax.swing.JTextArea();
        btnCH = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnEvento = new javax.swing.JButton();
        btnLocal = new javax.swing.JButton();
        btnData = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modelo do certificado");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        btnParticipante.setText("Participante");
        btnParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticipanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnParticipante);
        btnParticipante.setBounds(730, 50, 110, 30);

        txtAreaModelo.setColumns(20);
        txtAreaModelo.setLineWrap(true);
        txtAreaModelo.setRows(5);
        jScrollPane1.setViewportView(txtAreaModelo);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 690, 250);

        btnCH.setText("CH");
        btnCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCHActionPerformed(evt);
            }
        });
        getContentPane().add(btnCH);
        btnCH.setBounds(730, 130, 110, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salvar.png"))); // NOI18N
        jButton1.setText("Salvar modelo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(710, 350, 140, 30);

        btnEvento.setText("Evento");
        btnEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEvento);
        btnEvento.setBounds(730, 90, 110, 30);

        btnLocal.setText("Local");
        btnLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalActionPerformed(evt);
            }
        });
        getContentPane().add(btnLocal);
        btnLocal.setBounds(730, 210, 110, 30);

        btnData.setText("Data");
        btnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnData);
        btnData.setBounds(730, 170, 110, 30);

        setSize(new java.awt.Dimension(874, 436));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParticipanteActionPerformed
        txtAreaModelo.setText(txtAreaModelo.getText() + " %nomeParticipante%");
    }//GEN-LAST:event_btnParticipanteActionPerformed

    private void btnCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCHActionPerformed
        txtAreaModelo.setText(txtAreaModelo.getText() + " %chEvento%");
    }//GEN-LAST:event_btnCHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EventoDAO e = new EventoDAO();
        salvarModelo();
        e.getEvento("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         Main.linha_selecionada = jTableEventos.getSelectedRow();
         String modelo = String.valueOf(jTableEventos.getValueAt(Main.linha_selecionada, 6));
         if(modelo.equals("null")){
             txtAreaModelo.setText("");
         }else{
             txtAreaModelo.setText(modelo);
         }
        
    }//GEN-LAST:event_formWindowOpened

    private void btnEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventoActionPerformed
       txtAreaModelo.setText(txtAreaModelo.getText() + " %nomeEvento%");
    }//GEN-LAST:event_btnEventoActionPerformed

    private void btnLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalActionPerformed
       txtAreaModelo.setText(txtAreaModelo.getText() + " %localEvento%");
    }//GEN-LAST:event_btnLocalActionPerformed

    private void btnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataActionPerformed
        txtAreaModelo.setText(txtAreaModelo.getText() + " %dataEvento%");
    }//GEN-LAST:event_btnDataActionPerformed

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
            java.util.logging.Logger.getLogger(ModeloCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModeloCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModeloCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModeloCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModeloCertificado dialog = new ModeloCertificado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCH;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnEvento;
    private javax.swing.JButton btnLocal;
    private javax.swing.JButton btnParticipante;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaModelo;
    // End of variables declaration//GEN-END:variables
}
