/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.Participantes;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import model.ModeloTabela;
import model.Participante;

/**
 *
 * @author mayck
 */
public class ParticipanteDAO 
{
    public void getParticipantes(String id_evento, String busca) 
   {
        ArrayList<Object> dados = new ArrayList<Object>();
        String query;
        
        query = "SELECT * FROM participante WHERE evento_id = '" + id_evento + "'";
        
        String[] Colunas = new String[]{"ID","Nome","Email","Atualizado","evento_id"};
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
                        resultado.getInt("id_participante"),
                        resultado.getString("nome_participante"),                         
                        resultado.getString("email_participante"),                         
                        resultado.getString("alteracao_participante"),                          
                        resultado.getString("evento_id"),                          
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
       

        JTableHeader cabecalho = Participantes.jTableParticipantes.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        Participantes.jTableParticipantes.setModel(modelo);
        Participantes.jTableParticipantes.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Participantes.jTableParticipantes.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        Participantes.jTableParticipantes.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        Participantes.jTableParticipantes.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        Participantes.jTableParticipantes.getColumnModel().getColumn(1).setPreferredWidth(300);
        Participantes.jTableParticipantes.getColumnModel().getColumn(2).setPreferredWidth(100);
        Participantes.jTableParticipantes.getColumnModel().getColumn(3).setPreferredWidth(100);
        Participantes.jTableParticipantes.getColumnModel().getColumn( 4 ).setMaxWidth( 0 );  
        Participantes.jTableParticipantes.getColumnModel().getColumn( 4 ).setMinWidth( 0 );  
        Participantes.jTableParticipantes.getTableHeader().getColumnModel().getColumn( 4 ).setMaxWidth( 0 );  
        Participantes.jTableParticipantes.getTableHeader().getColumnModel().getColumn( 4 ).setMinWidth( 0 );
        Participantes.jTableParticipantes.getTableHeader().setReorderingAllowed(false);   
        Participantes.jTableParticipantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        Participantes.jTableParticipantes.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public Boolean inserirParticipante(String evento_id, Participante p)
    {
        try
        {
            instrucao = conexao.prepareStatement("INSERT INTO participante(nome_participante,email_participante,alteracao_participante,evento_id) VALUES(?,?,?,?)");//isso so funciona pra varchar
            instrucao.setNString( 1, p.getNomeParticipante());
            instrucao.setNString( 2, p.getEmailParticipante());
            instrucao.setNString( 3, p.getAlteracaoParticipante());
            instrucao.setNString( 4, evento_id);
            instrucao.execute();
            
            Participantes.labelMessage.setBackground(new Color(0,204,51));
            Participantes.labelMessage.setText("Participante inserido com sucesso");
            Participantes.labelMessage.setVisible(true);
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
    
    public Boolean alterarParticipante(Participante p, String linhaParticipante)
   {   
            try
            {

               instrucao = conexao.prepareStatement("UPDATE participante set nome_participante = ?, email_participante = ?, alteracao_participante = ?  where id_participante = " + linhaParticipante);//isso so funciona pra varchar


                instrucao.setString(1, p.getNomeParticipante());
                instrucao.setString(2, p.getEmailParticipante());
                instrucao.setString(3, p.getAlteracaoParticipante());
                
                instrucao.executeUpdate();                
                instrucao.close();

                Participantes.labelMessage.setText("Participante atualizado");
                Participantes.labelMessage.setBackground(new Color(0,204,51));
                Participantes.labelMessage.setVisible(true);
                return true;
                
            }catch( SQLException ex )
            {    
                JOptionPane.showMessageDialog(null, ex);
                return false;
            }
    }
    
     public Boolean deletarParticipante(String linhaParticipante)
   {
       if(linhaParticipante.equals("0"))
        {
            Participantes.labelMessage.setBackground(new Color(230,0,0));
            Participantes.labelMessage.setText("Escolha um participante para deletar");
            Participantes.labelMessage.setVisible(true);
        }else
        {
            try
            {
                instrucao = conexao.prepareStatement( "DELETE FROM participante WHERE id_participante = " + linhaParticipante );
                instrucao.execute();
                
                 Participantes.labelMessage.setBackground(new Color(0,204,51));
                 Participantes.labelMessage.setText("Participante deletado permanentemente");
                 Participantes.labelMessage.setVisible(true);
                 instrucao.close();
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
       return true;      
    }
}
