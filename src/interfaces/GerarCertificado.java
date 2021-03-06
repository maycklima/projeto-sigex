
package interfaces;

import static interfaces.RegistroDigital.txtPagina;
import model.FixedLengthDocument;
import sql.CertificadoDAO;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sql.RegistroDAO;

/**
 *
 * @author mayck
 */
public class GerarCertificado extends javax.swing.JDialog {

    int rowEventos = Main.jTableEventos.getSelectedRow();
    int rowParticipantes = Participantes.jTableParticipantes.getSelectedRow();
    String linhaProjeto = Main.jTableEventos.getValueAt(rowEventos, 0).toString();
    RegistroDAO registro = new RegistroDAO();
    
    public GerarCertificado(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        
        txtLivro.setText(registro.getMaiorLivro());
        txtPagina.setText(registro.getMaiorPagina(registro.getMaiorLivro()));
        if(jbAutomatico.isSelected()){
            txtRegistro.setEnabled(false);
        }else{
            txtRegistro.setEnabled(true);
        }
        labelMensagem.setVisible(false);
        txtRegistro.setText(String.valueOf(registro.getMaiorRegistro(registro.getMaiorLivro(), registro.getMaiorPagina(registro.getMaiorLivro()))));
        
    
    }
  
      public void gerarCertificado()
      {   
          CertificadoDAO certificado = new CertificadoDAO();
          if(!checkTransparente.isSelected())
          {
              certificado.geraCertificadoParticipante(linhaProjeto,txtLivro.getText(),txtPagina.getText(),txtRegistro.getText()); 
          }else{
              certificado.geraCertificadoParticipanteTransparente(linhaProjeto,txtLivro.getText(),txtPagina.getText(),txtRegistro.getText()); 
          }
          
      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgroupRegistro = new javax.swing.ButtonGroup();
        labelMensagem = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        nomeAluno1 = new javax.swing.JLabel();
        txtParticipante = new javax.swing.JTextField();
        nomeOrientador = new javax.swing.JLabel();
        txtEvento = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        checkTransparente = new javax.swing.JCheckBox();
        btnPreviousLivro = new javax.swing.JButton();
        txtLivro = new javax.swing.JTextField();
        btnNextLivro = new javax.swing.JButton();
        labelLivro = new javax.swing.JLabel();
        btnPreviousPagina = new javax.swing.JButton();
        txtPagina = new javax.swing.JTextField();
        btnNextPagina = new javax.swing.JButton();
        labelLivro1 = new javax.swing.JLabel();
        txtRegistro = new javax.swing.JTextField();
        jbAutomatico = new javax.swing.JRadioButton();
        jbManual = new javax.swing.JRadioButton();
        nRegistro = new javax.swing.JLabel();
        labelMensagem1 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerar certificado");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        titulo.setBackground(new java.awt.Color(204, 204, 204));
        titulo.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Gerar certificado");
        titulo.setOpaque(true);
        getContentPane().add(titulo);
        titulo.setBounds(0, 0, 430, 60);

        nomeAluno1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeAluno1.setText("Participante");
        getContentPane().add(nomeAluno1);
        nomeAluno1.setBounds(30, 150, 110, 20);

        txtParticipante.setEnabled(false);
        getContentPane().add(txtParticipante);
        txtParticipante.setBounds(30, 170, 350, 30);

        nomeOrientador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeOrientador.setText("Evento");
        getContentPane().add(nomeOrientador);
        nomeOrientador.setBounds(30, 210, 110, 20);

        txtEvento.setEnabled(false);
        getContentPane().add(txtEvento);
        txtEvento.setBounds(30, 230, 350, 30);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/certificadoPequeno.png"))); // NOI18N
        jButton1.setText("Gerar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 320, 100, 30);

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 60, 430, 10);

        checkTransparente.setText("Fundo transparente");
        checkTransparente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTransparenteActionPerformed(evt);
            }
        });
        getContentPane().add(checkTransparente);
        checkTransparente.setBounds(30, 270, 140, 23);

        btnPreviousLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/previous.png"))); // NOI18N
        btnPreviousLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousLivroActionPerformed(evt);
            }
        });
        getContentPane().add(btnPreviousLivro);
        btnPreviousLivro.setBounds(30, 100, 30, 30);
        getContentPane().add(txtLivro);
        txtLivro.setBounds(60, 100, 40, 30);

        btnNextLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        btnNextLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextLivroActionPerformed(evt);
            }
        });
        getContentPane().add(btnNextLivro);
        btnNextLivro.setBounds(100, 100, 30, 30);

        labelLivro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelLivro.setText("Livro");
        getContentPane().add(labelLivro);
        labelLivro.setBounds(60, 80, 60, 20);

        btnPreviousPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/previous.png"))); // NOI18N
        btnPreviousPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousPaginaActionPerformed(evt);
            }
        });
        getContentPane().add(btnPreviousPagina);
        btnPreviousPagina.setBounds(140, 100, 30, 30);
        getContentPane().add(txtPagina);
        txtPagina.setBounds(170, 100, 40, 30);

        btnNextPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next.png"))); // NOI18N
        btnNextPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPaginaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNextPagina);
        btnNextPagina.setBounds(210, 100, 30, 30);

        labelLivro1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelLivro1.setText("Página");
        getContentPane().add(labelLivro1);
        labelLivro1.setBounds(170, 80, 60, 20);

        txtRegistro.setDocument(new FixedLengthDocument(6));
        txtRegistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRegistroFocusGained(evt);
            }
        });
        txtRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRegistroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRegistroKeyTyped(evt);
            }
        });
        getContentPane().add(txtRegistro);
        txtRegistro.setBounds(250, 100, 90, 30);

        bgroupRegistro.add(jbAutomatico);
        jbAutomatico.setSelected(true);
        jbAutomatico.setText("Automático");
        jbAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAutomaticoActionPerformed(evt);
            }
        });
        getContentPane().add(jbAutomatico);
        jbAutomatico.setBounds(340, 90, 90, 23);

        bgroupRegistro.add(jbManual);
        jbManual.setText("Manual");
        jbManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbManualActionPerformed(evt);
            }
        });
        getContentPane().add(jbManual);
        jbManual.setBounds(340, 113, 90, 20);

        nRegistro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nRegistro.setText("Nº Registro");
        getContentPane().add(nRegistro);
        nRegistro.setBounds(250, 80, 110, 20);

        labelMensagem1.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem1.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem1.setFocusable(false);
        labelMensagem1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem1.setOpaque(true);
        getContentPane().add(labelMensagem1);
        labelMensagem1.setBounds(0, 0, 430, 20);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(-10, 60, 440, 320);

        setSize(new java.awt.Dimension(436, 400));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtParticipante.setText(Participantes.jTableParticipantes.getValueAt(rowParticipantes, 1).toString());
        txtEvento.setText(Main.jTableEventos.getValueAt(rowEventos, 1).toString());
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gerarCertificado();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkTransparenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTransparenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkTransparenteActionPerformed

    private void btnPreviousLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousLivroActionPerformed
        int n_livro = Integer.parseInt(txtLivro.getText()) - 1;
        if(n_livro >= 1)
        {
        txtLivro.setText(String.valueOf(n_livro));
        if(registro.getMaiorPagina(String.valueOf(n_livro)) == null)
        {
            txtPagina.setText("1");
        }else{
             txtPagina.setText(registro.getMaiorPagina(String.valueOf(n_livro)));  
        } 
        txtRegistro.setText(String.valueOf(registro.getMaiorRegistro(String.valueOf(n_livro), registro.getMaiorPagina(String.valueOf(n_livro)))));
        }else
        {
        }
    }//GEN-LAST:event_btnPreviousLivroActionPerformed

    private void btnNextLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLivroActionPerformed
        int n_livro = Integer.parseInt(txtLivro.getText()) + 1;
        txtLivro.setText(String.valueOf(n_livro));
        if(registro.getMaiorPagina(String.valueOf(n_livro)) == null)
        {
            txtPagina.setText("1");
        }else{
             txtPagina.setText(registro.getMaiorPagina(String.valueOf(n_livro)));  
        }       
        txtRegistro.setText(String.valueOf(registro.getMaiorRegistro(String.valueOf(n_livro), registro.getMaiorPagina(String.valueOf(n_livro)))));
    }//GEN-LAST:event_btnNextLivroActionPerformed

    private void btnPreviousPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousPaginaActionPerformed
        int n_pagina = Integer.parseInt(txtPagina.getText()) - 1;
        if(n_pagina >= 1)
        {
            txtPagina.setText(String.valueOf(n_pagina));
            txtRegistro.setText(String.valueOf(registro.getMaiorRegistro(String.valueOf(txtLivro.getText()), txtPagina.getText())));
        }else
        {
        }
    }//GEN-LAST:event_btnPreviousPaginaActionPerformed

    private void btnNextPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPaginaActionPerformed
        int n_pagina = Integer.parseInt(txtPagina.getText()) + 1;
        txtPagina.setText(String.valueOf(n_pagina));
        txtRegistro.setText(String.valueOf(registro.getMaiorRegistro(String.valueOf(txtLivro.getText()), txtPagina.getText())));
    }//GEN-LAST:event_btnNextPaginaActionPerformed

    private void txtRegistroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRegistroFocusGained
        labelMensagem.setVisible(false);
    }//GEN-LAST:event_txtRegistroFocusGained

    private void txtRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegistroKeyPressed
        
    }//GEN-LAST:event_txtRegistroKeyPressed

    private void txtRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegistroKeyTyped
       
    }//GEN-LAST:event_txtRegistroKeyTyped

    private void jbAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAutomaticoActionPerformed
        txtRegistro.setEnabled(false);
    }//GEN-LAST:event_jbAutomaticoActionPerformed

    private void jbManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbManualActionPerformed
        txtRegistro.setEnabled(true);
    }//GEN-LAST:event_jbManualActionPerformed

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
            java.util.logging.Logger.getLogger(GerarCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarCertificado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GerarCertificado dialog = null;
                dialog = new GerarCertificado(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel bg;
    private javax.swing.ButtonGroup bgroupRegistro;
    private javax.swing.JButton btnNextLivro;
    private javax.swing.JButton btnNextPagina;
    private javax.swing.JButton btnPreviousLivro;
    private javax.swing.JButton btnPreviousPagina;
    private javax.swing.JCheckBox checkTransparente;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton jbAutomatico;
    private javax.swing.JRadioButton jbManual;
    private javax.swing.JLabel labelLivro;
    private javax.swing.JLabel labelLivro1;
    public static javax.swing.JLabel labelMensagem;
    public static javax.swing.JLabel labelMensagem1;
    private javax.swing.JLabel nRegistro;
    private javax.swing.JLabel nomeAluno1;
    private javax.swing.JLabel nomeOrientador;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtEvento;
    public static javax.swing.JTextField txtLivro;
    public static javax.swing.JTextField txtPagina;
    private javax.swing.JTextField txtParticipante;
    private javax.swing.JTextField txtRegistro;
    // End of variables declaration//GEN-END:variables
}
