/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.Main;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import model.Evento;
import model.ModeloTabela;

/**
 *
 * @author mayck
 */
public class EventoDAO 
{
    
    public void getEvento(String evento) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        query = "SELECT id_evento,local_evento,tipo_evento,nome_evento,ch_evento,data_evento,modelo_certificado,ano_evento, (SELECT COUNT(id_participante) from participante WHERE evento_id = id_evento) AS qnt_participantes FROM evento WHERE evento_excluido = 0";
        
        String[] Colunas = new String[]{"ID","Nome","Tipo","Data","Carga Horária","Nº Participantes","Modelo Certificado","Local","Ano"};
        try 
        {
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();        
            
            if(resultado.first())
            {
                do 
                {                 
                    dados.add(new Object[]
                    {                    
                        resultado.getInt("id_evento"),
                        resultado.getString("nome_evento"),
                        resultado.getString("tipo_evento"),
                        resultado.getString("data_evento"),                          
                        resultado.getInt("ch_evento"),                        
                        resultado.getInt("qnt_participantes"),
                        resultado.getString("modelo_certificado"), 
                        resultado.getString("local_evento"),
                        resultado.getString("ano_evento")
                    });  
                } 
                while(resultado.next());
            }else
            { 
            }
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }   
        }
       

        JTableHeader cabecalho = Main.jTableEventos.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Main.jTableEventos.setModel(modelo);
        Main.jTableEventos.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableEventos.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Main.jTableEventos.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Main.jTableEventos.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Main.jTableEventos.getColumnModel().getColumn(1).setPreferredWidth(200);
        Main.jTableEventos.getColumnModel().getColumn(2).setPreferredWidth(100);
        Main.jTableEventos.getColumnModel().getColumn(3).setPreferredWidth(150);
        Main.jTableEventos.getColumnModel().getColumn(4).setPreferredWidth(80);
        Main.jTableEventos.getColumnModel().getColumn(5).setPreferredWidth(90);
        Main.jTableEventos.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableEventos.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
        Main.jTableEventos.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
        Main.jTableEventos.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
        Main.jTableEventos.getTableHeader().setReorderingAllowed(false);   
        Main.jTableEventos.getColumnModel().getColumn(7).setPreferredWidth(200);
        Main.jTableEventos.getColumnModel().getColumn(8).setPreferredWidth(30);
        Main.jTableEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Main.jTableEventos.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public String getModelo(String idEvento) 
    {
        String query;
        String modelo = "";
        
        query = "SELECT id_evento,tipo_evento,nome_evento,ch_evento,data_evento,modelo_certificado, (SELECT COUNT(id_participante) from participante WHERE evento_id = id_evento) AS qnt_participantes FROM evento WHERE evento_excluido = 0 and id_evento = " + idEvento;
        
        try 
        {
            instrucao = conexao.prepareStatement(query);
            resultado = instrucao.executeQuery();        
            
            if(resultado.first())
            {                  
               modelo = resultado.getString("modelo_certificado");                  
            }else
            { 
            }
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e){
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
                JOptionPane.showMessageDialog(null, e);                
            }   
        }
        
        return modelo;
        
    }
    public Boolean inserirEvento(Evento evento)
    {
        try
        {
            instrucao = conexao.prepareStatement("INSERT INTO evento(nome_evento,ch_evento,data_evento,tipo_evento,local_evento,ano_evento) VALUES(?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setNString( 1, evento.getNomeEvento());
            instrucao.setNString( 2, evento.getChEvento());
            instrucao.setNString( 3, evento.getDataEvento());
            instrucao.setNString( 4, evento.getTipoEvento());
            instrucao.setNString( 5, evento.getLocalEvento());
            instrucao.setInt( 6, evento.getAnoEvento());
            instrucao.execute();
            
            Main.labelMessage.setVisible(true);
            Main.labelMessage.setBackground(new Color(0,204,51));
            Main.labelMessage.setText("Evento inserido com sucesso");
            return true;
        }
        catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
              JOptionPane.showMessageDialog(null, "Usuario com o mesmo nome ou CPF já existente!");
            }else if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               JOptionPane.showMessageDialog(null, e);                
            } 
            return false;
        }
    }
    public Boolean alterarEvento(Evento e, String linhaEvento)
   {   
            try
            {

               instrucao = conexao.prepareStatement("UPDATE evento set nome_evento = ?,tipo_evento = ?,ch_evento = ?, data_evento = ?,local_evento = ?,ano_evento = ? where id_evento = " + linhaEvento);//isso so funciona pra varchar


                instrucao.setString(1, e.getNomeEvento());
                instrucao.setString(2, e.getTipoEvento());
                instrucao.setString(3, e.getChEvento());
                instrucao.setString(4, e.getDataEvento());
                instrucao.setString(5, e.getLocalEvento());
                instrucao.setInt(6, e.getAnoEvento());
                
                instrucao.executeUpdate();                
                instrucao.close();

                Main.labelMessage.setText("Eventos atualizado");
                Main.labelMessage.setVisible(true);
                Main.labelMessage.setBackground(new Color(0,204,51));
                return true;
                
            }catch( SQLException ex )
            {           
                if(ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
                {
                    Main.labelMessage.setVisible(true);
                    Main.labelMessage.setBackground(new Color(230,0,0));
                    Main.labelMessage.setText("Já existente um aluno cadastrado com esse CPF");
                }else{
                    JOptionPane.showMessageDialog(null, ex);                
                }
                return false;
            }
    }
    
    public Boolean deletarEvento(String linhaEvento)
   {
            try
            {
                instrucao = conexao.prepareStatement( "UPDATE evento SET evento_excluido = 1 WHERE id_evento = " + linhaEvento );
                instrucao.execute();
                instrucao.close();
                
                Main.labelMessage.setText("Evento removido");                
                Main.labelMessage.setBackground(new Color(230,0,0));
                Main.labelMessage.setVisible(true);
                return true;
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
                return false;
            }
        
    }
   
   public void deletarPermanente(String linhaAluno)
   {
       if(linhaAluno.equals("0"))
        {
            Main.labelMessage.setVisible(true);
            Main.labelMessage.setBackground(new Color(230,0,0));
            Main.labelMessage.setText("Escolha um evento para deletar");
        }
        else
        {
            try
            {
                instrucao = conexao.prepareStatement( "DELETE FROM aluno WHERE id_aluno = " + linhaAluno );
                instrucao.execute();
                
                 Main.labelMessage.setVisible(true);
                 Main.labelMessage.setBackground(new Color(0,204,51));
                 Main.labelMessage.setText("Aluno deletado permanentemente");
                
                instrucao.close();
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
    }
    
}
