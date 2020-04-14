package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.Main;
import model.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import interfaces.Participantes;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Karol
 */
public class CertificadoDAO 
{    
    
    public void geraCertificadoParticipante(String linhaProjeto, String n_livro, String n_pagina, String n_registro)
    {
        EventoDAO evento = new EventoDAO();
        RegistroDAO r = new RegistroDAO();
        Document document = new Document(PageSize.A4.rotate());
        
        Font f = new  Font(Font.getFamily("Calibri"), 72, Font.NORMAL);
        Font f2 = new Font(Font.getFamily("Calibri"), 10, Font.BOLD);
        Font f3 = new Font(Font.getFamily("Calibri"), 12, Font.BOLD);
        Font f4 = new Font(Font.getFamily("Calibri"), 12);
            
        int rowEvento      = Main.jTableEventos.getSelectedRow();
        String nomeEvento  = Main.jTableEventos.getValueAt(rowEvento, 1 ).toString();
        String tipoEvento  = Main.jTableEventos.getValueAt(rowEvento, 2 ).toString();
        String dataEvento  = Main.jTableEventos.getValueAt(rowEvento, 3 ).toString();
        String chEvento    = Main.jTableEventos.getValueAt(rowEvento, 4 ).toString();
        String localEvento = Main.jTableEventos.getValueAt(rowEvento, 7 ).toString();
        
        String pastaEvento  = Main.jTableEventos.getValueAt(rowEvento, 0 ).toString();
        
        int rowParticipante      = Participantes.jTableParticipantes.getSelectedRow();             
        String nomeParticipante  = Participantes.jTableParticipantes.getValueAt(rowParticipante, 1 ).toString();
        String emailParticipante = Participantes.jTableParticipantes.getValueAt(rowParticipante, 2 ).toString();
        String texto = evento.getModelo(Participantes.jTableParticipantes.getValueAt(rowParticipante, 4 ).toString());
              
        
           try 
            {
                
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + nomeEvento);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(  diretorio + "\\" + n_registro + "-" + nomeParticipante + ".pdf"));
                document.open();
                URL certificado = getClass().getResource("/images/certificado.png");
                Image img = Image.getInstance(certificado);
                
                float[] columnWidths = {255};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(95);
                
                PdfPTable table2 = new PdfPTable(columnWidths);
                table2.setWidthPercentage(90);
                table2.setSpacingBefore(100);
                
                
                Paragraph paragrafo1 = new Paragraph(" ", f);
                paragrafo1.setLeading(175f);
                
