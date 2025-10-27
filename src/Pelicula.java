public class Pelicula {
    private String codigo;
    private String titulo;
    private int duracion; // en minutos
    private String genero;
    private int cantidadTotal; // stock inicial
    private int cantidadDisponible; // stock actual
    private int contadorRentas; // veces que se ha rentado en total

    public Pelicula(String codigo, String titulo, int duracion, String genero, int cantidadDisponible) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.cantidadTotal = cantidadDisponible;
        this.cantidadDisponible = cantidadDisponible;
        this.contadorRentas = 0;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public int getDuracion() { return duracion; }
    public String getGenero() { return genero; }
    public int getCantidadTotal() { return cantidadTotal; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public int getContadorRentas() { return contadorRentas; }

    // Operaciones simples
    public boolean rentarUna() {
        if (cantidadDisponible > 0) {
            cantidadDisponible -= 1;
            contadorRentas += 1;
            return true;
        }
        return false;
    }

    public boolean devolverUna() {
        if (cantidadDisponible < cantidadTotal) {
            cantidadDisponible += 1;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + titulo + " (" + duracion + " min) Género: " + genero +
                " - Disponibles: " + cantidadDisponible + " / Total: " + cantidadTotal +
                " - Veces rentada: " + contadorRentas;
    }
}
