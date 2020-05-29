package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ClienteNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class ClienteDaoNathalia {
	
	public void incluir(ClienteNathalia cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO clientes (nome_cliente ,idade_cliente ) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, cliente.getNome().trim());
			ps.setInt(2, cliente.getIdade());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ClienteNathalia cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE clientes SET nome_cliente=?, idade_cliente=? WHERE id_cliente=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, cliente.getNome().trim());
			ps.setInt(2, cliente.getIdade());
			ps.setLong(3, cliente.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public void excluir(ClienteNathalia cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM clientes WHERE id_cliente=?, ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setLong(1, cliente.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public List<ClienteNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_cliente, nome_cliente,idade_cliente FROM clientes ";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<ClienteNathalia> listaRetorno = new ArrayList<>();
		try {
			while(rs.next()) {
				ClienteNathalia cliente = new ClienteNathalia();
				cliente.setId(rs.getLong("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente").trim());
				cliente.setIdade(rs.getInt("idade_cliente"));
				listaRetorno.add(cliente);
			}	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}
	
	public ClienteNathalia pesquisarPorId (Long id) throws Exception {
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_cliente, nome_cliente, idade_cliente FROM clientes WHERE id_cliente=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClienteNathalia retorno = new ClienteNathalia();
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs= ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_cliente"));
				retorno.setNome(rs.getString("nome_cliente").trim());
				retorno.setIdade(rs.getInt("idade_cliente"));
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
