package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ProdutoNathalia;
import br.com.faculdadedelta.modelo.VendaNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class VendaDaoNathalia {

	public void incluir(VendaNathalia venda) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO vendas(desc_produto,id_produto,valor_produto)VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
		try {
			
			ps.setString(1, venda.getDesc().trim());
			ps.setLong(2, venda.getProduto().getId());
			ps.setDouble(3, venda.getValor());
		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(VendaNathalia venda ) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE vendas SET desc_produto=?, id_produto=?, valor_produto=? WHERE id_venda=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			
			ps.setString(1, venda.getDesc());
			ps.setLong(2,venda.getProduto().getId());
			ps.setDouble(3, venda.getValor());
			ps.setLong(4, venda.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
		
	}
	
	public void excluir(VendaNathalia venda) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM vendas WHERE id_venda=?  ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, venda.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public List<VendaNathalia> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT  "
				+"v.id_venda AS  idVenda, "
				+"v.desc_produto AS descVenda,   "
				+"v.valor_produto AS valorProduto,  "
				+"p.id_produto AS idProduto,  "
				+"p.desc_produto AS descProduto"
				+" FROM vendas v"
				+" INNER JOIN produtos p ON v.id_venda = p.id_produto";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		List<VendaNathalia> listaRetorno = new ArrayList<VendaNathalia>();
		
		try {
			while(rs.next()) {
				VendaNathalia venda = new VendaNathalia();
				venda.setId(rs.getLong("idVenda"));
				venda.setDesc(rs.getString("descVenda"));
				venda.setValor(rs.getDouble("valorProduto"));
				
				ProdutoNathalia produto = new ProdutoNathalia();
				produto.setId(rs.getLong("idProduto"));
				produto.setProduto(rs.getString("descProduto"));
				
				venda.setProduto(produto);
				listaRetorno.add(venda);
			}
		
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	}finally {
		Conexao.fecharConexao(conn, ps, rs);
	}
		return listaRetorno;
		
	}
	
	
}
