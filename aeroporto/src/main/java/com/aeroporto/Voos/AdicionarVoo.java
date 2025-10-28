package com.aeroporto.Voos;

import javax.swing.*;
import com.aeroporto.PainelPrincipal;
import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import java.awt.*;

public class AdicionarVoo extends JFrame {
    Colors cor = new Colors(); // Classe que armazena cores padrão

    public AdicionarVoo(Dados voos, Dados checkIn) {
        String titulo = "Adicionar Voo";
        setTitle(titulo);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout vertical para empilhar componentes
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(cor.getAzulFundo());

        // Título da tela
        JLabel Titulo = new JLabel(titulo, SwingConstants.CENTER);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Titulo.setBackground(cor.getAzulTopo());
        Titulo.setForeground(Color.WHITE);
        Titulo.setOpaque(true);
        Titulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(2));
        add(Titulo);
        add(Box.createVerticalStrut(2));

        // Painel de status com imagem e informações de voos disponíveis/indisponíveis
        JPanel PainelStatus = new JPanel(new BorderLayout());
        PainelStatus.setPreferredSize(new Dimension(350, 60));
        PainelStatus.setMaximumSize(new Dimension(350, 60));
        PainelStatus.setOpaque(true);
        PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Imagem representativa do status
        ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
        Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        PainelStatus.add(labelImagem, BorderLayout.WEST);

        // Painel interno para mostrar voos disponíveis e indisponíveis
        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 5));

        JLabel textDisponivel = new JLabel("Disponível:");
        JLabel Disponivel = new JLabel("0");
        Disponivel.setText(Integer.toString(voos.listarVoosDisponivel()));

        // Contagem de voos totalmente indisponíveis
        int indisponivel = 0;
        for (Voo v : voos.listarVoos()) {
            int cont = 0;
            for (String vaga : v.getAssentos()) {
                if (vaga.equals("disponivel")) {
                    cont++;
                }
            }
            // Se todos os assentos do voo estão ocupados
            if (cont == 0) {
                indisponivel++;
            }
        }

        JLabel textIndisponivel = new JLabel("Indisponível:");
        JLabel Indisponivel = new JLabel("0");
        Indisponivel.setText(Integer.toString(indisponivel));
        Indisponivel.setBackground(cor.getVermelho());
        Indisponivel.setOpaque(true);
        Disponivel.setBackground(cor.getVerde());
        Disponivel.setOpaque(true);
        statusPanel.setBackground(cor.getBranco());
        statusPanel.setOpaque(true);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Adiciona labels ao painel de status
        statusPanel.add(textDisponivel);
        statusPanel.add(Disponivel);
        statusPanel.add(textIndisponivel);
        statusPanel.add(Indisponivel);

        // Container para alinhar o painel de status à direita
        JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerDireita.add(statusPanel);
        PainelStatus.add(Box.createHorizontalGlue());
        labelImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        PainelStatus.add(containerDireita, BorderLayout.CENTER);
        add(PainelStatus);
        add(Box.createVerticalStrut(2));

        // Painel do formulário de cadastro do voo
        JPanel formularioCadastroVoo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formularioCadastroVoo.setPreferredSize(new Dimension(350, 150));
        formularioCadastroVoo.setMaximumSize(new Dimension(350, 150));

        // Campos de entrada do voo
        JLabel labNumeroVoo = new JLabel("N° Voo:");
        labNumeroVoo.setPreferredSize(new Dimension(100, 15));
        JTextField textNumeroVoo = new JTextField(20);
        formularioCadastroVoo.add(labNumeroVoo);
        formularioCadastroVoo.add(textNumeroVoo);

        JLabel labOrigem = new JLabel("Origem:");
        labOrigem.setPreferredSize(new Dimension(100, 15));
        JTextField textOrigem = new JTextField(20);
        formularioCadastroVoo.add(labOrigem);
        formularioCadastroVoo.add(textOrigem);

        JLabel labDestino = new JLabel("Destino:");
        labDestino.setPreferredSize(new Dimension(100, 15));
        JTextField textDestino = new JTextField(20);
        formularioCadastroVoo.add(labDestino);
        formularioCadastroVoo.add(textDestino);

        JLabel labHorarioPartida = new JLabel("Horario de Partida:");
        labHorarioPartida.setPreferredSize(new Dimension(100, 15));
        JTextField textHorarioPartida = new JTextField(20);
        formularioCadastroVoo.add(labHorarioPartida);
        formularioCadastroVoo.add(textHorarioPartida);

        JLabel labHorarioChegada = new JLabel("Horario de Chegada:");
        labHorarioChegada.setPreferredSize(new Dimension(100, 15));
        JTextField textHorarioChegada = new JTextField(20);
        formularioCadastroVoo.add(labHorarioChegada);
        formularioCadastroVoo.add(textHorarioChegada);

        JLabel labQuantidadePassageiros = new JLabel("Quantidade Passageiros:");
        labQuantidadePassageiros.setPreferredSize(new Dimension(100, 15));
        JSpinner numQuantidadePassageiros = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        formularioCadastroVoo.add(labQuantidadePassageiros);
        formularioCadastroVoo.add(numQuantidadePassageiros);

        // Painel com botões Salvar e Cancelar
        JPanel btnSalvarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvarCancelar.setPreferredSize(new Dimension(350, 45));
        btnSalvarCancelar.setMaximumSize(new Dimension(350, 45));

        JButton salvar = new JButton("Salvar");
        btnSalvarCancelar.add(salvar);

        // Evento do botão Salvar
        salvar.addActionListener(e -> {
            String numero = textNumeroVoo.getText();
            String origem = textOrigem.getText();
            String destino = textDestino.getText();
            String partida = textHorarioPartida.getText();
            String chegada = textHorarioChegada.getText();
            int quantidade = (int) numQuantidadePassageiros.getValue();

            // Validação dos campos do formulário
            if (numero.isBlank() || origem.isBlank() || destino.isBlank() ||
                    partida.isBlank() || chegada.isBlank() || quantidade <= 0) {

                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha todos os campos corretamente.",
                        "Campos obrigatórios",
                        JOptionPane.WARNING_MESSAGE);
                return; // Interrompe se algum campo não estiver preenchido
            }

            // Cria novo voo e adiciona à lista de voos
            Voo voo = new Voo(numero, origem, destino, partida, chegada, quantidade);
            voos.adicionarVoos(voo);

            // Atualiza status de voos disponíveis
            // voos.listarVoos();

            Disponivel.setText(Integer.toString(voos.listarVoosDisponivel()));
            textNumeroVoo.setText("");
            textOrigem.setText("");
            textDestino.setText("");
            textHorarioPartida.setText("");
            textHorarioChegada.setText("");
            numQuantidadePassageiros.setValue(0);

            abrirTela(voos, checkIn);
        });

        JButton cancelar = new JButton("Cancelar");
        btnSalvarCancelar.add(cancelar);

        // Evento do botão Cancelar: fecha tela e abre PainelPrincipal
        cancelar.addActionListener(e -> {
            abrirTela(voos, checkIn);
        });

        add(formularioCadastroVoo);
        add(Box.createVerticalStrut(5));
        add(btnSalvarCancelar);
    }

    // Método auxiliar para abrir a tela principal e fechar esta
    private void abrirTela(Dados voos, Dados checkIn) {
        dispose();
        new PainelPrincipal(voos, checkIn);
    }
}
