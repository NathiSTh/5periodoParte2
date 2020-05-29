package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MultaDaoNathalia;
import br.com.faculdadedelta.modelo.InfracaoNathalia;
import br.com.faculdadedelta.modelo.MotoristaNathalia;
import br.com.faculdadedelta.modelo.MultaNathalia;
import br.com.faculdadedelta.modelo.VeiculoNathalia;

@ManagedBean
@SessionScoped
public class MultaControllerIgorNathalia {

	private MultaNathalia multa = new MultaNathalia();
	private MultaDaoNathalia dao = new MultaDaoNathalia();
	private InfracaoNathalia infracaoSelecionada = new InfracaoNathalia();
	private VeiculoNathalia veiculoSelecionado = new VeiculoNathalia();
	private MotoristaNathalia motoristaSelecionado = new MotoristaNathalia();

	public MultaNathalia getMulta() {
		return multa;
	}

	public void setMulta(MultaNathalia multa) {
		this.multa = multa;
	}

	public InfracaoNathalia getInfracaoSelecionada() {
		return infracaoSelecionada;
	}

	public void setInfracaoSelecionada(InfracaoNathalia infracaoSelecionada) {
		this.infracaoSelecionada = infracaoSelecionada;
	}

	public VeiculoNathalia getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public void setVeiculoSelecionado(VeiculoNathalia veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	public MotoristaNathalia getMotoristaSelecionado() {
		return motoristaSelecionado;
	}

	public void setMotoristaSelecionado(MotoristaNathalia motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}

	public void limparCampos() {
		multa = new MultaNathalia();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (multa.getId() == null) {
				multa.setInfracao(infracaoSelecionada);
				multa.setMotorista(motoristaSelecionado);
				multa.setVeiculo(veiculoSelecionado);
				dao.incluir(multa);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				multa.setInfracao(infracaoSelecionada);
				multa.setMotorista(motoristaSelecionado);
				multa.setVeiculo(veiculoSelecionado);
				dao.alterar(multa);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroMulta.xhmtl";
	}

	public String editar() {
		multa.setInfracao(infracaoSelecionada);
		multa.setMotorista(motoristaSelecionado);
		multa.setVeiculo(veiculoSelecionado);
		return "CadastroMulta.xhtml";
	}

	public String excluir() {
		try {
			multa.setInfracao(infracaoSelecionada);
			multa.setMotorista(motoristaSelecionado);
			multa.setVeiculo(veiculoSelecionado);
			dao.excluir(multa);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaMulta.xhtml";
	}

	public List<MultaNathalia> getLista() {
		List<MultaNathalia> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}