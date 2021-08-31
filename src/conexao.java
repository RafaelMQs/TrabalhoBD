import java.sql.*;
import javax.swing.*;

public class conexao {
	public Connection conn = null;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String dbname = "joguinhos";
	private final String url = "jdbc:mysql://localhost:3306/"+dbname;
	private final String usu = "root";
	private final String senha = "";
	
	public boolean getConnection(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usu, senha);
			System.out.println("Você esta Conectado");
			return true;
		}catch(ClassNotFoundException error){
			JOptionPane.showMessageDialog(null,"Driver não encontrado\n" + error.toString());
			return false;
		}catch(SQLException error) {
			JOptionPane.showMessageDialog(null,"Problema de conexão com a fonte de dados\n" + error.toString());
			return false;
		}
	}
	
	public void close(){
		try{
			conn.close();
			System.out.println("Desconectou");
		}catch(SQLException erro){
			
		}
	}
	
	
}
