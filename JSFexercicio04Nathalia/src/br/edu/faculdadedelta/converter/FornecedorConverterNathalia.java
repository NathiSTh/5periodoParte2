package br.edu.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.FornecedorDAONathalia;
import br.edu.faculdadedelta.modelo.FornecedorNathalia;

@FacesConverter (value="FornecedorConverter")
public class FornecedorConverterNathalia implements Converter{
	
	private FornecedorDAONathalia dao = new FornecedorDAONathalia();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((FornecedorNathalia) valor).getIdForn());
		}
		return null;
	}

}
