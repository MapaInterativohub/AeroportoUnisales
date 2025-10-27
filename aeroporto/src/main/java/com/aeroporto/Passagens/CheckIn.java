package com.aeroporto.Passagens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.*;

import com.aeroporto.PainelPrincipal;
import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import com.aeroporto.Voos.Voo;

public class CheckIn extends JFrame {
    Colors cor = new Colors(); // Classe para cores customizadas

    // Construtor da tela de Check-In
    public CheckIn(Dados voos, Dados checkin) {
        String titulo = "Check In";
        setTitle(titulo);
        setSize(400, 500);
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha ao clicar no X
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Layout vertical
        getContentPane().setBackground(cor.getAzulFundo());

        // ---------- TÍTULO ----------
        JLabel Titulo = new JLabel(titulo, SwingConstants.CENTER);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Titulo.setBackground(cor.getAzulTopo());
        Titulo.setForeground(Color.WHITE);
        Titulo.setOpaque(true);
        Titulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // altura fixa, largura expansível
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente
        add(Titulo);

        add(Box.createVerticalStrut(2)); // Espaço vertical

        // ---------- PAINEL DE STATUS ----------
        JPanel PainelStatus = new JPanel(new BorderLayout());
        PainelStatus.setPreferredSize(new Dimension(350, 45));
        PainelStatus.setMaximumSize(new Dimension(350, 45));
        PainelStatus.setOpaque(true);
        PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // espaçamento interno

        // Imagem do lado esquerdo
        ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
        Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        PainelStatus.add(labelImagem, BorderLayout.WEST);

        // Painel de status com grid 2x2
        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 5));

        JLabel textDisponivel = new JLabel("Disponível:");
        JLabel Disponivel = new JLabel("0");

        // Contagem de voos totalmente ocupados
        int indisponivel = 0;
        for (Voo v : voos.listarVoos()) {
            int cont = 0;
            for (String vaga : v.getAssentos()) {
                if (vaga.equals("disponivel")) {
                    cont++;
                }
            }
            if (cont == 0) { // nenhum assento disponível
                indisponivel++;
            }
        }

        JLabel textIndisponivel = new JLabel("Indisponível:");
        JLabel Indisponivel = new JLabel("0");
        Indisponivel.setText(Integer.toString(indisponivel));
        Disponivel.setText(Integer.toString(voos.listarVoosDisponivel() - indisponivel));

        statusPanel.add(textDisponivel);
        statusPanel.add(Disponivel);
        statusPanel.add(textIndisponivel);
        statusPanel.add(Indisponivel);

        // Painel auxiliar para alinhar à direita
        JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerDireita.add(statusPanel);
        PainelStatus.add(Box.createHorizontalGlue());
        labelImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Adiciona painel de status à tela
        PainelStatus.add(containerDireita, BorderLayout.CENTER);
        add(PainelStatus);

        add(Box.createVerticalStrut(2));

        // ---------- PAINEL DE SELEÇÃO DE VOO ----------
        JPanel PaineSelecaoVoo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PaineSelecaoVoo.setPreferredSize(new Dimension(350, 28));
        PaineSelecaoVoo.setMaximumSize(new Dimension(350, 28));
        PaineSelecaoVoo.setBackground(cor.getCinza());
        PaineSelecaoVoo.setOpaque(true);
        PaineSelecaoVoo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textVoo = new JLabel("Vool:");
        textVoo.setPreferredSize(new Dimension(100, 15));

        // Preenchendo JComboBox com números de voo
        String[] numeroDosVoos = new String[voos.getListaVoos().size()];
        int controle = 0;
        for (Voo v : voos.getListaVoos()) {
            numeroDosVoos[controle] = v.getNumero();
            controle++;
        }
        controle = 0;

        JComboBox<String> comboBox = new JComboBox<>(numeroDosVoos);
        comboBox.setPreferredSize(new Dimension(220, 20));
        PaineSelecaoVoo.add(textVoo);
        PaineSelecaoVoo.add(comboBox);

        // ---------- PAINEL DE CHECK-IN ----------
        JPanel JPanelCheckIn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanelCheckIn.setPreferredSize(new Dimension(350, 250));
        JPanelCheckIn.setMaximumSize(new Dimension(350, 250));
        JPanelCheckIn.setBackground(cor.getCinza());
        JPanelCheckIn.setOpaque(true);
        JPanelCheckIn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listener do comboBox para atualizar passageiros
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String vooSelecionado = (String) comboBox.getSelectedItem();
                System.out.println(vooSelecionado);

                // Limpa lista de passageiros antes de atualizar
                JPanelCheckIn.removeAll();
                int contador = 0;

                // Inverte lista para mostrar últimos voos primeiro
                ArrayList<Voo> listaInvertida = new ArrayList<>(voos.getListaVoos());
                Collections.reverse(listaInvertida);

                for (Voo v : listaInvertida) {
                    if (vooSelecionado.equals(v.getNumero())) {
                        for (Passageiro p : v.getCheckIn()) {
                            System.out.println(p);

                            // ---------- PAINEL DE CADA PASSAGEIRO ----------
                            JPanel Passagem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
                            Passagem.setPreferredSize(new Dimension(340, 65));
                            Passagem.setMaximumSize(new Dimension(340, 65));
                            Passagem.setBackground(cor.getBranco());
                            Passagem.setOpaque(true);

                            // Label com informações do passageiro
                            JLabel descricao = new JLabel("<html>"
                                    + "<div style='font-family:Arial; font-size:11px; line-height:1.4em;'>"
                                    + "<b>Nome:</b> " + p.getNome() + "<br>"
                                    + "<b>CPF:</b> " + p.getCPF() + "<br>"
                                    + "<b>Voo:</b> " + p.getVoo().getNumero() + " &nbsp;&nbsp; "
                                    + "<b>Assento:</b> " + p.getAcento()
                                    + "</div>"
                                    + "</html>");
                            descricao.setOpaque(true);
                            descricao.setBackground(Color.WHITE);
                            descricao.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
                            descricao.setPreferredSize(new Dimension(220, 55));

                            JScrollPane scroll = new JScrollPane(descricao);
                            descricao.setBackground(cor.getBranco());
                            descricao.setOpaque(true);
                            Passagem.add(scroll);

                            // ---------- PAINEL DE BOTÕES ----------
                            JPanel PanelBtnAprovacao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                            PanelBtnAprovacao.setPreferredSize(new Dimension(100, 50));
                            PanelBtnAprovacao.setMaximumSize(new Dimension(100, 50));
                            PanelBtnAprovacao.setBackground(cor.getBranco());
                            PanelBtnAprovacao.setOpaque(true);

                            JButton aprovar = new JButton("Aprovar");
                            aprovar.setActionCommand(Integer.toString(contador));
                            aprovar.setFont(new Font("Arial", Font.BOLD, 11));
                            aprovar.setPreferredSize(new Dimension(90, 18));

                            JButton reprovar = new JButton("Recusar");
                            reprovar.setActionCommand(Integer.toString(contador));
                            reprovar.setFont(new Font("Arial", Font.BOLD, 11));
                            reprovar.setPreferredSize(new Dimension(90, 18));

                            // Apenas o passageiro no topo do Stack pode aprovar/reprovar
                            if (p.getNome().equals(v.getCheckIn().peek().getNome())) {
                                aprovar.setEnabled(true);
                                reprovar.setEnabled(true);
                            } else {
                                aprovar.setEnabled(false);
                                reprovar.setEnabled(false);
                                Passagem.setBackground(cor.getCinza());
                                descricao.setBackground(cor.getCinza());
                                PanelBtnAprovacao.setBackground(cor.getCinza());
                            }

                            PanelBtnAprovacao.add(aprovar); // adiciona botão ao painel
                            Passagem.add(PanelBtnAprovacao); // adiciona painel ao passageiro
                            JPanelCheckIn.add(Passagem); // adiciona passageiro ao painel principal

                            // Listener do botão aprovar
                            aprovar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Passagem.removeAll(); // remove componentes do painel do passageiro

                                    String a = e.getActionCommand();
                                    System.out.println("Clicou no botão com ID: " + a);

                                    Stack<Passageiro> checIn = v.getCheckIn();
                                    checIn.pop(); // remove passageiro do topo do stack
                                    p.setCheckInRelisado("confimado"); // marcar check-in (typo aqui)
                                    v.setCheckIn(checIn); // atualiza stack no voo
                                    System.out.println(checIn);

                                    Passagem.revalidate();
                                    Passagem.repaint();
                                }

                            });
                        }
                    }
                }

                // Atualiza o layout do painel principal
                JPanelCheckIn.revalidate();
                JPanelCheckIn.repaint();
            }
        });

        // ---------- ADICIONA PAINÉIS NA TELA ----------
        add(Box.createVerticalStrut(2));
        add(PaineSelecaoVoo);
        add(Box.createVerticalStrut(2));
        add(JPanelCheckIn);
        add(Box.createVerticalStrut(2));

        // ---------- BOTÃO CANCELAR ----------
        JPanel btnSalvarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvarCancelar.setPreferredSize(new Dimension(350, 45));
        btnSalvarCancelar.setMaximumSize(new Dimension(350, 45));

        JButton cancelar = new JButton("Cancelar");
        btnSalvarCancelar.add(cancelar);
        add(btnSalvarCancelar);

        cancelar.addActionListener(e -> {
            abrirTela(new PainelPrincipal(voos, checkin)); // volta para painel principal
        });

        // setVisible(true); // ativar visibilidade fora do construtor
    }

    // Método para abrir outra tela e fechar a atual
    private void abrirTela(JFrame tela) {
        dispose();
        tela.setVisible(true);
    }
}
