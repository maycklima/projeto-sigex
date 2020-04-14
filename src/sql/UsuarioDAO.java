package sql;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import static db.DAO.conexao;
import static db.DAO.instrucao;
import static db.DAO.resultado;
import interfaces.Login;
import interfaces.Main;
import interfaces.RecuperarSenha;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.LerArquivo;
import model.Usuario;
/**
 *
 * @author Karol
 */
public class UsuarioDAO 
{
    Usuario u = new Usuario();
    LerArquivo ler = new LerArquivo(); 
    
    
    public void verificarLogin(String usuario, String senha, Boolean lembrar) throws IOException
    {
        try
        {
            instrucao = conexao.prepareStatement( "SELECT * FROM contas WHERE usuario = '" + usuario + "' and senha = '" + senha + "'"  );
            resultado  = instrucao.executeQuery();
            
            
            if(resultado.next()) // Next retorna 0 ou 1 como resultado da consuta
            {
                 Usuario.setId(resultado.getInt("id_conta"));                 
                 Usuario.setUsuario(resultado.getString("usuario"));
                 Usuario.setSalvar_em(resultado.getString("salvar_em"));
                 
                 
                 if(lembrar)
                 {
                    instrucao = conexao.prepareStatement("UPDATE contas SET lembrar = 1 WHERE usuario = '" + usuario + "'");
                    instrucao.executeUpdate();
                    ler.gravarUsuario(usuario);                   
                 }else{
                     ler.gravarUsuario("");
                 }
                 
                 Main main = new Main();
                 main.setExtendedState(Main.MAXIMIZED_BOTH);
                 main.setVisible(true);
            }                    
            else 
            {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos, tente novamente!");
                Login l = new Login();
                l.setVisible(true);
            }
            
            
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
    }
    public void inserirUsuario(Usuario usuario)
    {
        try
        {
            instrucao = conexao.prepareStatement("INSERT INTO contas(usuario,senha,cpf) VALUES(?,?,?)");//isso so funciona pra varchar
            instrucao.setNString( 1, Usuario.getUsuario());
            instrucao.setNString( 2, Usuario.getSenha());
            instrucao.setNString( 3, Usuario.getCpf());
            instrucao.executeUpdate();
            
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Main.labelMensagem.setText("Usuário inserido com sucesso");
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
        }
    }
    public void atualizarUsuario(Usuario usuario)
    {
        try
        {
            instrucao = conexao.prepareStatement("UPDATE contas set usuario = ?, cpf = ?, senha = ? where id_conta =" + Usuario.getId());
            instrucao.setNString(1, Usuario.getUsuario() );
            instrucao.setNString(2, Usuario.getCpf() );
            instrucao.setNString(3, Usuario.getSenha() );

            instrucao.execute();
            
            Main.labelMensagem.setVisible(true);
            Main.labelMensagem.setBackground(new Color(0,204,51));
            Main.labelMensagem.setText("Usuário alterado com sucesso");
       }        
        catch( MySQLIntegrityConstraintViolationException ex )
        {
             JOptionPane.showMessageDialog(null, "Nome do usuario ou CPF já existente.");
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
    public void recuperarSenha()
    {
        
        try 
        {
            instrucao = conexao.prepareStatement( "SELECT * FROM contas WHERE usuario = '" + RecuperarSenha.txtUsuario.getText() + "' and cpf = '" + RecuperarSenha.txtCpf.getText() + "'");
            resultado = instrucao.executeQuery();
            
            if( resultado.next() )//next retorna um boolean 1 se achou um usuarios 0 se nao achou 
            {
                try
                {
                
                    instrucao = conexao.prepareStatement("UPDATE contas set usuario = ?, cpf = ?, senha = ? where usuario = '" + RecuperarSenha.txtUsuario.getText() + "' and cpf = '" + RecuperarSenha.txtCpf.getText() + "'");
                    instrucao.setNString(1, Usuario.getUsuario() );
                    instrucao.setNString(2, Usuario.getCpf() );
                    instrucao.setNString(3, Usuario.getSenha() );
                    instrucao.executeUpdate();
                    
                    Login.msgConexao.setVisible(true);
                    Login.msgConexao.setBackground(new Color(0,204,51));
                    Login.msgConexao.setText("Senha recuperada com sucesso!"); 
                }                
                catch( MySQLIntegrityConstraintViolationException ex )
                {
                     JOptionPane.showMessageDialog(null, "Nome do usuario ou CPF já existente.");
                }
                catch( SQLException e)
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
            else
            {
                JOptionPane.showMessageDialog(null, "Não foi encontrado nenhuma conta com esse Usuário e CPF");
            }
        }
        catch( SQLException e) 
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
    public Usuario getRecuperar()
        {
        try 
        {
            instrucao = conexao.prepareStatement( "SELECT * FROM contas WHERE id_conta = " + Usuario.getId() );
            resultado = instrucao.executeQuery();
            
            if( resultado.next() )//next retorna um boolean 1 se achou um usuarios 0 se nao achou 
            {
               // Usuario u = new Usuario();// entre aspas e o nome das colunas no banco
                Usuario.setUsuario(resultado.getString( "usuario" ));
                Usuario.setCpf(resultado.getString( "cpf" )); 
                Usuario.setSenha(resultado.getString( "senha" )); 
                                        
            }
            else
            {
                System.out.println("Não Foi Encontrado Nenhum Usuário!");
            }
        }
        catch( SQLException e) 
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
        
        return u;
    }
    public void deletarUsuario()
    {
        try
        {
            instrucao = conexao.prepareStatement( "DELETE FROM contas WHERE id_conta = " + Usuario.getId() );
            instrucao.execute();
            
           // Main.labelMensagem.setVisible(true);
           // Main.labelMensagem.setBackground(new Color(0,204,51));
           // Main.labelMensagem.setText("Usuário deletado");
            System.exit(0);
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
