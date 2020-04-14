/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import sql.EventoDAO;
import sql.UsuarioDAO;

/**
 *
 * @author mayck
 */
public class Main extends javax.swing.JFrame {

    static int linha_selecionada = 0;
    public static String linhaEvento = "0";
    EventoDAO eD = new EventoDAO();
    
    public Main() {
        initComponents();
        inicializar();
        labelMensagem.setVisible(false);
    }
    
    public void inicializar()
    {
        labelMessage.setVisible(false);
        //btnFilttro.setVisible(false);
        //populaComboBoxVigencia();
       //selecionaVigencia();
        eD.getEvento("");
        Ola.setText("Olá, " + Usuario.getUsuario().substring(0,1).toUpperCase().concat(Usuario.getUsuario().substring(1)));
        //mostrarQnt();    
        //jPanelFiltro.setVisible(false);
        //btnCancelar.setVisible(false);
        //btnAtivar.setVisible(false);   
    }

    public void sair()
    {
        int resposta  = JOptionPane.showConfirmDialog(null , "Deseja realmente sair?" , "Logout", JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
            dispose();
            Login l = new Login();
            l.setVisible(true);
        }
       else{
            
           }
    }
    public void abrirRegistro()
    {
       labelMensagem.setVisible(false);
       JFrame mainFrame = new JFrame();
       RegistroDigital rd = new RegistroDigital(mainFrame, true);
       rd.setLocationRelativeTo(mainFrame);
       rd.setVisible(true);
    }  
    
    public void selecionaEvento()
    {
        if(jTableEventos.getSelectedRow() >= 0)
            {
                linha_selecionada = jTableEventos.getSelectedRow();
                linhaEvento = (String.valueOf(jTableEventos.getValueAt(linha_selecionada, 0))); 
                labelMessage.setVisible(false);
                btnParticipantes.setEnabled(true);
                btnAddEventos.setEnabled(true);
                btnAlterarEvento.setEnabled(true);
                btnExcluirEvento.setEnabled(true);
                btnModeloCertificado.setEnabled(true);
            }else
            {
                
            }  
    }
    
    public void excluirEvento()
    {
        if(eD.deletarEvento(linhaEvento))
        {
         eD.getEvento("");
        }
    }
    
    public void abrirCadastrarEventos()
    {
        JFrame mainFrame = new JFrame();
        CadastrarEvento e = new CadastrarEvento(mainFrame, true);
        e.setLocationRelativeTo(mainFrame);
        e.setVisible(true);
    }
    public void abrirAlterarEventos()
    {
        if(linhaEvento.equals("0"))
        {
            labelMessage.setText("Selecione um evento para editar");
            labelMessage.setBackground(new Color(255,102,0));           
            labelMessage.setVisible(true);
            
        }else{
            JFrame mainFrame = new JFrame();
            AlterarEvento e = new AlterarEvento(mainFrame, true);
            e.setLocationRelativeTo(mainFrame);
            e.setVisible(true);
        }
    }
    
    public void abrirEvento()
    {
        if(linhaEvento.equals("0"))
        {
            labelMessage.setVisible(true);
            labelMessage.setBackground(new Color(255,102,0));
            labelMessage.setText("Selecione um evento para abrir");
            
        }else{
            JFrame mainFrame = new JFrame();
            Participantes d = new Participantes(mainFrame, true);
            d.setLocationRelativeTo(mainFrame);
            d.setVisible(true);
        }
         
    }
    
    public void abrirModeloCertificado()
    {      
        if(linhaEvento.equals("0"))
        {
            labelMessage.setText("Selecione um evento para setar um modelo");
            labelMessage.setBackground(new Color(255,102,0));           
            labelMessage.setVisible(true);
            
        }else{
            JFrame mainFrame = new JFrame();
            ModeloCertificado mC = new ModeloCertificado(mainFrame, true);
            mC.setLocationRelativeTo(mainFrame);
            mC.setVisible(true);
        }
    
    }
    
