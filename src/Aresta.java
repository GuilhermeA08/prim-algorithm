public class Aresta {
    Integer existe;
    Integer peso;

    public Aresta(Integer existe, Integer peso) {
        this.existe = existe;
        this.peso = peso;
    }

    public String toString() {
        return existe + " - " + peso;
    }
}
