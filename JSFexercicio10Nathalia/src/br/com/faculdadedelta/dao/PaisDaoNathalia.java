package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.PaisNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class PaisDaoNathalia {
	
	public void incluir(PaisNathalia pais) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO pais(nome_pais,codigo_pais)VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1,pais.getNome().trim());
			ps.setString(2, pais.getCodigo().trim());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
		
	}

	public void alterar(PaisNathalia pais) throws Exception {
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE pais SET nome_pais=?, codigo_pais=? WHERE id_pais=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1,pais.getNome().trim());
			ps.setString(2,pais.getCodigo().trim());
			ps.setLong(3,pais.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
		
	}
	
	public void excluir(PaisNathalia pais) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM  pais WHERE id_pais=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, pais.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<PaisNathalia> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
	String sql = " SELECT * FROM pais ";
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs= ps.executeQuery();
	List<PaisNathalia> listaRetorno = new ArrayList<>();
		try {
			while(rs.next()) {
				PaisNathalia pais = new PaisNathalia();
				pais.setId(rs.getLong("id_pais"));
				pais.setNome(rs.getString("nome_pais").trim());
				pais.setCodigo(rs.getString("codigo_pais").trim());
				listaRetorno.add(pais);
			}
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	}finally {
		Conexao.fecharConexao(conn, ps, rs);
	}
	
		
		return listaRetorno;
		
	}
	
	public PaisNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM pais WHERE id_pais=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		PaisNathalia retorno = new PaisNathalia();	
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				retorno.setId(rs.getLong("id_pais"));
				retorno.setNome(rs.getString("nome_pais").trim());
				retorno.setCodigo(rs.getString("codigo_pais").trim());
	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		return retorno;
		
	}

}
