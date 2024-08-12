import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SkateGameUI extends JFrame {
    private SkateGame jogo;
    private JTextArea outputArea;
    private JTextField manobraField;
    private boolean isFullscreen = false;
    private Dimension windowedSize;
    private Point windowedLocation;

    public SkateGameUI(SkateGame jogo) {
        this.jogo = jogo;

        setTitle("Skate Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adiciona KeyListener para alternar a tela cheia
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    toggleFullscreen();
                }
            }
        });

        initUI();
    }

    private void initUI() {
        // Criação dos componentes
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        manobraField = new JTextField(20);

        JButton executarButton = new JButton("Executar Manobra");
        JButton statusButton = new JButton("Verificar Status");

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nome da Manobra:"));
        inputPanel.add(manobraField);
        inputPanel.add(executarButton);
        inputPanel.add(statusButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Adiciona o painel à janela
        add(panel);

        // Ações dos botões
        executarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manobra = manobraField.getText().trim();
                if (!manobra.isEmpty()) {
                    jogo.executarManobra(manobra);
                    outputArea.append("Executando manobra: " + manobra + "\n");
                } else {
                    outputArea.append("Por favor, insira o nome de uma manobra.\n");
                }
            }
        });

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manobra = manobraField.getText().trim();
                if (!manobra.isEmpty()) {
                    jogo.verificarStatus(manobra);
                    outputArea.append("Verificando status da manobra: " + manobra + "\n");
                } else {
                    outputArea.append("Por favor, insira o nome de uma manobra.\n");
                }
            }
        });
    }

    private void toggleFullscreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (!isFullscreen) {
            // Salvar estado da janela normal
            windowedSize = getSize();
            windowedLocation = getLocation();

            // Alternar para tela cheia
            dispose();
            setUndecorated(true);
            gd.setFullScreenWindow(this);
            isFullscreen = true;
        } else {
            // Restaurar para janela normal
            gd.setFullScreenWindow(null);
            dispose();
            setUndecorated(false);
            setSize(windowedSize);
            setLocation(windowedLocation);
            setVisible(true);
            isFullscreen = false;
        }
    }

    public static void main(String[] args) {
        // Criar um novo skatista
        Skatista pedro = new Skatista("Pedro");
        // Criar o jogo e adicionar o skatista
        SkateGame jogo = new SkateGame(pedro);

        // Adicionar manobras adicionais
        jogo.adicionarManobra("KickFlip", 15.0, 0.5, 0.2);
        jogo.adicionarManobra("HardFlip", 15.0, 0.5, 0.2);

        SwingUtilities.invokeLater(() -> {
            SkateGameUI ex = new SkateGameUI(jogo);
            ex.setVisible(true);
        });
    }
}
