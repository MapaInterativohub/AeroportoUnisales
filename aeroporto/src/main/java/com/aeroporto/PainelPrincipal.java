package com.aeroporto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import com.aeroporto.Passagens.AdicionarPassagem;
import com.aeroporto.Passagens.AprovarPassagem;
import com.aeroporto.Passagens.CheckIn;
import com.aeroporto.Voos.AdicionarVoo;
import com.aeroporto.Voos.Voo;

public class PainelPrincipal extends JFrame {
    Colors cor = new Colors();

    public PainelPrincipal(Dados voos, Dados checkIn) {
        String titulo = "Painel de Voo";
        setTitle(titulo);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout vertical
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(cor.getAzulFundo());

            // === Título ===
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        headerPanel.setBackground(cor.getAzulTopo());

        JLabel Titulo = new JLabel(titulo, SwingConstants.CENTER);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Titulo.setForeground(Color.WHITE);
        Titulo.setOpaque(false);

        add(Box.createVerticalStrut(2));
        add(headerPanel);
        add(Box.createVerticalStrut(2));


        // === Painel Status ===
        JPanel PainelStatus = new JPanel(new BorderLayout());
        PainelStatus.setPreferredSize(new Dimension(550, 60));
        PainelStatus.setMaximumSize(new Dimension(550, 60));
        PainelStatus.setOpaque(true);
        PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Logo
        ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
        Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        PainelStatus.add(labelImagem, BorderLayout.WEST);

        // Status (disponível / indisponível)
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

        JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerDireita.add(statusPanel);
        PainelStatus.add(Box.createHorizontalGlue());
        PainelStatus.add(containerDireita, BorderLayout.CENTER);
        add(PainelStatus);
        add(Box.createVerticalStrut(2));

        // === Painel Administrativo ===
        JPanel btnControleAdimistrativo = new JPanel();
        btnControleAdimistrativo.setLayout(new BoxLayout(btnControleAdimistrativo, BoxLayout.Y_AXIS));
        btnControleAdimistrativo.setPreferredSize(new Dimension(550, 60));
        btnControleAdimistrativo.setMaximumSize(new Dimension(550, 60));
        btnControleAdimistrativo.setBackground(cor.getCinza());
        btnControleAdimistrativo.setOpaque(true);
        btnControleAdimistrativo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textAdministrativo = new JLabel("Aeroporto");
        textAdministrativo.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAdministrativo.setBackground(cor.getCinzaEscuro());
        textAdministrativo.setOpaque(true);
        textAdministrativo.setPreferredSize(new Dimension(550, 10));
        textAdministrativo.setMaximumSize(new Dimension(550, 10));

        btnControleAdimistrativo.add(textAdministrativo);

        JPanel jPanelbtnAdminstrativo = new JPanel();
        jPanelbtnAdminstrativo.setOpaque(true);
        jPanelbtnAdminstrativo.setBackground(cor.getCinza());
        jPanelbtnAdminstrativo.setPreferredSize(new Dimension(550, 100));
        jPanelbtnAdminstrativo.setMaximumSize(new Dimension(550, 100));
        jPanelbtnAdminstrativo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // === Botões administrativos ===
        JButton btnAddVoo = new JButton("Adicionar Voo");
        btnAddVoo.addActionListener(e -> abrirTela(new AdicionarVoo(voos, checkIn)));

        JButton btnAddPassagem = new JButton("Comprar Passagem");
        btnAddPassagem.addActionListener(e -> abrirTela(new AdicionarPassagem(voos, checkIn)));

        JButton btnAprova = new JButton("Aprovar Passagem");
        btnAprova.addActionListener(e -> abrirTela(new AprovarPassagem(voos, checkIn)));

        JButton btnCheckIn = new JButton("Check-In");
        btnCheckIn.addActionListener(e -> abrirTela(new CheckIn(voos, checkIn)));

        jPanelbtnAdminstrativo.add(btnAddVoo);
        jPanelbtnAdminstrativo.add(btnAddPassagem);
        jPanelbtnAdminstrativo.add(btnAprova);
        jPanelbtnAdminstrativo.add(btnCheckIn);

        btnControleAdimistrativo.add(Box.createVerticalStrut(5));
        btnControleAdimistrativo.add(jPanelbtnAdminstrativo);
        add(btnControleAdimistrativo);
        add(Box.createVerticalStrut(5));

        // === Painel de Voo ===
        JPanel jPanelVoo = new JPanel();
        jPanelVoo.setLayout(new BoxLayout(jPanelVoo, BoxLayout.Y_AXIS));
        jPanelVoo.setPreferredSize(new Dimension(550, 500));
        jPanelVoo.setMaximumSize(new Dimension(550, 500));
        jPanelVoo.setBackground(cor.getBranco());
        jPanelVoo.setOpaque(true);
        jPanelVoo.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Voo v : voos.listarVoos()) {
            int acentosOcupado = 0;
            int acentosDisponivel = v.getQuantidade();

            for (String item : v.getAssentos()) {
                if (item.equals("disponivel")) {
                    acentosOcupado++;
                }
            }

            String t = "N°:" + v.getNumero() + "  |  Origem: " + v.getOrigem() + "  |  Destino: " + v.getDestino()
                    + " | Acentos: disp:" + acentosOcupado + "/ indi:" + (acentosDisponivel - acentosOcupado);
            JLabel text = new JLabel(t);

            if (acentosOcupado > 3) {
                text.setBackground(cor.getVerde());
            } else if (acentosOcupado == 3 || acentosOcupado == 2) {
                text.setBackground(cor.getLaranja());
            } else {
                text.setBackground(cor.getVermelho());
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
        add(Box.createVerticalGlue());
        setVisible(true);
    }

    private void abrirTela(JFrame tela) {
    dispose();
    tela.setVisible(true);
    }



    // ✅ Getter para os botões administrativos
    public java.util.List<JButton> getBotoesAdministrativos() {
        java.util.List<JButton> botoes = new java.util.ArrayList<>();

        // Procura todos os botões dentro do painel cinza de administração
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JPanel painel) {
                for (Component inner : painel.getComponents()) {
                    if (inner instanceof JPanel subPainel) {
                        for (Component btn : subPainel.getComponents()) {
                            if (btn instanceof JButton jButton) {
                                botoes.add(jButton);
                            }
                        }
                    }
                }
            }
        }
        return botoes;
    }
}
