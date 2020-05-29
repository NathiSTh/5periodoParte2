package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CarroNathalia;
import br.edu.faculdadedelta.modelo.MarcaNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class CarroDaoNathalia {
	public void incluir(CarroNathalia carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO carros (desc_modelo, id_marca, valor_carro) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, carro.getDesc().trim());
			ps.setLong(2, carro.getMarca().getId());
			ps.setDouble(3, carro.getValor());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void alterar(CarroNathalia carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE carros SET desc_modelo =?, id_marca =?, valor_carro =? WHERE id_carro =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, carro.getDesc().trim());
			ps.setLong(2, carro.getMarca().getId());
			ps.setDouble(3, carro.getValor());
			ps.setLong(4, carro.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void excluir(CarroNathalia carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM carros WHERE id_carro =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, carro.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public List<CarroNathalia> listar() throws Exception{
			Connection conn = Conexao.conectarNoBancoDeDados();
			
			String sql = "SELECT \r\n" + 
					"p.id AS id_processo,\r\n" + 
					"p.numero_proc AS numero,\r\n" + 
					"p.assunto_proc AS assunto, \r\n" + 
					"p.data_autuacao_proc AS data, \r\n" + 
					"p.valor_proc AS valor, \r\n" + 
					"i.id AS id_interessado,\r\n" + 
					"i.nome_interessado AS nome, \r\n" + 
					"i.cpf_interessado AS cpf\r\n" + 
					"FROM processos p INNER JOIN interessados i ON i.id = p.id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<CarroNathalia> listaRetorno = new ArrayList<>();
			
			try {
				while(rs.next()) {
					CarroNathalia carro = new CarroNathalia();
					carro.setId(rs.getLong("idCarro"));
					carro.setDesc(rs.getString("descCarro").trim());
					carro.setValor(rs.getDouble("valorCarro"));
					
					MarcaNathalia marca = new MarcaNathalia();
					marca.setId(rs.getLong("idMarca"));
					marca.setDesc(rs.getString("descMarca").trim());
					
					carro.setMarca(marca);
					
					listaRetorno.add(carro);		
					
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
