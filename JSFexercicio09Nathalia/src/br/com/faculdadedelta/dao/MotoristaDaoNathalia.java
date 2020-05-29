package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.MotoristaNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class MotoristaDaoNathalia {

	public void incluir(MotoristaNathalia motorista) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO motoristas (nome_motorista, cpf_motorista, cnh_motorista) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, motorista.getNome().trim());
			ps.setString(2, motorista.getCpf().trim());
			ps.setString(3, motorista.getCnh().trim());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(MotoristaNathalia motorista) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE motoristas SET nome_motorista=?, cpf_motorista=?, cnh_motorista WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, motorista.getNome().trim());
			ps.setString(2, motorista.getCpf().trim());
			ps.setString(3, motorista.getCnh().trim());
			ps.setLong(4, motorista.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(MotoristaNathalia motorista) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM motoristas WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, motorista.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<MotoristaNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM motoristas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MotoristaNathalia> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				MotoristaNathalia motorista = new MotoristaNathalia();
				motorista.setId(rs.getLong("id"));
				motorista.setNome(rs.getString("nome_motorista"));
				motorista.setCpf(rs.getString("cpf_motorista"));
				motorista.setCnh(rs.getString("cnh_motorista"));
				listaRetorno.add(motorista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}

	public MotoristaNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM motoristas WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MotoristaNathalia retorno = new MotoristaNathalia();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNome(rs.getString("nome_motorista").trim());
				retorno.setCpf(rs.getString("cpf_motorista").trim());
				retorno.setCnh(rs.getString("cnh_motorista").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
	}

}