import java.util.HashMap;
import java.util.Map;

public class Skatista {
    private String nome;
    private Map<String, Double> habilidades;

    public Skatista(String nome) {
        this.nome = nome;
        this.habilidades = new HashMap<>();
        // Inicializa habilidades de manobras com 0% para novas manobras
    }

    public String getNome() {
        return nome;
    }

    public double getHabilidade(String manobra) {
        return habilidades.getOrDefault(manobra, 0.0);
    }

    public void atualizarHabilidade(String manobra, double incremento) {
        double novaHabilidade = Math.min(getHabilidade(manobra) + incremento, 100.0);
        habilidades.put(manobra, novaHabilidade);
    }

    // Adiciona um método para verificar o status de uma manobra específica
    public void verificarStatus(String manobra, double chanceBase) {
        double habilidade = getHabilidade(manobra);
        double chanceReal = Math.min(chanceBase + habilidade, 100.0);
        System.out.println("Status da manobra " + manobra + ":");
        System.out.println("Chance base: " + chanceBase + "%");
        System.out.println("Habilidade do skatista: " + habilidade + "%");
        System.out.println("Chance real de acerto: " + chanceReal + "%");
    }
}
