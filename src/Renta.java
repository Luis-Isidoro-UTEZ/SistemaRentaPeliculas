public class Renta {
    private Pelicula pelicula;
    private int cantidadActualRentada; // cuántas copias están rentadas ahora mismo

    public Renta(Pelicula pelicula) {
        this.pelicula = pelicula;
        this.cantidadActualRentada = 1;
    }

    public Pelicula getPelicula() { return pelicula; }
    public int getCantidadActualRentada() { return cantidadActualRentada; }

    public void incrementar() { cantidadActualRentada++; }
    public void decrementar() { if (cantidadActualRentada > 0) cantidadActualRentada--; }

    @Override
    public String toString() {
        return pelicula.getTitulo() + " - Copias rentadas ahora: " + cantidadActualRentada;
    }
}
