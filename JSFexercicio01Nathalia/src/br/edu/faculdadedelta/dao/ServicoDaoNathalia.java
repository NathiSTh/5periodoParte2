package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ClienteNathalia;
import br.edu.faculdadedelta.modelo.ServicoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class ServicoDaoNathalia {
	
	public void incluir(ServicoNathalia servico) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO servico (nome_servico, descricao_servico ) VALUES(?,?) ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, servico.getNome().trim());
			ps.setString(2, servico.getDescricao().trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ServicoNathalia servico) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE servico SET nome_servico=?, descricao_servico=? WHERE id_servico=?";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, servico.getNome().trim());
			ps.setString(2, servico.getDescricao().trim());
			ps.setLong(3, servico.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(ServicoNathalia servico) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM servico WHERE id_servico=?";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setLong(1, servico.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<ServicoNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_servico,nome_servico,descricao_servico FROM servico ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ServicoNathalia> listaRetorno = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				ServicoNathalia servico = new ServicoNathalia();
				servico.setId(rs.getLong("id_servico"));
				servico.setNome(rs.getString("nome_servico").trim());
				servico.setDescricao(rs.getString("descricao_servico").trim());
				listaRetorno.add(servico);
			}	


		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}
	
	public ServicoNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_servico,nome_servico,descricao_servico FROM servico WHERE id_servico =? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicoNathalia retorno = new ServicoNathalia();
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs= ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_servico"));
				retorno.setNome(rs.getString("nome_servico").trim());
				retorno.setDescricao(rs.getString("descricao_servico").trim());
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