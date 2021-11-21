import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DesafioGincana {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //desenvolva sua solução aqui
        // variáveis
        int N;
        List<String> nome = new ArrayList<>();
        List<Integer> valor = new ArrayList<>();
        List<String> vencedores = new ArrayList<>();
        String val;
        String[] leitor;

        // Executa programa até ser digitado "0"
        while (!(val = bf.readLine()).equals("0")){
            N = Integer.parseInt(val); // número de participantes
            nome.clear();
            valor.clear();

            // preenche as listas de nomes e valores correspondentes
            for (int i = 0; i < N; i++) {
                leitor = bf.readLine().split(" ");
                nome.add(leitor[0]);
                valor.add(Integer.parseInt(leitor[1]));
            }

            // começa o jogo
            int indice = 0; // indice do primeiro participante
            int indiceAnterior; // indice do início da próxima rodada baseada na jogada anterior
            int novoValor = valor.get(indice); // valor do participante onde começa a contagem
            int x; //conta a quantos participantes da posicação inicial está o participante a ser eliminado
            // executa o loop até restar um participante (vencedor)
            while (N > 1) {
                // cálculo do indice na lista do participante a ser eliminado.
                indiceAnterior = indice;
                x = novoValor % N;
                if (novoValor % 2 != 0) {  //se o valor for ímpar conta crescentemente se for ímpar decresce.
                    indice = indice + x;
                } else indice = indice - x;
                // se o valor calculado exceder a lista retorna ao ínicio ou fim da lista para ajustar o indice
                if (indice < 0) indice = N - (x - indiceAnterior);
                else if (indice > N - 1) indice = x - (N - indiceAnterior) ;

                // ajuste dos parametros para próxima iteração e remoçao do participante das listas (nome e valor)
                novoValor = valor.get(indice);
                valor.remove(indice);
                nome.remove(indice);
                N--;
                // define os indices onde a próxima contagem vai começar dependendo se for par ou impar
                if (novoValor % 2 != 0) {
                    indice--;
                    if (indice < 0) indice = N - 1;
                } else {
                    if (indice == N) indice = 0;
                }
            }
            vencedores.add(nome.get(0));
        }
        bf.close();
        for(String vencedor: vencedores) System.out.println("Vencedor(a): "+vencedor);
    }
}
