package views;

import data.Persistencia;
import domain.*;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class Controlador {

    public static TipoAlimentacion[] getTiposAlimentacion() {
        return TipoAlimentacion.values();
    }

    public static ArrayList<Especie> getEspecies() {
        return Persistencia.getEspecies();
    }

    public static ArrayList<Sector> getSectores() {
        return Persistencia.getSectores();
    }
    
    public static ArrayList<Pais> getPaises(){
        return Persistencia.getPaises();
    }

    public static ArrayList<AnimalViewModel> getAnimales() {
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for (Mamifero animal : Persistencia.getAnimales()) {
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }

    public static ComidaViewModel calcularComida() {
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }

    public static Especie getEspecie(String nombre) {
        ArrayList<Especie> especies = Persistencia.getEspecies();
        for (Especie elemento : especies) {
            if (elemento.getNombre() == null ? nombre == null : elemento.getNombre().equals(nombre)) {
                return elemento; 
            }
        }
        return null; 
    }
    
    public static Pais getPais(String nombre) {
    ArrayList<Pais> paises = Persistencia.getPaises();
    for (Pais elemento : paises) {
        if (elemento.getNombre() == null ? nombre == null : elemento.getNombre().equals(nombre)) {
            return elemento;
        }
    }
    return null;
}
   
  public static void IngresarAnimal(Mamifero animal) throws InvalidPropertiesFormatException{
      Persistencia.AgregarAnimales(animal);
  }
}
