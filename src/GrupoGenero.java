public class GrupoGenero {
    private String genero;
    private ListaPeliculas peliculas;

    public GrupoGenero(String genero) {
        this.genero = genero;
        this.peliculas = new ListaPeliculas();
    }

    public String getGenero() { return genero; }
    public ListaPeliculas getPeliculas() { return peliculas; }

    public void imprimirGrupo() {
        System.out.println("Género: " + genero);
        peliculas.imprimirLista();
    }
}
