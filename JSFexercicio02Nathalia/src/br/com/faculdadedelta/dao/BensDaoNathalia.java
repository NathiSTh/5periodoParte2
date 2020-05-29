package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BensNathalia;
import br.com.faculdadedelta.modelo.DepartamentoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class BensDaoNathalia {

	public void incluir(BensNathalia bens) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO bens (nome_bem, especificacao_bem, id_departamento, valor_bem) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, bens.getNome().trim().trim());
			ps.setString(2, bens.getEspecificacao().trim());
			ps.setLong(3, bens.getDepartamento().getId());
			ps.setDouble(4, bens.getValor());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			conn.close();
		}
	}

	public void alterar(BensNathalia bens) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bens SET nome_bem=?, especificacao_bem=?, id_departamento=?, valor_bem=? WHERE id_bem=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, bens.getNome().trim());
			ps.setString(2, bens.getEspecificacao().trim());
			ps.setLong(3, bens.getDepartamento().getId());
			ps.setDouble(4, bens.getValor());
			ps.setLong(5, bens.getId());

			ps.executeUpdate();
			
		} catch (Exception e) {
		
		} finally {
			
			ps.close();
			conn.close();
		}
	}
	
	public void exluir(BensNathalia bens) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM bens WHERE id_bem = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, bens.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			
			ps.close();
			conn.close();
		}
	}
	
	public List<BensNathalia> listar() throws ClassNotFoundException, SQLException{
		
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT \r\n" + 
				"b.id_bem AS idBem,   \r\n" + 
				"b.nome_bem AS nomeBem,    \r\n" + 
				"b.especificacao_bem AS espcBem,   \r\n" + 
				"b.valor_bem AS valorBem, \r\n" + 
				"d.id_departamento AS idDepartamento,   \r\n" + 
				"d.desc_departamento AS descDepartamento \r\n" + 
				"FROM bens b\r\n" + 
				"INNER JOIN departamentos d ON  b.id_bem = d.id_departamento";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<BensNathalia> listaRetorno = new ArrayList<>();
		
		try {
			while(rs.next()) {
				BensNathalia bens = new BensNathalia();
				
				bens.setId(rs.getLong("idBem"));
				bens.setEspecificacao(rs.getString("espcBem").trim());
				bens.setNome(rs.getString("nomeBem").trim());
				bens.setValor(rs.getDouble("valorBem"));
				
				
				DepartamentoNathalia departamento = new DepartamentoNathalia();
				
				departamento.setId(rs.getLong("idDepartamento"));
				departamento.setDepartemanto(rs.getString("descDepartamento").trim());
				
				bens.setDepartamento(departamento);
				listaRetorno.add(bens);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			conn.close();
		}
		
		
		
		return listaRetorno;
	}
}
