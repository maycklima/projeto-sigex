/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sql.CertificadoDAO;
import sql.ParticipanteDAO;

/**
 *
 * @author mayck
 */
public class Participantes extends javax.swing.JDialog {

    
    static int linha_selecionada = 0;
    static String linhaParticipante = "0";
    ParticipanteDAO pD = new ParticipanteDAO();
    
    public Participantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
    }
    public void inicializar()
    {
        labelMessage.setVisible(false);
        pD.getParticipantes(Main.linhaEvento,"");
    }    
    
    public void selecionaParticipante()
    {
        if(jTableParticipantes.getSelectedRow() >= 0)
            {
                linha_selecionada = jTableParticipantes.getSelectedRow();
                linhaParticipante = (String.valueOf(jTableParticipantes.getValueAt(linha_selecionada, 0))); 
                labelMessage.setVisible(false);
                btnAlterarParticipante.setEnabled(true);
                btnExcluirParticipante.setEnabled(true);
                btnGerarCertificado.setEnabled(true);
                
            }else
            {
                
            }  
    }
    
    public void excluirParticipante()
    {
        if(pD.deletarParticipante(linhaParticipante))
        {
         pD.getParticipantes(Main.linhaEvento,"");
        }
    }
    
    public void abrirCadastrarParticipantes()
    {
        JFrame mainFrame = new JFrame();
        CadastrarParticipante c = new CadastrarParticipante(mainFrame, true);
        c.setLocationRelativeTo(mainFrame);
        c.setVisible(true); 
    }
    
    public void abrirAlterarParticipante()
    {
        JFrame mainFrame = new JFrame();
        AlterarParticipante p = new AlterarParticipante(mainFrame, true);
        p.setLocationRelativeTo(mainFrame);
        p.setVisible(true); 
    }
    
    public void abrirGeraCertificadoParticipantes()
    {
        JFrame mainFrame = new JFrame();
        GerarCertificado c = new GerarCertificado(mainFrame, true);
        c.setLocationRelativeTo(mainFrame);
        c.setVisible(true); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableParticipantes = new javax.swing.JTable();
        btnAdicionarParticipante = new javax.swing.JButton();
        btnAlterarParticipante = new javax.swing.JButton();
        btnExcluirParticipante = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        labelMessage = new javax.swing.JLabel();
        btnGerarCertificado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Participantes");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        getContentPane().setLayout(null);

        jTableParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableParticipantes.setToolTipText("");
        jTableParticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableParticipantesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableParticipantesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableParticipantes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 80, 1150, 410);

        btnAdicionarParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar.png"))); // NOI18N
        btnAdicionarParticipante.setText("Adicionar participante");
        btnAdicionarParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarParticipanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarParticipante);
        btnAdicionarParticipante.setBounds(330, 510, 180, 30);

        btnAlterarParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        btnAlterarParticipante.setText("Editar participante");
        btnAlterarParticipante.setEnabled(false);
        btnAlterarParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarParticipanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterarParticipante);
        btnAlterarParticipante.setBounds(520, 510, 180, 30);

        btnExcluirParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteProjeto.png"))); // NOI18N
        btnExcluirParticipante.setText("Excluir participante");
        btnExcluirParticipante.setEnabled(false);
        btnExcluirParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirParticipanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluirParticipante);
        btnExcluirParticipante.setBounds(710, 510, 180, 30);

        title.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Participantes");
        getContentPane().add(title);
        title.setBounds(0, 24, 1200, 50);

        labelMessage.setBackground(new java.awt.Color(0, 204, 51));
        labelMessage.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMessage.setForeground(new java.awt.Color(255, 255, 255));
        labelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelMessage.setFocusable(false);
        labelMessage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMessage.setOpaque(true);
        getContentPane().add(labelMessage);
        labelMessage.setBounds(0, 0, 1200, 30);

        btnGerarCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/certificadoPequeno.png"))); // NOI18N
        btnGerarCertificado.setText("Gerar certificado");
        btnGerarCertificado.setEnabled(false);
        btnGerarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarCertificadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerarCertificado);
        btnGerarCertificado.setBounds(1010, 510, 160, 30);

        getAccessibleContext().setAccessibleName("");

        setSize(new java.awt.Dimension(1216, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarParticipanteActionPerformed
        abrirCadastrarParticipantes();
    }//GEN-LAST:event_btnAdicionarParticipanteActionPerformed

    private void btnAlterarParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarParticipanteActionPerformed
        abrirAlterarParticipante();
    }//GEN-LAST:event_btnAlterarParticipanteActionPerformed

    private void btnExcluirParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirParticipanteActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse participante?", "Excluir participante",JOptionPane.YES_NO_OPTION);
        if(resposta == 0)
        {
            excluirParticipante();
        }
    }//GEN-LAST:event_btnExcluirParticipanteActionPerformed

    private void jTableParticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParticipantesMouseClicked
       selecionaParticipante();
    }//GEN-LAST:event_jTableParticipantesMouseClicked

    private void jTableParticipantesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParticipantesMousePressed
        selecionaParticipante();
    }//GEN-LAST:event_jTableParticipantesMousePressed

    private void btnGerarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarCertificadoActionPerformed
    abrirGeraCertificadoParticipantes();
    }//GEN-LAST:event_btnGerarCertificadoActionPerformed

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
            java.util.logging.Logger.getLogger(Participantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Participantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Participantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Participantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Participantes dialog = new Participantes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdicionarParticipante;
    private javax.swing.JButton btnAlterarParticipante;
    private javax.swing.JButton btnExcluirParticipante;
    private javax.swing.JButton btnGerarCertificado;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableParticipantes;
    public static javax.swing.JLabel labelMessage;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
