package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InfracaoNathalia;
import br.com.faculdadedelta.modelo.VeiculoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class VeiculoDaoNathalia {

	public void incluir(VeiculoNathalia veiculo) throws Exception {
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO veiculos (gravidade_veiculo, descricao_veiculo) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1,  veiculo.getGravidade().trim());
			ps.setString(2, veiculo.getDesc().trim());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
	
	public void alterar(VeiculoNathalia veiculo) throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE veiculos SET gravidade_veiculo=?, descricao_veiculo=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
	    try {
			ps.setString(1, veiculo.getGravidade().trim());
			ps.setString(2, veiculo.getDesc().trim());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
	
	public void excluir(VeiculoNathalia veiculo) throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM veiculos WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, veiculo.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
	
	public List<VeiculoNathalia> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM veiculos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<VeiculoNathalia> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				VeiculoNathalia veiculo = new VeiculoNathalia();
				veiculo.setId(rs.getLong("id"));
				veiculo.setGravidade(rs.getString("gravidade_veiculo"));
				veiculo.setDesc(rs.getString("descricao_veiculo"));
				listaRetorno.add(veiculo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
		}
	
    public  InfracaoNathalia pesquisarPorId(Long id) throws Exception {
    	Connection conn = Conexao.conectarNoBancoDeDados();
    	String sql = "SELECT * FROM veiculos WHERE id=?";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	InfracaoNathalia retorno = new InfracaoNathalia();
    	try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setGravidade(rs.getString("gravidade_veiculo").trim());
				retorno.setDesc(rs.getString("descricao_veiculo").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
    	return retorno;
        }}