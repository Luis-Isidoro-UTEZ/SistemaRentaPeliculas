public class ListaRentas {
    private NodoRenta cabeza;
    private int tamanio;

    public ListaRentas() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregarFinal(Renta r) {
        NodoRenta nuevo = new NodoRenta(r);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoRenta actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean eliminarPorCodigo(String codigo) {
        NodoRenta actual = cabeza;
        NodoRenta anterior = null;
        while (actual != null) {
            if (actual.dato.getPelicula().getCodigo().equalsIgnoreCase(codigo)) {
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

    public Renta buscarPorCodigo(String codigo) {
        NodoRenta actual = cabeza;
        while (actual != null) {
            if (actual.dato.getPelicula().getCodigo().equalsIgnoreCase(codigo)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public void imprimirLista() {
        NodoRenta actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato.toString());
            actual = actual.siguiente;
        }
    }

    public int size() { return tamanio; }
}
