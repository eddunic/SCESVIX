package scesvi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import scesvi.model.Servidor;

public class DAOServidor extends DAO {
	
	private static Servidor servidor;
	
	public static Servidor getServidor() {
		return servidor;
	}
	
	public static void insert(Servidor servidor) {
		String query = "INSERT INTO SERVIDOR VALUES(?,?,?,?,?,?,?,?)";
		try (PreparedStatement pst = getConnection().prepareStatement(query)) {
			pst.setString(1, servidor.getSiape());
			pst.setString(2, servidor.getCpf());
			pst.setString(3, servidor.getNome());
			pst.setString(4, servidor.getSenha());
			pst.setString(5, servidor.getDataNascS());
			pst.setString(6, servidor.getCnh());
			pst.setString(7, servidor.getCategoria());
			pst.setString(8, servidor.getAutorizadoVeicInstitucional());

			pst.executeUpdate();
			pst.close();
			disconnection();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public static void delete(String siape) {
		String query = "DELETE FROM SERVIDOR WHERE siape = " + siape;
		try (PreparedStatement pst = getConnection().prepareStatement(query)) {
			pst.executeUpdate(query);
			pst.close();
			disconnection();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public static void consultServidor(String siape) {
		String query = "SELECT * FROM SERVIDOR WHERE siape = " + siape;
		try (PreparedStatement pst = getConnection().prepareStatement(query)) {
			ResultSet resultset = pst.executeQuery(query);
			while(resultset.next()) {
				System.out.println(resultset.getString("nome"));
				System.out.println(resultset.getString("senha"));
			}

			pst.close();
			disconnection();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public static void list() {
		String query = "SELECT * FROM SERVIDOR";
		try (PreparedStatement pst = getConnection().prepareStatement(query)) {
			ResultSet resultset = pst.executeQuery(query);
			while(resultset.next()) {
				System.out.println(resultset.getString("nome"));
			}

			pst.close();
			disconnection();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
	public static void listNomeDepCargo() {
		String query = "SELECT SERVIDOR.nome, DEPARTAMENTO.sigla, CARGO.titulacao\r\n" + 
					   "FROM SERVIDOR INNER JOIN LOTADO\r\n" + 
					   "ON SERVIDOR.siape = LOTADO.siapeServ\r\n" + 
					   "INNER JOIN DEPARTAMENTO\r\n" + 
					   "ON DEPARTAMENTO.codigo = LOTADO.codDep\r\n" + 
					   "INNER JOIN CONTRATADO\r\n" + 
					   "ON SERVIDOR.siape = CONTRATADO.siapeServ\r\n" + 
					   "INNER JOIN CARGO\r\n" + 
					   "ON CARGO.codigo = CONTRATADO.codCargo\r\n" + 
					   "GROUP BY SERVIDOR.nome;";
		try (PreparedStatement pst = getConnection().prepareStatement(query)) {
			ResultSet resultset = pst.executeQuery(query);
			while(resultset.next()) {
				System.out.println(resultset.getString("SERVIDOR.nome"));
				System.out.println(resultset.getString("DEPARTAMENTO.sigla"));
				System.out.println(resultset.getString("CARGO.titulacao"));
			}

			pst.close();
			disconnection();
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
	}
	
}
