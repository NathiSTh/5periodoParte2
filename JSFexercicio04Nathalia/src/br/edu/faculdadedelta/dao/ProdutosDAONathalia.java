package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.FornecedorNathalia;
import br.edu.faculdadedelta.modelo.ProdutosNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class ProdutosDAONathalia {
	
	public void incluir(ProdutosNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO produtos (desc_produto, id_fornecedor, valor_produto) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, produto.getDescProd().trim());
			ps.setLong(2, produto.getFornecedor().getIdForn());
			ps.setDouble(3, produto.getValorProd());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void alterar(ProdutosNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE produtos SET desc_produto=?, id_fornecedor=?, valor_produto=? WHERE id_produto=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, produto.getDescProd().trim());
			ps.setLong(2, produto.getFornecedor().getIdForn());
			ps.setDouble(3, produto.getValorProd());
			ps.setLong(4, produto.getIdProd());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void excluir(ProdutosNathalia produto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM produtos WHERE id_produto=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, produto.getIdProd());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public List<ProdutosNathalia> listar() throws Exception{
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "SELECT\r\n" + 
					"p.id_produto AS idProduto," + 
					"p.desc_produto AS descProd,\r\n" + 
					"p.valor_produto AS valorProd,\r\n" + 
					"f.id_fornecedor AS idForn,\r\n" + 
					"f.desc_fornecedor AS descForn\r\n" + 
					"FROM produtos p INNER JOIN fornecedores f ON p.id_fornecedor = f.id_fornecedor;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<ProdutosNathalia> listaRetorno = new ArrayList<>();
			
			try {
				while(rs.next()) {
					ProdutosNathalia produto = new ProdutosNathalia();
					produto.setIdProd(rs.getLong("idProduto"));
					produto.setDescProd(rs.getString("descProd").trim());
					produto.setValorProd(rs.getDouble("valorProd"));
					
					FornecedorNathalia fornecedor = new FornecedorNathalia();
					fornecedor.setIdForn(rs.getLong("idForn"));
					fornecedor.setDescForn(rs.getString("descForn").trim());
					
					produto.setFornecedor(fornecedor);
					
					listaRetorno.add(produto);		
					
				}
		
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				Conexao.fecharConexao(conn, ps, rs);
			}
			
		 return listaRetorno;
	}
}