                PdfPCell cell = new PdfPCell();                
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setFixedHeight(332f);
                texto = texto.replace("%nomeParticipante%", nomeParticipante);
                texto = texto.replace("%nomeEvento%", nomeEvento); 
                texto = texto.replace("%tipoEvento%", tipoEvento); 
                texto = texto.replace("%chEvento%", chEvento); 
                texto = texto.replace("%dataEvento%", dataEvento); 
                texto = texto.replace("%localEvento%", localEvento); 
                
                
                String HTML = "<p class=\"texto\">" + texto + "<br></br><br></br><br></br><br></br></p><p class=\"data\">" + getData() + "</p>";
                String CSS = "p.texto{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align: justify;}p.data{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align:right;}";
            
                ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);            
                list.forEach((element) -> 
                {
                    cell.addElement(element);
                });
                table.addCell(cell);
                
                PdfPCell cellMinisterio = new PdfPCell(new Phrase("Ministério da Educação",f3));
                cellMinisterio.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cellMinisterio.setBorder(Rectangle.NO_BORDER);
                cellMinisterio.setBorderWidthBottom(0);   
                table2.addCell(cellMinisterio);
                
                PdfPCell instituto = new PdfPCell(new Phrase("Instituto Federal de Educação, Ciência e Tecnologia Goiano",f3));
                instituto.setHorizontalAlignment(Element.ALIGN_CENTER);
                instituto.setBorderWidthTop(0); 
                instituto.setBorderWidthBottom(0);    
                table2.addCell(instituto);
                
                PdfPCell campus = new PdfPCell(new Phrase("Campus Urutaí",f3));
                campus.setHorizontalAlignment(Element.ALIGN_CENTER);
                campus.setBorderWidthTop(0); 
                campus.setBorderWidthBottom(0);    
                table2.addCell(campus);
                
                PdfPCell direcao = new PdfPCell(new Phrase("Direção de Extensão",f3));
                direcao.setHorizontalAlignment(Element.ALIGN_CENTER);
                direcao.setBorderWidthTop(0); 
                direcao.setBorderWidthBottom(0);    
                table2.addCell(direcao);
                
                PdfPCell endereco = new PdfPCell(new Phrase("Rod. Geraldo Silva Nascimento, Km 2,5, Zona Rural, Urutaí-GO - CEP:75.790-000. Telefone: (64) 3465-1900",f4));
                endereco.setHorizontalAlignment(Element.ALIGN_CENTER);
                endereco.setBorderWidthTop(0); 
                endereco.setBorderWidthBottom(0);    
                table2.addCell(endereco);
                
                PdfPCell cell2 = new PdfPCell(new Phrase("Certificado gerado pelo sistema SIGEX e registrado nos arquivos da DIREX sob nº " + n_registro + " na página nº " + n_pagina + " do livro nº " + n_livro + ".",f4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(15);
                cell2.setBorderWidthTop(0); 
                cell2.setBorderWidthBottom(0);    
                table2.addCell(cell2);
                
                PdfPCell data = new PdfPCell(new Phrase(getData(),f4));
                data.setHorizontalAlignment(Element.ALIGN_CENTER);
                data.setPaddingTop(35);
                data.setPaddingBottom(60);
                data.setBorderWidthTop(0);                 
                data.setBorderWidthBottom(0);                 
                table2.addCell(data);
                
                PdfPCell nome = new PdfPCell(new Phrase("Rafael Vasconcelos de Oliveira",f4));
                nome.setHorizontalAlignment(Element.ALIGN_CENTER);
                nome.setBorderWidthTop(0);                  
                nome.setBorderWidthBottom(0);                    
                table2.addCell(nome);
                
                PdfPCell cargo = new PdfPCell(new Phrase("Secretário da DIREX",f4));
                cargo.setHorizontalAlignment(Element.ALIGN_CENTER);
                cargo.setPaddingBottom(20);
                cargo.setBorderWidthTop(0);                
                table2.addCell(cargo);
              
                PdfContentByte canvas = writer.getDirectContentUnder();
                img.scaleAbsolute(PageSize.A4.rotate());
                img.setAbsolutePosition(0,0);
                canvas.addImage(img);   
                
                document.add(paragrafo1);
                document.add(table);
                document.add(table2);
                
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Certificado gerado");
                //setOcorrencias(linhaProjeto, nomeParticipante + " (Aluno)");
                setRegistroDigital(nomeEvento,nomeParticipante, emailParticipante, pastaEvento,n_livro, n_pagina, n_registro);
               // setCertificadoAluno(pastaEvento);
                Desktop.getDesktop().open(new File(diretorio + "\\" + n_registro + "-"  + nomeParticipante + ".pdf"));
        } 
        catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        } catch (IOException ex) {
            Logger.getLogger(CertificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            document.close();
        }
    }
    
    public void geraCertificadoParticipanteTransparente(String linhaProjeto, String n_livro, String n_pagina, String n_registro)
    {
        EventoDAO evento = new EventoDAO();
        RegistroDAO r = new RegistroDAO();
        Document document = new Document(PageSize.A4.rotate());        
        Font f = new  Font(Font.getFamily("Calibri"), 72, Font.NORMAL);
        Font f2 = new Font(Font.getFamily("Calibri"), 10, Font.BOLD);
        Font f3 = new Font(Font.getFamily("Calibri"), 12, Font.BOLD);
        Font f4 = new Font(Font.getFamily("Calibri"), 12);
            
        int rowEvento     = Main.jTableEventos.getSelectedRow();
        String nomeEvento = Main.jTableEventos.getValueAt(rowEvento, 1 ).toString();
        String tipoEvento = Main.jTableEventos.getValueAt(rowEvento, 2 ).toString();
        String dataEvento = Main.jTableEventos.getValueAt(rowEvento, 3 ).toString();
        String chEvento   = Main.jTableEventos.getValueAt(rowEvento, 4 ).toString();
        String localEvento   = Main.jTableEventos.getValueAt(rowEvento, 7 ).toString(); 
        
        String pastaEvento  = Main.jTableEventos.getValueAt(rowEvento, 0 ).toString();
        
        int rowParticipante      = Participantes.jTableParticipantes.getSelectedRow();             
        String nomeParticipante  = Participantes.jTableParticipantes.getValueAt(rowParticipante, 1 ).toString();
        String emailParticipante = Participantes.jTableParticipantes.getValueAt(rowParticipante, 2 ).toString();
        String texto = evento.getModelo(Participantes.jTableParticipantes.getValueAt(rowParticipante, 4 ).toString());
              
        
           try 
            {
                
                File diretorio = new File(Usuario.getSalvar_em() + "\\" + nomeEvento);
                    //JOptionPane.showMessageDialog(null, linhaProjeto);
                if (!diretorio.exists())
                     diretorio.mkdirs();
                
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(  diretorio + "\\"  + n_registro + "-" + nomeParticipante + ".pdf"));
                document.open();
                URL certificado = getClass().getResource("/images/certificado.png");
                Image img = Image.getInstance(certificado);
                
                float[] columnWidths = {255};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(95);
                
                PdfPTable table2 = new PdfPTable(columnWidths);
                table2.setWidthPercentage(90);
                table2.setSpacingBefore(100);
                
                
                Paragraph paragrafo1 = new Paragraph(" ", f);
                paragrafo1.setLeading(175f);
                
                PdfPCell cell = new PdfPCell();                
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setFixedHeight(332f);
                texto = texto.replace("%nomeParticipante%", nomeParticipante);
                texto = texto.replace("%nomeEvento%", nomeEvento); 
                texto = texto.replace("%tipoEvento%", tipoEvento); 
                texto = texto.replace("%chEvento%", chEvento); 
                texto = texto.replace("%dataEvento%", dataEvento); 
                texto = texto.replace("%localEvento%", localEvento); 
                
                
                String HTML = "<p class=\"texto\">" + texto + "." + "<br></br><br></br><br></br><br></br></p><p class=\"data\">" + getData() + "</p>";
                String CSS = "p.texto{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align: justify;}p.data{ font-family: Arial; line-height: 1.5; font-size: 24px; text-align:right;}";
            
                ElementList list = XMLWorkerHelper.parseToElementList(HTML,CSS);            
                list.forEach((element) -> 
                {
                    cell.addElement(element);
                });
                table.addCell(cell);
                
                PdfPCell cellMinisterio = new PdfPCell(new Phrase("Ministério da Educação",f3));
                cellMinisterio.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cellMinisterio.setBorder(Rectangle.NO_BORDER);
                cellMinisterio.setBorderWidthBottom(0);   
                table2.addCell(cellMinisterio);
                
                PdfPCell instituto = new PdfPCell(new Phrase("Instituto Federal de Educação, Ciência e Tecnologia Goiano",f3));
                instituto.setHorizontalAlignment(Element.ALIGN_CENTER);
                instituto.setBorderWidthTop(0); 
                instituto.setBorderWidthBottom(0);    
                table2.addCell(instituto);
                
                PdfPCell campus = new PdfPCell(new Phrase("Campus Urutaí",f3));
                campus.setHorizontalAlignment(Element.ALIGN_CENTER);
                campus.setBorderWidthTop(0); 
                campus.setBorderWidthBottom(0);    
                table2.addCell(campus);
                
                PdfPCell direcao = new PdfPCell(new Phrase("Direção de Extensão",f3));
                direcao.setHorizontalAlignment(Element.ALIGN_CENTER);
                direcao.setBorderWidthTop(0); 
                direcao.setBorderWidthBottom(0);    
                table2.addCell(direcao);
                
                PdfPCell endereco = new PdfPCell(new Phrase("Rod. Geraldo Silva Nascimento, Km 2,5, Zona Rural, Urutaí-GO - CEP:75.790-000. Telefone: (64) 3465-1900",f4));
                endereco.setHorizontalAlignment(Element.ALIGN_CENTER);
                endereco.setBorderWidthTop(0); 
                endereco.setBorderWidthBottom(0);    
                table2.addCell(endereco);
                
                PdfPCell cell2 = new PdfPCell(new Phrase("Certificado gerado pelo sistema SIGEX e registrado nos arquivos da DIREX sob nº " + n_registro + " na página nº " + n_pagina + " do livro nº " + n_livro + ".",f4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setPadding(15);
                cell2.setBorderWidthTop(0); 
                cell2.setBorderWidthBottom(0);    
                table2.addCell(cell2);
                
                PdfPCell data = new PdfPCell(new Phrase(getData(),f4));
                data.setHorizontalAlignment(Element.ALIGN_CENTER);
                data.setPaddingTop(35);
                data.setPaddingBottom(60);
                data.setBorderWidthTop(0);                 
                data.setBorderWidthBottom(0);                 
                table2.addCell(data);
                
                PdfPCell nome = new PdfPCell(new Phrase("Rafael Vasconcelos de Oliveira",f4));
                nome.setHorizontalAlignment(Element.ALIGN_CENTER);
                nome.setBorderWidthTop(0);                  
                nome.setBorderWidthBottom(0);                    
                table2.addCell(nome);
                
                PdfPCell cargo = new PdfPCell(new Phrase("Secretário da DIREX",f4));
                cargo.setHorizontalAlignment(Element.ALIGN_CENTER);
                cargo.setPaddingBottom(20);
                cargo.setBorderWidthTop(0);                
                table2.addCell(cargo);
              
                PdfContentByte canvas = writer.getDirectContentUnder();
                img.scaleAbsolute(PageSize.A4.rotate());
                img.setAbsolutePosition(0,0);
                //canvas.addImage(img);   
                
                document.add(paragrafo1);
                document.add(table);
                document.add(table2);
                
                Main.labelMensagem.setVisible(true);
                Main.labelMensagem.setBackground(new Color(0,204,51));
                Main.labelMensagem.setText("Certificado gerado");
                //setOcorrencias(linhaProjeto, nomeParticipante + " (Aluno)");
                setRegistroDigital(nomeEvento,nomeParticipante, emailParticipante, pastaEvento, n_livro, n_pagina, n_registro);
               // setCertificadoAluno(pastaEvento);
                Desktop.getDesktop().open(new File(diretorio + "\\" + n_registro + "-"  + nomeParticipante + ".pdf"));
        } 
        catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Defina um modelo para o certificado para gerar");
        }
        finally
        {
            document.close();
        }
    }
    
    public void setOcorrencias(String linhaProjeto, String nome)
    {
        try{
    
            Date data =  new Date();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data_atual = formato.format(data);
            
            instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
            instrucao.setString (2, "CONFECÇÃO");
            instrucao.setString (3, "Certificado gerado para " + nome + ".");            
            instrucao.setString (4, data_atual);
            instrucao.execute();  
        
            }
            catch(SQLException ex)
            {
                if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, ex);                
                }  
            }
    }
    
    public void setOcorrenciasCertificado(String linhaProjeto, String nome, String email)
    {
        try{
            
            instrucao = conexao.prepareStatement("SELECT aluno1, aluno2, nome_orientador from projeto_view where id_projeto = " + linhaProjeto);
            resultado = instrucao.executeQuery();
            resultado.first();
            
            if(nome.equals(resultado.getString("aluno1")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_enviado1 = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();
            }else if(nome.equals(resultado.getString("aluno2")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_enviado2 = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();                
            }else if(nome.equals(resultado.getString("nome_orientador")))
            {
                instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_orientador = 2 where id_projeto = " + linhaProjeto);//isso so funciona pra varchar
                instrucao.execute();                
            } 
            
            Date data =  new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data_atual = formato.format(data);
            
            instrucao = conexao.prepareStatement("INSERT INTO ocorrencias (projeto_id,tipo_ocorrencia,descricao_ocorrencia,data_ocorrencia) VALUES (?,?,?,?)");//isso so funciona pra varchar
            
            instrucao.setInt    (1, Integer.parseInt(linhaProjeto));
            instrucao.setString (2, "ENVIO");
            instrucao.setString (3, "Certificado enviado para " + nome + " em " + email);            
            instrucao.setString (4, data_atual);
            instrucao.execute();  
        
            }
            catch(SQLException e)
            {
                if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
            }
    }
    
    public int populaNRegistro(String n_livro,String n_pagina)
    {
      try
        {
            instrucao = conexao.prepareStatement("SELECT MAX(n_registro) AS maiorRegistro FROM registro WHERE n_livro = '" + n_livro + "' and n_pagina = " + n_pagina);
            resultado = instrucao.executeQuery();
            resultado.first();
            int nregistropopula = resultado.getInt("maiorRegistro");                       
            return nregistropopula + 1;           
            
        }
        catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
            return 0;
        }
    }
    
    public void setRegistroDigital(String nomeEvento,String nome, String email, String pastaEvento, String n_livro, String n_pagina, String n_registro)
    {   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
           RegistroDAO registro = new RegistroDAO();
           Date date = new Date();           
        try
       {
            instrucao = conexao.prepareStatement("INSERT INTO registro (n_registro,n_livro,n_pagina,nome,descricao,data_registro,email,data_entrega,situacao,n_documento) VALUES (?,?,?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setString (1, n_registro);
            instrucao.setString (2, n_livro);
            instrucao.setString (3, n_pagina);
            instrucao.setString (4, nome);
            instrucao.setString (5, nomeEvento);
            instrucao.setString (6,String.valueOf(dateFormat.format(date)));
            instrucao.setString (7, email);
            instrucao.setString (8, null);
            instrucao.setString (9, null);
            instrucao.setString (10, pastaEvento);
            instrucao.execute();
            
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Numero de registro já existente");
        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public void setCertificadoAluno(String idProjeto)
    {
        try
       {            
            instrucao = conexao.prepareStatement("UPDATE projeto SET = 1 where id_projeto = " + idProjeto);//isso so funciona pra varchar
            instrucao.execute();
            
            //AlunoDAO a = new AlunoDAO();
           // a.getAluno("");
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Erro cerficado 1" + ex);

        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public void setCertificadoOrientador(String idProjeto)
    {
        try
       {            
            instrucao = conexao.prepareStatement("UPDATE projeto SET certificado_orientador = 1 where id_projeto = " + idProjeto);//isso so funciona pra varchar
            instrucao.execute();
            
            //OrientadorDAO o = new OrientadorDAO();
           // o.getOrientador("");
        }
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Erro cerficado 1" + ex);

        }
        catch( SQLException e )
        {
             if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
                {              
                    JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                    System.exit(0);
                }else
                {
                    JOptionPane.showMessageDialog(null, e);                
                }  
        }
    }
    
    public String getData()
    {
        Date data =  new Date();
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd");
        String dia = formatoDia.format(data);
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
        String mes = formatoMes.format(data);
        String mesString = mes;
        switch (mes) 
        {
            case "01":
                mesString = "janeiro";
                break;
            case "02":
                mesString = "fevereiro";
                break;
            case "03":
                mesString = "março";
                break;
            case "04":
                mesString = "abril";
                break;
            case "05":
                mesString = "maio";
                break;
            case "06":
                mesString = "junho";
                break;
            case "07":
                mesString = "julho";
                break;
            case "08":
                mesString = "agosto";
                break;
            case "09":
                mesString = "setembro";
                break;
            case "10":
                mesString = "outubro";
                break;
            case "11":
                mesString = "novembro";
                break;
            case "12":
                mesString = "dezembro";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Mês inválido");
                break;
        }
        SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
        String ano = formatoAno.format(data);
        
        return "Urutaí - GO, " + dia + " de " + mesString + " de " + ano + ".";
    }
}
    

