
package interfaces;

import static interfaces.RegistroDigital.jTableRegistroDigital;
import static interfaces.RegistroDigital.linha_selecionada;
import static interfaces.RegistroDigital.txtLivro;
import static interfaces.RegistroDigital.txtPagina;
import model.Usuario;
import sql.CertificadoDAO;
import sql.RegistroDAO;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Evento;

/**
 *
 * @author mayck
 */
public class EnviarEmail extends javax.swing.JDialog 
{

    int row = RegistroDigital.jTableRegistroDigital.getSelectedRow();
    String linhaProjeto = RegistroDigital.jTableRegistroDigital.getValueAt(row, 8).toString();
    String caminho;
    JFileChooser abrir = new JFileChooser();
    String destino = "";
    String pro = String.valueOf(jTableRegistroDigital.getValueAt(linha_selecionada, 2));
    int um;
    int dois;
    String periodo;
                
    public EnviarEmail(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        labelMensagemCor.setVisible(false);
    }
    
    public void enviarEmail()
    {
            try
            {
                //usuario e senha do seu gmail
                final String usuario = "pibic.urt@ifgoiano.edu.br";
                final String senha = "mrpgurutai2013";            
                
                
                //config. do gmail
                Properties mailProps = new Properties();
                mailProps.put("mail.transport.protocol", "smtp");
                mailProps.put("mail.smtp.starttls.enable", "true");
                mailProps.put("mail.smtp.host", "smtp.gmail.com");
                mailProps.put("mail.smtp.auth", "true");
                mailProps.put("mail.smtp.user", usuario);
                mailProps.put("mail.debug", "true");
                mailProps.put("mail.smtp.port", 465);
                mailProps.put("mail.smtp.socketFactory.port", 465);
                mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                mailProps.put("mail.smtp.socketFactory.fallback", "false");

                //eh necessario autenticar
                Session mailSession = Session.getInstance(mailProps, new javax.mail.Authenticator() {

                    @Override
                    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(usuario, senha);
                    }
                });
                mailSession.setDebug(false);

                //config. da mensagem
                Message mailMessage = new MimeMessage(mailSession);

                //remetente
                mailMessage.setFrom(new InternetAddress(txtEmail.getText(), "Gerência de Extensão"));

                //destinatario
                mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText()));

                //mensagem que vai no corpo do email
                javax.mail.internet.MimeBodyPart mbpMensagem = new javax.mail.internet.MimeBodyPart();
                mbpMensagem.setText(txtMensagem.getText());
                
                //			partes do email
                Multipart mp = new javax.mail.internet.MimeMultipart();
                mp.addBodyPart(mbpMensagem);
                
                // se tiver alguma coisa anexada ela inicializar o comando abaixo
                
                
                File Arquivo = new File(caminho);
                //setando o anexo
                MimeBodyPart mbpAnexo = new MimeBodyPart();
                mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
                mbpAnexo.setFileName(Arquivo.getName());
                mp.addBodyPart(mbpAnexo);
                
                //assunto do email
                mailMessage.setSubject(txtAssunto.getText());
                
                //seleciona o conteudo
                mailMessage.setContent(mp);
                
                //envia o email
                javax.mail.Transport.send(mailMessage);
                RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Email enviado com sucesso!");
                RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                
                /*
                if(!linhaProjeto.equals("0"))
                {
                    CertificadoDAO p = new CertificadoDAO();
                    p.setOcorrenciasCertificado(linhaProjeto,String.valueOf(jTableRegistroDigital.getValueAt(linha_selecionada, 1)),txtEmail.getText());
                }*/
                
                RegistroDAO rD = new RegistroDAO();
                rD.atualizaDpsEnvio(RegistroDigital.linhaRegistro, String.valueOf(dateFormat.format(date)), "ENVIADO");
                rD.getRegistro("",txtLivro.getText(), txtPagina.getText());
                dispose();
                
                
            } catch (HeadlessException | UnsupportedEncodingException | MessagingException e) 
            {
                JOptionPane.showMessageDialog(null, "Houve um erro no Envio !\n" + e);
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

        bgroupPeriodo = new javax.swing.ButtonGroup();
        nomeAluno1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        nomeOrientador = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        escolherArquivo = new javax.swing.JButton();
        imageIcon = new javax.swing.JLabel();
        labelAssunto = new javax.swing.JLabel();
        txtAssunto = new javax.swing.JTextField();
        labelMensagem = new javax.swing.JLabel();
        txtMensagem = new javax.swing.JTextField();
        labelMensagemCor = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enviar certificado");
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        nomeAluno1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeAluno1.setText("Enviar para...");
        getContentPane().add(nomeAluno1);
        nomeAluno1.setBounds(50, 80, 110, 20);

        txtEmail.setEnabled(false);
        getContentPane().add(txtEmail);
        txtEmail.setBounds(50, 100, 350, 30);

        nomeOrientador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nomeOrientador.setText("Anexo");
        getContentPane().add(nomeOrientador);
        nomeOrientador.setBounds(50, 260, 50, 20);

        txtFile.setEditable(false);
        getContentPane().add(txtFile);
        txtFile.setBounds(50, 280, 350, 30);

        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sendemail.png"))); // NOI18N
        btnEnviar.setText("Enviar");
        btnEnviar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviar);
        btnEnviar.setBounds(280, 350, 120, 30);

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 70, 460, 20);

        escolherArquivo.setText("Escolher Arquivo...");
        escolherArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escolherArquivoActionPerformed(evt);
            }
        });
        getContentPane().add(escolherArquivo);
        escolherArquivo.setBounds(270, 310, 130, 30);
        getContentPane().add(imageIcon);
        imageIcon.setBounds(0, 270, 50, 50);

        labelAssunto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelAssunto.setText("Assunto");
        getContentPane().add(labelAssunto);
        labelAssunto.setBounds(50, 140, 90, 20);

        txtAssunto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAssunto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAssuntoFocusGained(evt);
            }
        });
        getContentPane().add(txtAssunto);
        txtAssunto.setBounds(50, 160, 350, 30);

        labelMensagem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelMensagem.setText("Mensagem");
        getContentPane().add(labelMensagem);
        labelMensagem.setBounds(50, 200, 90, 20);

        txtMensagem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMensagemFocusGained(evt);
            }
        });
        txtMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensagemActionPerformed(evt);
            }
        });
        getContentPane().add(txtMensagem);
        txtMensagem.setBounds(50, 220, 350, 30);

        labelMensagemCor.setBackground(new java.awt.Color(0, 204, 51));
        labelMensagemCor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMensagemCor.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagemCor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagemCor.setFocusable(false);
        labelMensagemCor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelMensagemCor.setOpaque(true);
        getContentPane().add(labelMensagemCor);
        labelMensagemCor.setBounds(0, 0, 460, 20);

        titulo.setBackground(new java.awt.Color(204, 204, 204));
        titulo.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emailicon.png"))); // NOI18N
        titulo.setText("Enviar");
        titulo.setOpaque(true);
        getContentPane().add(titulo);
        titulo.setBounds(0, 0, 450, 70);

        bg.setBackground(new java.awt.Color(236, 236, 236));
        bg.setOpaque(true);
        getContentPane().add(bg);
        bg.setBounds(0, 70, 460, 340);

        setSize(new java.awt.Dimension(457, 436));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtEmail.setText(jTableRegistroDigital.getValueAt(row, 5).toString());
        
        String nomeEvento = String.valueOf(jTableRegistroDigital.getValueAt(linha_selecionada, 3));        
        String pastaEvento =  String.valueOf(jTableRegistroDigital.getValueAt(linha_selecionada, 8));
        String nomeAluno =  String.valueOf(jTableRegistroDigital.getValueAt(linha_selecionada, 2));
        
        String arq = Usuario.getSalvar_em() + "\\" + nomeEvento + "\\" + nomeAluno + ".pdf";
        if(new File(arq).exists()) 
        {
            caminho = arq;
            txtFile.setText(arq);
            URL pdfIcon;
            pdfIcon = getClass().getResource("/images/iconPDF.png");
            imageIcon.setIcon(new ImageIcon(pdfIcon));
        }else{
            txtFile.setText("");
        }
        try 
        {            
            if(pastaEvento.equals("0"))
            {
                txtAssunto.setText(nomeEvento);
                txtMensagem.setText("");
                
            }else
            {
            txtAssunto.setText(nomeEvento);
            txtMensagem.setText("Olá, " +  nomeAluno + ". Segue em anexo o certificado do evento " + nomeEvento + ".");
            }
        } catch (Exception e) 
        {
            txtAssunto.setText(nomeEvento);
            txtMensagem.setText(""); 
            JOptionPane.showMessageDialog(null, e);
        }            
    }//GEN-LAST:event_formWindowOpened

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if(txtAssunto.getText().isEmpty())
        {
            labelMensagemCor.setVisible(true);
            labelMensagemCor.setText("Insira um assunto");
            labelMensagemCor.setBackground(new Color(255,0,0));
        }
        else if(txtMensagem.getText().isEmpty())
        {
            labelMensagemCor.setVisible(true);
            labelMensagemCor.setText("Insira uma mensagem");
            labelMensagemCor.setBackground(new Color(255,0,0));
        }
        else if(txtEmail.getText().isEmpty())
        {
            labelMensagemCor.setVisible(true);
            labelMensagemCor.setText("Insira um destinatário");
            labelMensagemCor.setBackground(new Color(255,0,0));
        }else if(txtFile.getText().isEmpty())
        {
            labelMensagemCor.setVisible(true);
            labelMensagemCor.setText("Selecione um arquivo para enviar");
            labelMensagemCor.setBackground(new Color(255,0,0));
        }        
        else
        {
            //enviando.setVisible(true);
            enviarEmail();     
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void escolherArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escolherArquivoActionPerformed
        URL pdfIcon;        
        labelMensagemCor.setVisible(false);
        abrir.setDialogTitle("Procurar arquivo...");
        abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);        
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Arquivo","pdf");
        abrir.setFileFilter(ff);
        int retorno = abrir.showOpenDialog(null); 
        
           if (retorno == JFileChooser.APPROVE_OPTION)  
           {
                 caminho = abrir.getSelectedFile().getAbsolutePath(); 
                 txtFile.setText(caminho);                 
                try{
                    pdfIcon = getClass().getResource("/images/iconPDF.png");
                    imageIcon.setIcon(new ImageIcon(pdfIcon));
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }    
            }
    }//GEN-LAST:event_escolherArquivoActionPerformed

    private void txtAssuntoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAssuntoFocusGained
        labelMensagemCor.setVisible(false);
    }//GEN-LAST:event_txtAssuntoFocusGained

    private void txtMensagemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMensagemFocusGained
        labelMensagemCor.setVisible(false);
    }//GEN-LAST:event_txtMensagemFocusGained

    private void txtMensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMensagemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensagemActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnviarEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            @Override
            public void run() {
                EnviarEmail dialog;
                dialog = new EnviarEmail(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup bgroupPeriodo;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton escolherArquivo;
    private javax.swing.JLabel imageIcon;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelAssunto;
    private javax.swing.JLabel labelMensagem;
    public static javax.swing.JLabel labelMensagemCor;
    private javax.swing.JLabel nomeAluno1;
    private javax.swing.JLabel nomeOrientador;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtMensagem;
    // End of variables declaration//GEN-END:variables
}
