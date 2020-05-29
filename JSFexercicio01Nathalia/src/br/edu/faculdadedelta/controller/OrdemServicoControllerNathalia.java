package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.OrdemServicoDaoNathalia;
import br.edu.faculdadedelta.modelo.ClienteNathalia;
import br.edu.faculdadedelta.modelo.OrdemServicoNathalia;
import br.edu.faculdadedelta.modelo.ServicoNathalia;

@ManagedBean
@SessionScoped
public class OrdemServicoControllerNathalia {

	private OrdemServicoNathalia ordem = new OrdemServicoNathalia();
	private OrdemServicoDaoNathalia dao = new OrdemServicoDaoNathalia();
	private ClienteNathalia clienteSelecionado = new ClienteNathalia();
	private ServicoNathalia servicoSelecionado = new ServicoNathalia();

	public OrdemServicoNathalia getOrdem() {
		return ordem;
	}

	public void setOrdem(OrdemServicoNathalia ordem) {
		this.ordem = ordem;
	}

	public ClienteNathalia getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteNathalia clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ServicoNathalia getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(ServicoNathalia servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public void limparCampos() {
		ordem = new OrdemServicoNathalia();
		clienteSelecionado = new ClienteNathalia();
		servicoSelecionado = new ServicoNathalia();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (ordem.getId() == null) {
				ordem.setCliente(clienteSelecionado);
				ordem.setServico(servicoSelecionado);
				dao.incluir(ordem);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");

			} else {
				dao.alterar(ordem);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");

			}

		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());

		}

		return "CadastroOrdemServico.xhtml";

	}

	public String editar() {
		clienteSelecionado = ordem.getCliente();
		servicoSelecionado = ordem.getServico();
		return "CadastroOrdemServico.xhtml";

	}

	public String excluir() {
		try {
			dao.excluir(ordem);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return "ListaOrdemServico.xhtml";

	}

	public List<OrdemServicoNathalia> getListar() {
		List<OrdemServicoNathalia> listaRetorno = new ArrayList<>();

		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return listaRetorno;

	}

}
