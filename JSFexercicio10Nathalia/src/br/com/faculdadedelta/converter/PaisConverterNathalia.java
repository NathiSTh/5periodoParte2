package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.PaisDaoNathalia;
import br.com.faculdadedelta.modelo.PaisNathalia;

@FacesConverter(value = "paisConverter")
public class PaisConverterNathalia implements Converter {

	PaisDaoNathalia dao= new PaisDaoNathalia();
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor!=null) {
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
		if(valor!=null) {
			return String.valueOf(((PaisNathalia)valor).getId());
		}
		return null;
	}

}
