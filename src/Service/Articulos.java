package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Articulos {
   private List<Articulo> articulos;

    public Articulos() {
        this.articulos = new ArrayList<Articulo>();
    }

    public void anhadirAlaLista(Articulo articuloQueInsertar){
        articulos.add(articuloQueInsertar);
    }

    @Override
    public String toString() {
        return "Articulos{" +
                "articulos=" + articulos +
                '}';
    }

    public String getArticulo(String articuloAConseguir) {

        return String.valueOf(articulos.stream().
                filter(articulo1 -> articulo1.toString().
                        equals(articuloAConseguir)));
    }


}
