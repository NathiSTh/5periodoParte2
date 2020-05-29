package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.LivroDaoNathalia;
import br.edu.faculdadedelta.modelo.EditoraNathalia;
import br.edu.faculdadedelta.modelo.LivroNathalia;


@ManagedBean
@SessionScoped
public class LivroControllerNathalia {
	
	private LivroNathalia livro = new LivroNathalia();
	private LivroDaoNathalia dao = new LivroDaoNathalia();
	private EditoraNathalia editoraSelecionada = new EditoraNathalia();
	
	public LivroNathalia getLivro() {
		return livro;
	}
	public void setLivro(LivroNathalia livro) {
		this.livro = livro;
	}
	public EditoraNathalia getEditoraSelecionada() {
		return editoraSelecionada;
	}
	public void setEditoraSelecionada(EditoraNathalia editoraSelecionada) {
		this.editoraSelecionada = editoraSelecionada;
	}
	
	public void limparCampos() {
		livro = new LivroNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if(livro.getId() == null) {
				livro.setEditora(editoraSelecionada);
				dao.incluir(livro);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				livro.setEditora(editoraSelecionada);
				dao.alterar(livro);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroLivro.xhtml";
	}

	public String editar() {
		livro.setEditora(editoraSelecionada);
		return "CadastroLivro.xhtml";
	}
	
	public String excluir() {
		try {
			livro.setEditora(editoraSelecionada);
				dao.excluir(livro);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
			return "ListaLivro.xhtml";
	}
	
	public List<LivroNathalia> getListar(){
		List<LivroNathalia> listaRetorno = new ArrayList<>();
		
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}
}
