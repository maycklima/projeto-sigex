/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.RegistroDigital;
import model.ModeloTabela;
import model.Registro;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author mayck
 */
public class RegistroDAO 
{
    public void getRegistro(String busca,String n_livro, String n_pagina) 
    {
        ArrayList<Object> dados = new ArrayList<Object>();
        int n_registro;
        
        String[] Colunas = new String[]{"ID_Registro","Nº Registro","Participante","Descrição","Registrado","Email","Data Envio","Situação","Nº Doc"};
        try 
        {
            instrucao = conexao.prepareStatement("Select * from registro where n_livro = " + n_livro + " and n_pagina = " + n_pagina + " and nome like '%" + busca + "%' order by n_registro desc");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            do 
            {   
                n_registro = resultado.getInt("n_registro");
                dados.add(new Object[]
                {                    
                    resultado.getString("id_registro"),
                    String.format("%06d", n_registro),
                    resultado.getString("nome"),
                    resultado.getString("descricao"),
                    resultado.getString("data_registro"),
                    resultado.getString("email"),                    
                    resultado.getString("data_entrega"),
                    resultado.getString("situacao"),                  
                    resultado.getString("n_documento"),                  
                });

            } 
            while(resultado.next());
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
        {
          if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }        
        JTableHeader cabecalho = RegistroDigital.jTableRegistroDigital.getTableHeader();  
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        ModeloTabela modelo = new ModeloTabela(dados,Colunas);
        
        RegistroDigital.jTableRegistroDigital.setModel(modelo);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        RegistroDigital.jTableRegistroDigital.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        RegistroDigital.jTableRegistroDigital.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(1).setPreferredWidth(40);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(2).setPreferredWidth(200);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(3).setPreferredWidth(210);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(4).setPreferredWidth(35);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(5).setPreferredWidth(150);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(6).setPreferredWidth(40);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(7).setPreferredWidth(40);
        RegistroDigital.jTableRegistroDigital.getColumnModel().getColumn(8).setPreferredWidth(20);
        
        RegistroDigital.jTableRegistroDigital.getTableHeader().setReorderingAllowed(false);   
        RegistroDigital.jTableRegistroDigital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        RegistroDigital.jTableRegistroDigital.setRowHeight(20);  
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public String getMaiorLivro() 
    {
        String n_livro = "0";
        
        try 
        {
            instrucao = conexao.prepareStatement("SELECT MAX(n_livro) as maiorLivro from registro");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            n_livro = resultado.getString("maiorLivro"); 
        } 
        catch (SQLException e)
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
        return n_livro;        
   }
    public String getMaiorPagina(String n_livro) 
    {
        String n_pagina = "0";
        
        try 
        {
            instrucao = conexao.prepareStatement("SELECT MAX(n_pagina) AS maiorPagina FROM registro WHERE n_livro = '" + n_livro+ "'");
            resultado = instrucao.executeQuery();
            resultado.first();
            
            n_pagina = resultado.getString("maiorPagina");                   
           
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
        {
          if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }   
        return n_pagina;        
   }
    
    public int getMaiorRegistro(String n_livro, String n_pagina) 
    {
        int n_registro = 0;
        
        try 
        {
            instrucao = conexao.prepareStatement("SELECT MAX(n_registro) AS maiorRegistro FROM registro WHERE n_livro = '" + n_livro + "' and n_pagina = " + n_pagina );
            resultado = instrucao.executeQuery();
            resultado.first();
            
            n_registro = resultado.getInt("maiorRegistro");                   
           
            resultado.close();
            instrucao.close();
        } 
        catch (SQLException e)
        {
          if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
        }   
        return n_registro + 1;        
   }
    
    
    public void inserirRegistro(Registro r, String n_livro,String n_pagina)
    {   
       try
       {
            instrucao = conexao.prepareStatement("INSERT INTO registro (n_registro,n_livro,n_pagina,nome,descricao,data_registro,email,data_entrega,situacao,n_documento) VALUES (?,?,?,?,?,?,?,?,?,?)");//isso so funciona pra varchar
            instrucao.setString (1, r.getNregistro());
            instrucao.setString (2, n_livro);
            instrucao.setString (3, n_pagina);
            instrucao.setString (4, r.getNome());
            instrucao.setString (5, r.getDescricao());
            instrucao.setString (6, r.getData_Registro());
            instrucao.setString (7, r.getEmail());
            instrucao.setString (8, null);
            instrucao.setString (9, null);
            instrucao.setInt    (10, r.getNDocumento());
            instrucao.execute();
            
            RegistroDigital.labelMensagem.setVisible(true);
            RegistroDigital.labelMensagem.setText("Registro inserido com sucesso");
            RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));
        }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
                RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Já existe um registro com esse número");
                RegistroDigital.labelMensagem.setBackground(new Color(230,0,0));
            }else if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               JOptionPane.showMessageDialog(null, e);                
            } 
        }
    }
    
    public void atualizarRegistro(Registro registro , String linhaRegistro)
    {        
            try
            {

               instrucao = conexao.prepareStatement("UPDATE registro set n_registro = ?, nome = ?, descricao = ?, data_registro = ?,email = ?, data_entrega = ?,situacao = ? where id_registro = " + linhaRegistro);//isso so funciona pra varchar


                instrucao.setString(1, registro.getNregistro());
                instrucao.setString(2, registro.getNome());
                instrucao.setString(3, registro.getDescricao() );
                instrucao.setString(4, registro.getData_Registro());
                instrucao.setString(5, registro.getEmail());
                instrucao.setString(6, registro.getData_entrega()); 
                if(registro.getSituacao())
                {
                instrucao.setString(7, "ENVIADO");                    
                }else{
                instrucao.setString(7, "");     
                }                
                instrucao.executeUpdate();
                
                instrucao.close();
                
                RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Registro atualizado");                 
                RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));
            }catch( SQLException e )
        {
            if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException)
            {
               RegistroDigital.labelMensagem.setVisible(true);
                RegistroDigital.labelMensagem.setText("Já existe um registro com esse número");
                RegistroDigital.labelMensagem.setBackground(new Color(230,0,0));
            }if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               JOptionPane.showMessageDialog(null, e);                
            } 
        }
    }
    
    public void deletarRegistro(String numeroRegistro)
    {
        try
        {
            instrucao = conexao.prepareStatement( "DELETE FROM registro WHERE id_registro = " + numeroRegistro );
            instrucao.execute();
            
            RegistroDigital.labelMensagem.setVisible(true);
            RegistroDigital.labelMensagem.setText("Registro removido");
            RegistroDigital.labelMensagem.setBackground(new Color(0,204,51));

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
    
    public void atualizaDpsEnvio(String nregistro, String dataEnvio, String situacao)
    {
        try
            {
                instrucao = conexao.prepareStatement("UPDATE registro set data_entrega = ?,situacao = ? where n_registro = " + nregistro);//isso so funciona pra varchar

                instrucao.setString(1, dataEnvio);
                instrucao.setString(2, situacao);                
                instrucao.executeUpdate();                
                instrucao.close();    
            }
            catch (SQLException e)
            {
                if(e instanceof com.mysql.jdbc.exceptions.jdbc4.CommunicationsException)
            {              
                JOptionPane.showMessageDialog(null, "Sua sessão expirou, faça o login novamente!");
                System.exit(0);
            }else
            {
               //JOptionPane.showMessageDialog(null, e);                
            } 
            }
    }
}
