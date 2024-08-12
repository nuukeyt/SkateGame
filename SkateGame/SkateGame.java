import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkateGame {
    private Skatista skatista;
    private List<Manobra> manobras;

    public SkateGame(Skatista skatista) {
        this.skatista = skatista;
        this.manobras = new ArrayList<>();
        inicializarManobras();
    }

    private void inicializarManobras() {
        // Adiciona manobras iniciais
        adicionarManobra("Ollie", 40.0, 1.0, 0.5);
        adicionarManobra("Varial", 30.0, 1.0, 0.5);
        adicionarManobra("Shove it", 30.0, 1.0, 0.5);
        adicionarManobra("Fs 180", 25.0, 1.0, 0.5);
        adicionarManobra("Bs 180", 25.0, 1.0, 0.5);
        adicionarManobra("Flip", 20.0, 1.0, 0.5);
        adicionarManobra("Heel Flip", 20.0, 1.0, 0.5);
    }

    public void adicionarManobra(String nome, double chanceBase, double incrementoAcerto, double incrementoErro) {
        manobras.add(new Manobra(nome, chanceBase, incrementoAcerto, incrementoErro));
    }

    public void executarManobra(String nome) {
        Manobra manobra = encontrarManobra(nome);

        if (manobra == null) {
            System.out.println("Manobra não reconhecida.");
            return;
        }

        boolean sucesso = manobra.executar(skatista.getHabilidade(nome));
        double incremento = manobra.calcularIncremento(sucesso);
        skatista.atualizarHabilidade(nome, incremento);

        if (sucesso) {
            System.out.println("Manobra " + nome + " bem-sucedida!");
        } else {
            System.out.println("Manobra " + nome + " falhou.");
        }
    }

    public void verificarStatus(String nome) {
        Manobra manobra = encontrarManobra(nome);
        if (manobra != null) {
            skatista.verificarStatus(nome, manobra.getChanceBase());
        } else {
            System.out.println("Manobra não reconhecida.");
        }
    }

    private Manobra encontrarManobra(String nome) {
        return manobras.stream()
                .filter(m -> m.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        // Criar um novo skatista
        Skatista pedro = new Skatista("Pedro");
        // Criar o jogo e adicionar o skatista
        SkateGame jogo = new SkateGame(pedro);

        // Adicionar manobras adicionais

        jogo.adicionarManobra("KickFlip", 15.0, 0.5, 0.2);
        jogo.adicionarManobra("HardFlip", 15.0, 0.5, 0.2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Executar manobra");
            System.out.println("2. Verificar status de manobra");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                System.out.print("Digite o nome da manobra para executar: ");
                String manobra = scanner.next();
                jogo.executarManobra(manobra);
            } else if (escolha == 2) {
                System.out.print("Digite o nome da manobra para verificar o status: ");
                String manobra = scanner.next();
                jogo.verificarStatus(manobra);
            } else if (escolha == 3) {
                break;
            } else {
                System.out.println("Escolha inválida.");
            }
        }
        scanner.close();
    }
}
