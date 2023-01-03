package bean;

import jakarta.faces.bean.ManagedBean;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

@ManagedBean
public class user {
    String nome;
    String email;
    
    public user(){
        
    }
    
    public boolean salvar(){
        int result = 0;
        
        try{
        
            //Configurações
            String driver = "org.postgresql.Driver";
            String user = "postgres";
            String senha = "123";
            String url = "jdbc:postgresql://localhost:5432/AulaJava";            
            
            // Conexao
            Class.forName(driver);               
            Connection con = null;                  
            con = (Connection) DriverManager.getConnection(url, user, senha);                  
            System.out.println("Conexão realizada com sucesso.");   
            
            PreparedStatement stmt = con.prepareStatement("insert into usuario(nome,email) values (?,?)");
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getEmail());
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("e");
            System.err.print(e.getMessage());
        }
        if(result ==1){
            return true;
        } else {
            return false;
        }
    }
    
    public String submit(){
        if (this.salvar())
            return "resposta.xhtml";
        else
            return "index.xhtml";
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
