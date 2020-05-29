package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ProdutoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class ProdutoDaoNathalia {

	public void incluir(ProdutoNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO produtos (desc_produto) VALUES(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, produto.getProduto().trim());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ProdutoNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE produtos SET desc_produto =? WHERE id_produto=?  ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, produto.getProduto().trim());
			ps.setLong(2, produto.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(ProdutoNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM produtos WHERE id_produto=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setLong(1, produto.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public List<ProdutoNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM  produtos  ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<ProdutoNathalia> listaRetorno = new ArrayList<>();

		try {
			while(rs.next()) {
				ProdutoNathalia produto = new ProdutoNathalia();
				produto.setId(rs.getLong("id_produto"));
				produto.setProduto(rs.getString("desc_produto").trim());
				listaRetorno.add(produto);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}

	public ProdutoNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM  produtos WHERE id_produto=?  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProdutoNathalia retorno = new ProdutoNathalia();

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();

				while(rs.next()) {
					retorno.setId(rs.getLong("id_produto"));
					retorno.setProduto(rs.getString("desc_produto").trim());
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
