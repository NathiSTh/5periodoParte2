package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ClienteNathalia;
import br.edu.faculdadedelta.modelo.OrdemServicoNathalia;
import br.edu.faculdadedelta.modelo.ServicoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class OrdemServicoDaoNathalia {
	
	public void incluir(OrdemServicoNathalia ordem) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO ordem_servico ( id_cliente,id_servico,valor_unitario_ordem_servico,qtde_ordem_servico,valor_desconto,valor_total_ordem_servico)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setLong(1, ordem.getCliente().getId());
			ps.setLong(2, ordem.getServico().getId());
			ps.setDouble(3, ordem.getValorUnitario());
			ps.setInt(4, ordem.getQuant());
			ps.setDouble(5, ordem.getValorDesconto());
			ps.setDouble(6, ordem.getValorTotal());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(OrdemServicoNathalia ordem) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE ordem_servico SET "
				+ "id_cliente=?, "
				+ "id_servico=?, "
				+ "valor_unitario_ordem_servico=?, "
				+ "qtde_ordem_servico=?, "
				+ "valor_desconto=?, "
				+ "valor_total_ordem_servico=?" + 
				"	WHERE id_ordem_servico=? ";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, ordem.getCliente().getId());
			ps.setLong(2, ordem.getServico().getId());
			ps.setDouble(3, ordem.getValorUnitario());
			ps.setInt(4, ordem.getQuant());
			ps.setDouble(5, ordem.getValorDesconto());
			ps.setDouble(6, ordem.getValorTotal());
			ps.setLong(7, ordem.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(OrdemServicoNathalia ordem) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM ordem_servico WHERE id_ordem_servico=? ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, ordem.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}


	public List<OrdemServicoNathalia>listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"o.id_ordem_servico AS idOrdem,\r\n" + 
				"o.valor_unitario_ordem_servico AS valorOrdem, \r\n" + 
				"o.qtde_ordem_servico AS quantOrdem,\r\n" + 
				"o.valor_desconto AS valorDesconto , \r\n" + 
				"o.valor_total_ordem_servico AS valorTotal,\r\n" + 
				"c.id_cliente AS idCliente,\r\n" + 
				"c.nome_cliente AS nomeCliente ,\r\n" + 
				"c.idade_cliente AS idadeCliente ,\r\n" + 
				"s.id_servico AS idServico, \r\n" + 
				"s.nome_servico AS nomeServico ,\r\n" + 
				"s.descricao_servico AS descricaoServico\r\n" + 
				"FROM ordem_servico o\r\n" + 
				"INNER JOIN clientes c ON o.id_cliente = c.id_cliente\r\n" + 
				"INNER JOIN servico s ON o.id_servico = s.id_servico";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdemServicoNathalia>listaRetorno = new ArrayList<OrdemServicoNathalia>();
	
		try {
			ps = conn.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				OrdemServicoNathalia ordem = new OrdemServicoNathalia();
				ordem.setId(rs.getLong("idOrdem"));
				ordem.setValorUnitario(rs.getDouble("valorOrdem"));
				ordem.setQuant(rs.getInt("quantOrdem"));
				ordem.setValorDesconto(rs.getDouble("valorDesconto"));
				ordem.setValorTotal(rs.getDouble("valorTotal"));
				
				ClienteNathalia cliente = new ClienteNathalia();
				cliente.setId(rs.getLong("idCliente"));
				cliente.setNome(rs.getString("nomeCliente"));
				cliente.setIdade(rs.getInt("idadeCliente"));
				
				ServicoNathalia servico = new ServicoNathalia();
				servico.setId(rs.getLong("idServico"));
				servico.setNome(rs.getString("nomeServico"));
				servico.setDescricao(rs.getString("descricaoServico"));
				
				ordem.setCliente(cliente);
				ordem.setServico(servico);
				
				listaRetorno.add(ordem);
				
				
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
