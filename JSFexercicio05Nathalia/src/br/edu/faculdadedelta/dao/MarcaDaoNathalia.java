package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.MarcaNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class MarcaDaoNathalia {
	
	public void incluir(MarcaNathalia marca) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO marcas (desc_marca) VALUES(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, marca.getDesc().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(MarcaNathalia marca) throws Exception {
		Connection conn =Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE marcas SET desc_marca =? WHERE id_marca =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, marca.getDesc().trim());
			ps.setLong(2, marca.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public void excluir(MarcaNathalia marca) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM marcas WHERE id_marca=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, marca.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public List<MarcaNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM marcas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MarcaNathalia> listaRetorno = new ArrayList<>();
		
		try {
			while(rs.next()) {
				MarcaNathalia marca = new MarcaNathalia();
				marca.setId(rs.getLong("id_marca"));
				marca.setDesc(rs.getString("desc_marca").trim());
				listaRetorno.add(marca);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public MarcaNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM marcas WHERE id_marca = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MarcaNathalia retorno = new MarcaNathalia();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_marca"));
				retorno.setDesc(rs.getString("desc_marca").trim());
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
