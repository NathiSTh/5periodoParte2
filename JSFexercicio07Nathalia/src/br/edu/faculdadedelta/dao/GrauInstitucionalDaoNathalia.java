package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.GrauInstrucaoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class GrauInstitucionalDaoNathalia {
	
	public void incluir (GrauInstrucaoNathalia GrauInstrucao) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO graudeinstrucao (nome) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, GrauInstrucao.getGrau().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar (GrauInstrucaoNathalia GrauInstrucao) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE graudeinstrucao SET nome=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, GrauInstrucao.getGrau().trim());
			ps.setLong(2, GrauInstrucao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir (GrauInstrucaoNathalia GrauInstrucao) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM graudeinstrucao WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, GrauInstrucao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<GrauInstrucaoNathalia> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM graudeinstrucao";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<GrauInstrucaoNathalia> listaRetorno = new ArrayList<>();
		
		try {
			while(rs.next()){
				GrauInstrucaoNathalia grau = new GrauInstrucaoNathalia();
				grau.setId(rs.getLong("id"));
				grau.setGrau(rs.getString("nome").trim());
				listaRetorno.add(grau);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public GrauInstrucaoNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM graudeinstrucao WHERE id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		GrauInstrucaoNathalia retorno = new GrauInstrucaoNathalia();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setGrau(rs.getString("nome").trim());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
	}
}
