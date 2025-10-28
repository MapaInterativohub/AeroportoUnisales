package com.aeroporto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import com.aeroporto.Passagens.AdicionarPassagem;
import com.aeroporto.Passagens.AprovarPassagem;
import com.aeroporto.Passagens.CheckIn;
import com.aeroporto.Voos.AdicionarVoo;
import com.aeroporto.Voos.Voo;

import java.awt.*;

public class PainelPrincipal extends JFrame {
    Colors cor = new Colors();

    public PainelPrincipal(Dados voos, Dados checkIn) {
        String titulo = "Painel de Voo";
        setTitle(titulo);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // BoxLayout vertical
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(cor.getAzulFundo());

        // Título
        JLabel Titulo = new JLabel(titulo, SwingConstants.CENTER);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Titulo.setBackground(cor.getAzulTopo());
        Titulo.setForeground(Color.WHITE);
        Titulo.setOpaque(true);
        Titulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // largura expansível, altura fixa
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        add(Box.createVerticalStrut(2));
        add(Titulo);
        add(Box.createVerticalStrut(2));

        JPanel PainelStatus = new JPanel(new BorderLayout());
        PainelStatus.setPreferredSize(new Dimension(550, 60));
        PainelStatus.setMaximumSize(new Dimension(550, 60));
        PainelStatus.setOpaque(true);
        PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaçamento interno
        PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Imagem à esquerda
        ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
        Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        PainelStatus.add(labelImagem, BorderLayout.WEST);

        // Painel de status (com grid)
        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 5));
        statusPanel.setBackground(cor.getBranco());
        statusPanel.setOpaque(true);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JLabel textDisponivel = new JLabel("Disponível:");
        JLabel Disponivel = new JLabel("0");
        Disponivel.setBackground(cor.getVerde());
        Disponivel.setOpaque(true);

        int indisponivel = 0;
        for (Voo v : voos.listarVoos()) {
            int cont = 0;
            for (String vaga : v.getAssentos()) {
                if (vaga.equals("disponivel")) {
                    cont++;
                }
            }
            // Se o voo estiver totalmente cheio (nenhum assento disponível)
            if (cont == 0) {
                indisponivel++;
            }
        }
        JLabel textIndisponivel = new JLabel("Indisponível:");
        JLabel Indisponivel = new JLabel("0");

        Indisponivel.setBackground(cor.getVermelho());
        Indisponivel.setOpaque(true);

        Indisponivel.setText(Integer.toString(indisponivel));
        Disponivel.setText(Integer.toString(voos.listarVoosDisponivel() - indisponivel));

        statusPanel.add(textDisponivel);
        statusPanel.add(Disponivel);
        statusPanel.add(textIndisponivel);
        statusPanel.add(Indisponivel);

        // Painel auxiliar para alinhar à direita
        JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        // containerDireita.setBackground(cinza);
        containerDireita.add(statusPanel);
        PainelStatus.add(Box.createHorizontalGlue());
        labelImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        // Adiciona à direita do painel principal
        PainelStatus.add(containerDireita, BorderLayout.CENTER);

        add(PainelStatus);

        add(Box.createVerticalStrut(2));

        // Painel Administrativo
        JPanel btnControleAdimistrativo = new JPanel();
        btnControleAdimistrativo.setLayout(new BoxLayout(btnControleAdimistrativo, BoxLayout.Y_AXIS));
        btnControleAdimistrativo.setPreferredSize(new Dimension(550, 60));
        btnControleAdimistrativo.setMaximumSize(new Dimension(550, 60));
        btnControleAdimistrativo.setBackground(cor.getCinza());
        btnControleAdimistrativo.setOpaque(true);
        btnControleAdimistrativo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza

        JLabel textAdministrativo = new JLabel("ADMINISTRAÇÃO");
        textAdministrativo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza dentro do painel
        textAdministrativo.setBackground(cor.getCinzaEscuro());
        textAdministrativo.setOpaque(true);
        textAdministrativo.setPreferredSize(new Dimension(550, 10));
        textAdministrativo.setMaximumSize(new Dimension(550, 10));

        // btnControleAdimistrativo.add(Box.createVerticalStrut(2));
        btnControleAdimistrativo.add(textAdministrativo);

        JPanel jPanelbtnAdminstrativo = new JPanel();
        jPanelbtnAdminstrativo.setOpaque(true);
        jPanelbtnAdminstrativo.setBackground(cor.getCinza());
        jPanelbtnAdminstrativo.setPreferredSize(new Dimension(550, 100));
        jPanelbtnAdminstrativo.setMaximumSize(new Dimension(550, 100));
        jPanelbtnAdminstrativo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza

        // Botões dentro do painel cinza
        JButton btnAddVoo = new JButton("Adcionar Voo");

        btnAddVoo.addActionListener(e -> {
            abrirTela(new AdicionarVoo(voos, checkIn));
        });

        JButton btnAddPassagem = new JButton("Comprar Passagem");

        btnAddPassagem.addActionListener(e -> {
            abrirTela(new AdicionarPassagem(voos, checkIn));
        });
        JButton btnAprova = new JButton("Aprovar Passagem");
        btnAprova.addActionListener(e -> {
            abrirTela(new AprovarPassagem(voos, checkIn));
        });

        JButton btnCheckIn = new JButton("Check-In");

        btnCheckIn.addActionListener(e -> {
            abrirTela(new CheckIn(voos, checkIn));
        });

        jPanelbtnAdminstrativo.add(btnAddVoo);
        jPanelbtnAdminstrativo.add(btnAddPassagem);
        jPanelbtnAdminstrativo.add(btnAprova);
        jPanelbtnAdminstrativo.add(btnCheckIn);

        btnControleAdimistrativo.add(Box.createVerticalStrut(5));
        btnControleAdimistrativo.add(jPanelbtnAdminstrativo);

        add(btnControleAdimistrativo);
        add(Box.createVerticalStrut(5));

        // Painel de Voo
        JPanel jPanelVoo = new JPanel();
        jPanelVoo.setLayout(new BoxLayout(jPanelVoo, BoxLayout.Y_AXIS));
        jPanelVoo.setPreferredSize(new Dimension(550, 500));
        jPanelVoo.setMaximumSize(new Dimension(550, 500));
        jPanelVoo.setBackground(cor.getBranco());
        jPanelVoo.setOpaque(true);
        jPanelVoo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza

        for (Voo v : voos.listarVoos()) {
            int acentosOcupado = 0;
            int acentosDisponivel = v.getQuantidade();

            for (String item : v.getAssentos()) {
                if (item.equals("disponivel")) { // verifica o tipo desejado
                    acentosOcupado++;
                }
            }

            String t = "N°:" + v.getNumero() + "  |  Origem: " + v.getOrigem() + "  |  Destino: " + v.getDestino()
                    + " | Acentos: disp:" + acentosOcupado + "/ indi:" + (acentosDisponivel - acentosOcupado);
            JLabel text = new JLabel(t);

            if (acentosOcupado > 3) {
                text.setBackground(cor.getVerde()); // mais de 3 assentos livres
            } else if (acentosOcupado == 3 || acentosOcupado == 2) {
                text.setBackground(cor.getLaranja()); // 2 ou 3 assentos livres
            } else if (acentosOcupado == 1) {
                text.setBackground(cor.getVermelho()); // apenas 1 assento livre
            } else {
                text.setBackground(cor.getVermelho()); // cheio (0 ou negativo)
            }

            text.setPreferredSize(new Dimension(545, 23));
            text.setMaximumSize(new Dimension(545, 23));
            text.setOpaque(true);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            text.setBorder(new EmptyBorder(0, 10, 0, 10));

            jPanelVoo.add(Box.createVerticalStrut(3));
            jPanelVoo.add(text);
        }

        add(jPanelVoo);
        add(Box.createVerticalGlue()); // empurra o conteúdo para cima

        setVisible(true);
    }

    private void abrirTela(JFrame tela) {
        dispose();
        tela.setVisible(true);
    }
}
