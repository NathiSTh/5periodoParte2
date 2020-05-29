package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MotoristaDaoNathalia;
import br.com.faculdadedelta.modelo.MotoristaNathalia;

@ManagedBean
@SessionScoped
public class MotoristaControllerNathalia {

	private MotoristaNathalia motorista = new MotoristaNathalia();
	private MotoristaDaoNathalia dao = new MotoristaDaoNathalia();

	public MotoristaNathalia getMotorista() {
		return motorista;
	}

	public void setMotorista(MotoristaNathalia motorista) {
		this.motorista = motorista;
	}

	public void limparCampos() {
		motorista = new MotoristaNathalia();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (motorista.getId() == null) {
				dao.incluir(motorista);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(motorista);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroMotorista.xhtml";
	}

	public String editar() {
		return "CadastroMotorista.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(motorista);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaMotorista.xhtml";
	}

	public List<MotoristaNathalia> getLista() {
		List<MotoristaNathalia> listaRetorno = new ArrayList<MotoristaNathalia>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return listaRetorno;
	}
}