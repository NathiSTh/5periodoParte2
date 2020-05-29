package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.InfracaoDaoNathalia;
import br.com.faculdadedelta.modelo.InfracaoNathalia;

@ManagedBean
@SessionScoped
public class InfracaoControllerNathalia {

	private InfracaoNathalia infracao = new InfracaoNathalia();
	private InfracaoDaoNathalia dao = new InfracaoDaoNathalia();

	public InfracaoNathalia getInfracao() {
		return infracao;
	}

	public void setInfracao(InfracaoNathalia infracao) {
		this.infracao = infracao;
	}

	public void limparCampos() {
		infracao = new InfracaoNathalia();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (infracao.getId() == null) {
				dao.incluir(infracao);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(infracao);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroInfracao.xhtml";
	}

	public String editar() {
		return "CadastroIngracao.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(infracao);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaInfracao.xhtml";
	}

	public List<InfracaoNathalia> getLista() {
		List<InfracaoNathalia> listaRetorno = new ArrayList<InfracaoNathalia>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}