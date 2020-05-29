package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.EditoraNathalia;
import br.edu.faculdadedelta.modelo.LivroNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class LivroDaoNathalia {
	
	public void incluir(LivroNathalia livro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO livros (nome_livro, id_editora, valor_livro) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, livro.getTitulo().trim());
			ps.setLong(2, livro.getEditora().getId());
			ps.setDouble(3, livro.getValor());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void alterar(LivroNathalia livro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE livros SET nome_livro=?, id_editora=?, valor_livro=? WHERE id_livro=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1, livro.getTitulo().trim());
			ps.setLong(2, livro.getEditora().getId());
			ps.setDouble(3, livro.getValor());
			ps.setLong(4, livro.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public void excluir(LivroNathalia livro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM livros WHERE id_livro=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setLong(1, livro.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}

	public List<LivroNathalia> listar() throws Exception{
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "SELECT\r\n" + 
					"l.id_livro AS idLivro," + 
					"l.nome_livro AS titulo,\r\n" + 
					"l.valor_livro AS valorLivro,\r\n" + 
					"e.id_editora AS idEdit,\r\n" + 
					"e.desc_editora AS DescEdit\r\n" + 
					"FROM livros l INNER JOIN editoras e ON l.id_editora = e.id_editora;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<LivroNathalia> listaRetorno = new ArrayList<>();
			
			try {
				while(rs.next()) {
					LivroNathalia livro = new LivroNathalia();
					livro.setId(rs.getLong("idLivro"));
					livro.setTitulo(rs.getString("titulo").trim());
					livro.setValor(rs.getDouble("valorLivro"));
					
					EditoraNathalia editora = new EditoraNathalia();
					editora.setId(rs.getLong("idEdit"));
					editora.setEditora(rs.getString("DescEdit").trim());
					
					livro.setEditora(editora);
					
					listaRetorno.add(livro);		
					
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
