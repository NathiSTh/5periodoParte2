package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.EditoraDaoNathalia;
import br.edu.faculdadedelta.modelo.EditoraNathalia;

@ManagedBean
@SessionScoped
public class EditoraControllerNathalia {
	
	private EditoraNathalia editora = new EditoraNathalia();
	private EditoraDaoNathalia dao = new EditoraDaoNathalia();
	
	public EditoraNathalia getEditora() {
		return editora;
	}
	public void setEditora(EditoraNathalia editora) {
		this.editora = editora;
	}
	
	public void limparCampos() {
		editora = new EditoraNathalia();
	}

	private void exibirMensagem (String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if(editora.getId() == null) {
				dao.incluir(editora);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(editora);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroEditora.xhtml";
	}
	
	public String editar() {
		return "CadastroEditora.xhtml";
	}
	
	public String excluir() {
		try {
				dao.excluir(editora);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");		
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaEditora.xhtml";
	}

	public List<EditoraNathalia> getLista(){
		List<EditoraNathalia> listaRetorno = new ArrayList<>();
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde!" + e.getMessage());
		}
		
		return listaRetorno;
	}

}
