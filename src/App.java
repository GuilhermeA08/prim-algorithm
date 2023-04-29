import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Carrega os arquivos com a matriz de incidência, e uma matriz com os pesos de
        // cada aresta na matriz de incidência
        File arquivoIncidencia = new File(
                "./src/matrizIncidencia.txt");
        File arquivoPesos = new File("./src/matrizPesos.txt");
        Scanner incidencia = new Scanner(arquivoIncidencia);
        Scanner pesos = new Scanner(arquivoPesos);

        // Duas listas, uma para adicionar os vértices iniciais, e a segunda lista para
        // os vértices em que o caminho já passou
        List<Integer> vertices = new ArrayList<>(7);
        List<Integer> verticesHolder = new ArrayList<>();

        // Matriz de Incidencia com as arestas e seus respectivos pesos
        Aresta[][] matrizIncidencia = new Aresta[7][7];
        for (int i = 0; i < 7; i++) {
            vertices.add(i);
            for (int j = 0; j < 7; j++) {
                matrizIncidencia[i][j] = new Aresta(incidencia.nextInt(), pesos.nextInt());
            }
        }

        // Embaralha a lista de vértices inicias para ter um vértice inicial diferente a
        // cada vez que o programa executar
        Collections.shuffle(vertices);

        // Lista de String onde vai conter as arestas que foram selecionadas que possuem
        // o menor peso naquele determinado momento, com os vértices que o conecta, com
        // uma variável para poder montarmos a strng
        List<String> caminhos = new ArrayList<>();
        String arestaHolder = "";

        // Variável menor peso para poder comparar os pesos a medida que o código itera
        // as arestas, e o remover uma variável para conseguirmos guardar o índice de
        // onde o vértice está dentro da Lista vertices
        Integer menorPeso = Integer.MAX_VALUE;
        int remover = 0;

        // Removemos o primeiro elemento da lista de vértices iniciais, e colocamos na
        // lista de vértices já percorridos, pois o primeiro vértice é aquele que irá
        // iniciar o algoritmo.
        verticesHolder.add(vertices.remove(0));

        /*
         * Aqui verificamos o algoritmo
         * Se a lista vertices possui [4, 2, 0, 1] e a lista verticesHolder possui [3],
         * iremos iniciar o algoritmo do vértice 3
         * Iremos iterar na matriz de incidencia procurando as arestas, caso a variável
         * "existe" seja igual a um, por exemplo
         * na primeira iteração, a verificação se dá na matriz[3][4], se a aresta que
         * liga 3 e 4 existir, ele verifica se o peso
         * da aresta é menor que o peso anterior, e faz essa verificação até ter feito
         * com todos os vértices.
         * Ao final, irá pegar a aresta com menor peso, remover o vértice que ele está
         * ligado que ainda está na lista inicial
         * de vértices, e colocá-lo na lista verticesHolder, que contém vértices que já
         * passaram. E depois vai verificar as
         * arestas desses dois para outros que não foram passados com o menor peso.
         */
        while (!vertices.isEmpty()) {
            for (int i = 0; i < verticesHolder.size(); i++) {
                for (int j = 0; j < vertices.size(); j++) {
                    // Aqui verificamos se a aresta conecta os dois vértices, e se o peso é menor
                    // que o anterior.
                    if (matrizIncidencia[verticesHolder.get(i)][vertices.get(j)].existe == 1
                            && matrizIncidencia[verticesHolder.get(i)][vertices.get(j)].peso < menorPeso) {
                        remover = j;
                        menorPeso = matrizIncidencia[verticesHolder.get(i)][vertices.get(j)].peso;
                        arestaHolder = verticesHolder.get(i) + " - " + menorPeso + " - " +
                                vertices.get(j);
                    }
                }
            }

            // Adicionamos a aresta, com os vértices conectados e o peso, removemos da lista
            // inicial, e colocamos na lista dos que já foram passados
            caminhos.add(arestaHolder);
            verticesHolder.add(vertices.remove(remover));
            menorPeso = Integer.MAX_VALUE;
        }

        // Aqui printamos os caminhos selecionados no seguinte formato:
        // Vértice1 - PesoDaAresta - Vértice2
        for (int i = 0; i < caminhos.size(); i++) {
            System.out.println(caminhos.get(i));
        }
        incidencia.close();
        pesos.close();
    }
}
