import java.util.Random;

public class Manobra {
    private String nome;
    private double chanceBase;
    private double incrementoAcerto;
    private double incrementoErro;
    private Random random;

    public Manobra(String nome, double chanceBase, double incrementoAcerto, double incrementoErro) {
        this.nome = nome;
        this.chanceBase = chanceBase;
        this.incrementoAcerto = incrementoAcerto;
        this.incrementoErro = incrementoErro;
        this.random = new Random();
    }

    public String getNome() {
        return nome;
    }

    public double getChanceBase() {
        return chanceBase;
    }

    public boolean executar(double habilidade) {
        double chanceReal = Math.min(chanceBase + habilidade, 100.0);
        return random.nextDouble() < chanceReal / 100.0;
    }

    public double calcularIncremento(boolean sucesso) {
        return sucesso ? incrementoAcerto : incrementoErro;
    }
}
