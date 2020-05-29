package br.com.faculdadedelta.conveter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.ProcessoDaoNathalia;
import br.com.faculdadedelta.modelo.ProcessoNathalia;

@FacesConverter(value = "processoConverter")
public class ProcessoConverterNathalia implements Converter {

	private ProcessoDaoNathalia dao= new ProcessoDaoNathalia();
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
			return String.valueOf(((ProcessoNathalia)valor).getId());
		}
		return null;
	}

}
