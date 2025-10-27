public class GestorCatalogo {
    private ListaPeliculas catalogo;
    private ListaRentas rentas;

    public GestorCatalogo() {
        catalogo = new ListaPeliculas();
        rentas = new ListaRentas();
    }

    // Agregar nueva película (código único)
    public boolean agregarPelicula(Pelicula p) {
        Pelicula existente = catalogo.buscarPorCodigo(p.getCodigo());
        if (existente != null) return false; // ya existe
        catalogo.agregarFinal(p);
        return true;
    }

    // Eliminar película (solo si no hay copias rentadas)
    public boolean eliminarPelicula(String codigo) {
        Pelicula p = catalogo.buscarPorCodigo(codigo);
        if (p == null) return false;
        if (p.getCantidadDisponible() < p.getCantidadTotal()) return false; // hay copias rentadas
        return catalogo.eliminarPorCodigo(codigo);
    }

    // Renta por codigo
    public boolean rentarPelicula(String codigo) {
        Pelicula p = catalogo.buscarPorCodigo(codigo);
        if (p == null) return false;
        boolean pudo = p.rentarUna();
        if (!pudo) return false;


        Renta r = rentas.buscarPorCodigo(codigo);
        if (r == null) rentas.agregarFinal(new Renta(p));
        else r.incrementar();
        return true;
    }

    // Devolución por código
    public boolean devolverPelicula(String codigo) {
        Renta r = rentas.buscarPorCodigo(codigo);
        if (r == null) return false; // no está rentada
        boolean pudo = r.getPelicula().devolverUna();
        if (!pudo) return false; // no había nada que devolver


        r.decrementar();
        if (r.getCantidadActualRentada() == 0) rentas.eliminarPorCodigo(codigo);
        return true;
    }

    // Reporte: películas disponibles para renta (devuelve una lista nueva)
    public ListaPeliculas reporteDisponibles() {
        ListaPeliculas resultado = new ListaPeliculas();
        Pelicula[] arr = catalogo.aArreglo();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCantidadDisponible() > 0) resultado.agregarFinal(arr[i]);
        }
        return resultado;
    }

    // Reporte: películas actualmente rentadas
    public ListaRentas reporteRentadas() {
        return rentas;
    }

    // Reporte: películas más rentadas (top N)
    public Pelicula[] reporteMasRentadas(int topN) {
        Pelicula[] arr = catalogo.aArreglo();
    // selección descendente por contadorRentas
        for (int i = 0; i < arr.length; i++) {
            int mejor = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].getContadorRentas() > arr[mejor].getContadorRentas()) mejor = j;
            }
            if (mejor != i) {
                Pelicula tmp = arr[i]; arr[i] = arr[mejor]; arr[mejor] = tmp;
            }
        }
        int n = Math.min(topN, arr.length);
        Pelicula[] salida = new Pelicula[n];
        for (int i = 0; i < n; i++) salida[i] = arr[i];
        return salida;
    }

    // Reporte: películas menos rentadas (top N)
    public Pelicula[] reporteMenosRentadas(int topN) {
        Pelicula[] arr = catalogo.aArreglo();
    // selección ascendente por contadorRentas
        for (int i = 0; i < arr.length; i++) {
            int mejor = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].getContadorRentas() < arr[mejor].getContadorRentas()) mejor = j;
            }
            if (mejor != i) {
                Pelicula tmp = arr[i]; arr[i] = arr[mejor]; arr[mejor] = tmp;
            }
        }
        int n = Math.min(topN, arr.length);
        Pelicula[] salida = new Pelicula[n];
        for (int i = 0; i < n; i++) salida[i] = arr[i];
        return salida;
    }

    // Reporte: películas clasificadas por género
    public ListaGrupoGenero reportePorGenero() {
        ListaGrupoGenero grupos = new ListaGrupoGenero();
        Pelicula[] arr = catalogo.aArreglo();
        for (int i = 0; i < arr.length; i++) {
            String gen = arr[i].getGenero();
            GrupoGenero g = grupos.buscarPorGenero(gen);
            if (g == null) {
                GrupoGenero nuevo = new GrupoGenero(gen);
                nuevo.getPeliculas().agregarFinal(arr[i]);
                grupos.agregarFinal(nuevo);
            } else {
                g.getPeliculas().agregarFinal(arr[i]);
            }
        }
        return grupos;
    }

    // Acceso al catálogo para imprimir o usar
    public ListaPeliculas obtenerCatalogo() { return catalogo; }
}