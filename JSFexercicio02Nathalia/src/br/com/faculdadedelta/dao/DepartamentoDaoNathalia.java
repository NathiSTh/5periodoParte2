package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.DepartamentoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class DepartamentoDaoNathalia {
	
	public void incluir(DepartamentoNathalia departamento) throws Exception {

		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO departamentos (desc_departamento) VALUES(?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, departamento.getDepartemanto().trim());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(DepartamentoNathalia departamento) throws Exception {

		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE departamentos SET desc_departamento =? WHERE id_departamento=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, departamento.getDepartemanto().trim());
			ps.setLong(2, departamento.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(DepartamentoNathalia departamento) throws Exception {

		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM departamentos WHERE id_departamento=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, departamento.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<DepartamentoNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM departamentos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<DepartamentoNathalia> listaRetorno = new ArrayList<>();
		
		try {
			while(rs.next()) {
				DepartamentoNathalia departamento = new DepartamentoNathalia();
				departamento.setId(rs.getLong("id_departamento"));
				departamento.setDepartemanto(rs.getString("desc_departamento").trim());
				listaRetorno.add(departamento);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}

	public DepartamentoNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM departamentos WHERE id_departamento = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		DepartamentoNathalia retorno = new DepartamentoNathalia();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_departamento"));
				retorno.setDepartemanto(rs.getString("desc_departamento").trim());
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
