public class ListaGrupoGenero {
    private NodoGrupoGenero cabeza;
    private int tamanio;

    public ListaGrupoGenero() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregarFinal(GrupoGenero g) {
        NodoGrupoGenero nuevo = new NodoGrupoGenero(g);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoGrupoGenero actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public GrupoGenero buscarPorGenero(String genero) {
        NodoGrupoGenero actual = cabeza;
        while (actual != null) {
            if (actual.dato.getGenero().equalsIgnoreCase(genero)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public void imprimirLista() {
        NodoGrupoGenero actual = cabeza;
        while (actual != null) {
            actual.dato.imprimirGrupo();
            actual = actual.siguiente;
        }
    }

    public int size() { return tamanio; }
}
