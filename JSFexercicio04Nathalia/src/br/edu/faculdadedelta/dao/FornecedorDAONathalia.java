package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.FornecedorNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class FornecedorDAONathalia {
	
	public void incluir(FornecedorNathalia fornecedor) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO fornecedores (desc_fornecedor) VALUES(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, fornecedor.getDescForn().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void alterar(FornecedorNathalia fornecedor) throws Exception {
		Connection conn =Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE fornecedores SET desc_fornecedor=? WHERE id_fornecedor=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, fornecedor.getDescForn().trim());
			ps.setLong(2, fornecedor.getIdForn());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void excluir(FornecedorNathalia fornecedor) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM fornecedores WHERE id_fornecedor=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, fornecedor.getIdForn());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public List<FornecedorNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM fornecedores";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<FornecedorNathalia> listaRetorno = new ArrayList<>();
		
		try {
			while(rs.next()) {
				FornecedorNathalia fornecedor = new FornecedorNathalia();
				fornecedor.setIdForn(rs.getLong("id_fornecedor"));
				fornecedor.setDescForn(rs.getString("desc_fornecedor").trim());
				listaRetorno.add(fornecedor);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}

	public FornecedorNathalia pesquisarPorId(Long idForn) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM fornecedores WHERE id_fornecedor = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		FornecedorNathalia retorno = new FornecedorNathalia();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, idForn);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setIdForn(rs.getLong("id_fornecedor"));
				retorno.setDescForn(rs.getString("desc_fornecedor").trim());
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
