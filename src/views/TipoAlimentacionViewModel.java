
package views;

import domain.Especie;
import domain.TipoAlimentacion;


public class TipoAlimentacionViewModel {
   public TipoAlimentacion TipoAlimentacionViewModel(String seleccionado){
    Especie especieSeleccionada = Controlador.getEspecie(seleccionado);
    TipoAlimentacion tipoAlimentacionEspecie = especieSeleccionada.getTipoAlimentacion();
       return tipoAlimentacionEspecie;
    }
}
