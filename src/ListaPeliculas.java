public class ListaPeliculas {
    private NodoPelicula cabeza;
    private int tamanio;

    public ListaPeliculas() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregarFinal(Pelicula p) {
        NodoPelicula nuevo = new NodoPelicula(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoPelicula actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    // Elimina la primera película con el código indicado
    public boolean eliminarPorCodigo(String codigo) {
        NodoPelicula actual = cabeza;
        NodoPelicula anterior = null;
        while (actual != null) {
            if (actual.dato.getCodigo().equalsIgnoreCase(codigo)) {
                if (anterior == null) cabeza = actual.siguiente;
                else anterior.siguiente = actual.siguiente;
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    // Busca y devuelve la película (o null)
    public Pelicula buscarPorCodigo(String codigo) {
        NodoPelicula actual = cabeza;
        while (actual != null) {
            if (actual.dato.getCodigo().equalsIgnoreCase(codigo)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    // Imprime toda la lista por consola
    public void imprimirLista() {
        NodoPelicula actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato.toString());
            actual = actual.siguiente;
        }
    }

    public int size() { return tamanio; }

    // Convierte la lista a un arreglo de Pelicula
    public Pelicula[] aArreglo() {
        Pelicula[] arr = new Pelicula[tamanio];
        NodoPelicula actual = cabeza;
        int i = 0;
        while (actual != null) {
            arr[i++] = actual.dato;
            actual = actual.siguiente;
        }
        return arr;
    }
}
