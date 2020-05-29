package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InfracaoNathalia;
import br.com.faculdadedelta.modelo.MotoristaNathalia;
import br.com.faculdadedelta.modelo.MultaNathalia;
import br.com.faculdadedelta.modelo.VeiculoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class MultaDaoNathalia {

	public void incluir(MultaNathalia multas) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO multas (id_infracao, idVeiculo, idMotorista, dataMulta) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, multas.getInfracao().getId());
			ps.setLong(2, multas.getVeiculo().getId());
			ps.setLong(3, multas.getMotorista().getId());
			ps.setDate(4, new java.sql.Date(multas.getData().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(MultaNathalia multas) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE multas SET id_infracao=? idVeiculo=? idMotorista=? dataMulta=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, multas.getInfracao().getId());
			ps.setLong(2, multas.getVeiculo().getId());
			ps.setLong(3, multas.getMotorista().getId());
			ps.setDate(4, new java.sql.Date(multas.getData().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(MultaNathalia multas) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM multas WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, multas.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<MultaNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT \r\n" + 
				"m.id AS idMulta,\r\n" + 
				"m.data_multa AS data, \r\n" + 
				"i.id AS idInfracao,\r\n" + 
				"i.gravidade_infracao AS gravidadeInfracao,\r\n" + 
				"i.descricao_infracao AS descInfracao,\r\n" + 
				"i.valor_infracao AS valor,\r\n" + 
				"v.id AS idVeiculo,\r\n" + 
				"v.gravidade_veiculo AS gravidadeVeiculo, \r\n" + 
				"v.descricao_veiculo AS descVeiculo,\r\n" + 
				"mo.id AS idMotorista,\r\n" + 
				"mo.nome_motorista AS nomeMotorista,\r\n" + 
				"mo.cpf_motorista AS cpfMotorista,\r\n" + 
				"mo.cnh_motorista AS cnhMotorista \r\n" + 
				"FROM multas m\r\n" + 
				"INNER JOIN infracoes i ON m.id = i.id\r\n" + 
				"INNER JOIN veiculos v ON m.id = v.id\r\n" + 
				"INNER JOIN motoristas mo ON m.id = mo.id";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MultaNathalia> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				MultaNathalia multa = new MultaNathalia();
				multa.setId(rs.getLong("idMulta"));
				multa.setData(rs.getDate("data"));

				InfracaoNathalia infracao = new InfracaoNathalia();
				infracao.setId(rs.getLong("idInfracao"));
				infracao.setGravidade(rs.getString("gravidadeInfracao").trim());
				infracao.setDesc(rs.getString("descInfracao").trim());
				infracao.setValorI(rs.getDouble("valor"));

				VeiculoNathalia veiculo = new VeiculoNathalia();
				veiculo.setId(rs.getLong("idVeiculo"));
				veiculo.setGravidade(rs.getString("gravidadeVeiculo").trim());
				veiculo.setDesc(rs.getString("descVeiculo").trim());

				MotoristaNathalia motorista = new MotoristaNathalia();
				motorista.setId(rs.getLong("idMotorista"));
				motorista.setNome(rs.getString("nomeMotorista").trim());
				motorista.setCpf(rs.getString("cpfMotorista").trim());
				motorista.setCnh(rs.getString("cnhMotorista").trim());

				multa.setInfracao(infracao);
				multa.setVeiculo(veiculo);
				multa.setMotorista(motorista);
				listaRetorno.add(multa);
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