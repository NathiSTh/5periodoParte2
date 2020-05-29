package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ContratoNathalia;
import br.com.faculdadedelta.modelo.ProcessoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class ContratoDaoNathalia {

	public void incluir(ContratoNathalia contrato) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO contratos(" + 
				"numero_contrato, " +
				"descricao_contrato, " +
				"valor_contrato, " +
				"data_inicio_contrato, " +
				"data_fim_contrato, " +
				"id_processo)" + 
				"VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, contrato.getNumContrato().trim());
			ps.setString(2, contrato.getContrato().trim());
			ps.setDouble(3, contrato.getValor());
			ps.setDate(4, new java.sql.Date(contrato.getDataInicio().getTime()));
			ps.setDate(5, new java.sql.Date(contrato.getDataFim().getTime()));
			ps.setLong(6, contrato.getProcesso().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ContratoNathalia contrato) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE contratos SET "
				+ "numero_contrato=?," 
				+ "descricao_contrato=?," 
				+ "valor_contrato=?,"
				+ "data_inicio_contrato=?," 
				+ "data_fim_contrato=?," 
				+ "id_processo=?" 
				+ "WHERE id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, contrato.getNumContrato().trim());
			ps.setString(2, contrato.getContrato().trim());
			ps.setDouble(3, contrato.getValor());
			ps.setDate(4, new java.sql.Date(contrato.getDataInicio().getTime()));
			ps.setDate(5, new java.sql.Date(contrato.getDataFim().getTime()));
			ps.setLong(6, contrato.getProcesso().getId());
			ps.setLong(7, contrato.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(ContratoNathalia contrato) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM contratos WHERE id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, contrato.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public List<ContratoNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"c.id AS idContrato,\r\n" + 
				"c.numero_contrato AS numContrato,\r\n" + 
				"c.descricao_contrato AS descContrato,\r\n" + 
				"c.valor_contrato AS valorContrato, \r\n" + 
				"c.data_inicio_contrato AS dataInicio,\r\n" + 
				"c.data_fim_contrato AS dataFim, \r\n" + 
				"p.id AS idProcesso,\r\n" + 
				"p.numero_processo AS numProcesso,\r\n" + 
				"p.descricao_processo AS descProcesso,\r\n" + 
				"p.valor_processo AS valorProcesso\r\n" + 
				"FROM contratos c\r\n" + 
				"INNER JOIN processos p ON c.id = p.id";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<ContratoNathalia> listaRetorno = new ArrayList<>();

		try {
			while (rs.next()) {

				ContratoNathalia contrato = new ContratoNathalia();
				contrato.setId(rs.getLong("idContrato"));
				contrato.setNumContrato(rs.getString("numContrato").trim());
				contrato.setContrato(rs.getString("descContrato").trim());
				contrato.setValor(rs.getDouble("valorContrato"));
				contrato.setDataInicio(rs.getDate("dataInicio"));
				contrato.setDataFim(rs.getDate("dataFim"));

				ProcessoNathalia processo = new ProcessoNathalia();
				processo.setId(rs.getLong("idProcesso"));
				processo.setNumero(rs.getString("numProcesso").trim());
				processo.setProcesso(rs.getString("descProcesso").trim());
				processo.setValorProc(rs.getDouble("valorProcesso"));

				contrato.setProcesso(processo);
				listaRetorno.add(contrato);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;

	}

}
