package views;

import domain.Carnivoro;
import domain.Especie;
import domain.Herbivoro;
import domain.Mamifero;
import domain.Pais;
import domain.Sector;
import domain.TipoAlimentacion;
import static domain.TipoAlimentacion.HERBIVORO;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import javax.swing.JOptionPane;

public class AnimalViewModel {

    private String especie;
    private int edad;
    private String sector;
    private double peso;
    private double valorFijo;
    private double porcentaje;

    public AnimalViewModel(Mamifero animal) {
        if (animal == null) {
            return;
        }
        especie = animal.getEspecie().getNombre();
        edad = animal.getEdad();
        sector = animal.getSector().toString();
        peso = animal.getPeso();
        valorFijo = animal instanceof Herbivoro ? ((Herbivoro) animal).getValorFijo() : 0;
        porcentaje = animal instanceof Carnivoro ? animal.getEspecie().getPorcentajePesoCarnivoro() : 0;
    }

    public static Sector buscarSectorDesdeTexto(String texto, ArrayList<Sector> listaSectores) {
        try {
            int numero = Integer.parseInt(texto.substring(texto.indexOf(":") + 2, texto.indexOf(" [")).trim());
            String tipoStr = texto.substring(texto.indexOf("[") + 1, texto.indexOf("]")).trim();
            TipoAlimentacion tipo = TipoAlimentacion.valueOf(tipoStr.toUpperCase());

            for (Sector s : listaSectores) {
                if (s.getNumero() == numero && s.getTipoAlimentacion() == tipo) {
                    return s;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al recuperar sector desde el string.");
            e.printStackTrace();
        }

        return null;
    }

    public static void AnimalInputModel(String edad, String peso, String especie, String sector, String valorfijo, String pais) throws InvalidPropertiesFormatException {
        // Parseo de valores
        int edadInt;
        double pesoDouble;
        double valorFijoDouble;
        edadInt = Integer.parseInt(edad);
        pesoDouble = Double.parseDouble(peso);
        valorFijoDouble = Double.parseDouble(valorfijo);
        Especie especieSeleccionada = Controlador.getEspecie(especie);
        Sector sectorSeleccionado = buscarSectorDesdeTexto(sector, Controlador.getSectores());
        Pais paisSeleccionado = Controlador.getPais(pais); // asumimos método así

// Crear el animal según tipo de alimentación
        if (especieSeleccionada.getTipoAlimentacion().equals(TipoAlimentacion.HERBIVORO)) {
            Herbivoro h = new Herbivoro(
                    edadInt,
                    pesoDouble,
                    especieSeleccionada,
                    sectorSeleccionado,
                    valorFijoDouble,
                    paisSeleccionado
            );
            Controlador.IngresarAnimal(h);

        } else if (especieSeleccionada.getTipoAlimentacion().equals(TipoAlimentacion.CARNIVORO)) {
            Carnivoro c = new Carnivoro(
                    edadInt,
                    pesoDouble,
                    especieSeleccionada,
                    sectorSeleccionado,
                    paisSeleccionado
            );
            Controlador.IngresarAnimal(c);
        } else {
            throw new IllegalArgumentException("Tipo de alimentación no reconocido");
        }

    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public String getSector() {
        return sector;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorFijo() {
        return valorFijo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}