    public void abrirSalvarEm()
    {
        JFrame mainFrame = new JFrame();
        SalvarEm salvar = new SalvarEm(mainFrame, true);
        salvar.setLocationRelativeTo(mainFrame);
        salvar.setVisible(true);
    }
    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModeloCertificado = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();
        Ola = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEventos = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        labelMessage = new javax.swing.JLabel();
        btnExcluirEvento = new javax.swing.JButton();
        btnAlterarEvento = new javax.swing.JButton();
        btnAddEventos = new javax.swing.JButton();
        btnParticipantes = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Opcoes = new javax.swing.JMenu();
        addUsuario = new javax.swing.JMenuItem();
        editarUsuario = new javax.swing.JMenuItem();
        excluirUsuario = new javax.swing.JMenuItem();
        sair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        salvar_em = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de Gerenciamento de Eventos de Extensão");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        btnModeloCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/certificadoPequeno.png"))); // NOI18N
        btnModeloCertificado.setText("Modelo do certificado");
        btnModeloCertificado.setToolTipText("Clique para adicionar um evento");
        btnModeloCertificado.setEnabled(false);
        btnModeloCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModeloCertificadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnModeloCertificado);
        btnModeloCertificado.setBounds(1000, 50, 180, 30);

        labelMensagem.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelMensagem.setFocusable(false);
        labelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagem.setOpaque(true);
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(0, 0, 1440, 30);
        getContentPane().add(Ola);
        Ola.setBounds(20, 50, 170, 30);

        jTableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableEventos.setToolTipText("");
        jTableEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEventosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableEventosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEventos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 1170, 500);

        title.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("SIGEX");
        getContentPane().add(title);
        title.setBounds(0, 24, 1180, 50);

        labelMessage.setBackground(new java.awt.Color(0, 204, 51));
        labelMessage.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMessage.setForeground(new java.awt.Color(255, 255, 255));
        labelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelMessage.setFocusable(false);
        labelMessage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMessage.setOpaque(true);
        getContentPane().add(labelMessage);
        labelMessage.setBounds(0, 0, 930, 30);

        btnExcluirEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteProjeto.png"))); // NOI18N
        btnExcluirEvento.setText("Excluir evento");
        btnExcluirEvento.setToolTipText("Clique para excluir o evento");
        btnExcluirEvento.setEnabled(false);
        btnExcluirEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluirEvento);
        btnExcluirEvento.setBounds(710, 590, 150, 30);

        btnAlterarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        btnAlterarEvento.setText("Alterar evento");
        btnAlterarEvento.setToolTipText("Clique para alterar o evento");
        btnAlterarEvento.setEnabled(false);
        btnAlterarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterarEvento);
        btnAlterarEvento.setBounds(540, 590, 150, 30);

        btnAddEventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar.png"))); // NOI18N
        btnAddEventos.setText("Adicionar evento");
        btnAddEventos.setToolTipText("Clique para adicionar um evento");
        btnAddEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEventosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddEventos);
        btnAddEventos.setBounds(370, 590, 150, 30);

        btnParticipantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/participantes.png"))); // NOI18N
        btnParticipantes.setText("Ver participantes");
        btnParticipantes.setToolTipText("Clique para ver os participantes do evento");
        btnParticipantes.setEnabled(false);
        btnParticipantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticipantesActionPerformed(evt);
            }
        });
        getContentPane().add(btnParticipantes);
        btnParticipantes.setBounds(1030, 590, 150, 30);

        Opcoes.setText("Opções");
        Opcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OpcoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OpcoesMouseExited(evt);
            }
        });

        addUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N
        addUsuario.setText("Novo Usuário");
        addUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(addUsuario);

        editarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alterarUser.png"))); // NOI18N
        editarUsuario.setText("Alterar Conta");
        editarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(editarUsuario);

        excluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteUser.png"))); // NOI18N
        excluirUsuario.setText("Excluir Conta");
        excluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirUsuarioActionPerformed(evt);
            }
        });
        Opcoes.add(excluirUsuario);

        sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sair.png"))); // NOI18N
        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        Opcoes.add(sair);

        jMenuBar1.add(Opcoes);

        jMenu2.setText("Registro");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        salvar_em.setText("Salvar em...");
        salvar_em.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salvar_emMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                salvar_emMousePressed(evt);
            }
        });
        jMenuBar1.add(salvar_em);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1446, 699));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       int resposta  = JOptionPane.showConfirmDialog(null , "Deseja realmente fechar o programa?" , "Saindo do sistema",JOptionPane.YES_NO_OPTION);
        
        if(resposta == 0)
        {
            System.exit(0);            
        }
       else{
            
           }
        
       
    }//GEN-LAST:event_formWindowClosing

    private void addUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsuarioActionPerformed
        JFrame mainFrame = new JFrame();
        CadastrarUsuario aU = new CadastrarUsuario(mainFrame, true);
        aU.setLocationRelativeTo(mainFrame);
        aU.setVisible(true);
    }//GEN-LAST:event_addUsuarioActionPerformed

    private void editarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarUsuarioActionPerformed
        JFrame mainFrame = new JFrame();
        AlterarUsuario usuario = new AlterarUsuario(mainFrame, true);
        usuario.setLocationRelativeTo(mainFrame);
        usuario.setVisible(true);
    }//GEN-LAST:event_editarUsuarioActionPerformed

    private void excluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirUsuarioActionPerformed
        int resposta  = JOptionPane.showConfirmDialog(null , "Você perderá acesso ao programa com a atual conta, deseja realmente excluí-la?", "Excluir conta de usuário", JOptionPane.YES_NO_OPTION);
        switch (resposta) {
            case 0:
                UsuarioDAO usuario = new  UsuarioDAO();
                usuario.deletarUsuario();     
                dispose();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita.");
                break;
            case 2:
                break;
            default:
                break;
        }
    }//GEN-LAST:event_excluirUsuarioActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        sair();
    }//GEN-LAST:event_sairActionPerformed

    private void OpcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseEntered
        Opcoes.setText("<html><u><b>Opções</b></u>");
    }//GEN-LAST:event_OpcoesMouseEntered

    private void OpcoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseExited
        Opcoes.setText("Opções");
    }//GEN-LAST:event_OpcoesMouseExited

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
    abrirRegistro();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
    abrirRegistro();
    }//GEN-LAST:event_jMenu2MousePressed

    private void jTableEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventosMouseClicked
        selecionaEvento();
    }//GEN-LAST:event_jTableEventosMouseClicked

    private void jTableEventosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventosMousePressed
        selecionaEvento();
    }//GEN-LAST:event_jTableEventosMousePressed

    private void btnModeloCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModeloCertificadoActionPerformed
        abrirModeloCertificado();
    }//GEN-LAST:event_btnModeloCertificadoActionPerformed

    private void btnExcluirEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirEventoActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse evento?","Excluir evento",JOptionPane.YES_NO_OPTION);
        if(resposta == 0)
        {
            excluirEvento();
            btnAlterarEvento.setEnabled(false);
            btnExcluirEvento.setEnabled(false);
            btnModeloCertificado.setEnabled(false);
            btnParticipantes.setEnabled(false);
        }
        
    }//GEN-LAST:event_btnExcluirEventoActionPerformed

    private void btnAlterarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarEventoActionPerformed
        abrirAlterarEventos();
    }//GEN-LAST:event_btnAlterarEventoActionPerformed

    private void btnAddEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEventosActionPerformed
        btnAlterarEvento.setEnabled(false);
        btnExcluirEvento.setEnabled(false);
        btnModeloCertificado.setEnabled(false);
        btnParticipantes.setEnabled(false);
        abrirCadastrarEventos();
    }//GEN-LAST:event_btnAddEventosActionPerformed

    private void btnParticipantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParticipantesActionPerformed
        abrirEvento();
    }//GEN-LAST:event_btnParticipantesActionPerformed

    private void salvar_emMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvar_emMouseClicked
        abrirSalvarEm();        // TODO add your handling code here:
    }//GEN-LAST:event_salvar_emMouseClicked

    private void salvar_emMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvar_emMousePressed
        abrirSalvarEm();        // TODO add your handling code here:
    }//GEN-LAST:event_salvar_emMousePressed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ola;
    private javax.swing.JMenu Opcoes;
    private javax.swing.JMenuItem addUsuario;
    private javax.swing.JButton btnAddEventos;
    private javax.swing.JButton btnAlterarEvento;
    private javax.swing.JButton btnExcluirEvento;
    private javax.swing.JButton btnModeloCertificado;
    private javax.swing.JButton btnParticipantes;
    private javax.swing.JMenuItem editarUsuario;
    private javax.swing.JMenuItem excluirUsuario;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableEventos;
    public static javax.swing.JLabel labelMensagem;
    public static javax.swing.JLabel labelMessage;
    private javax.swing.JMenuItem sair;
    private javax.swing.JMenu salvar_em;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
