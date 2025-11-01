package com.aeroporto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.aeroporto.Dados.Dados;
import com.aeroporto.Passagens.Passageiro;
import com.aeroporto.Voos.Voo;

public class TelaInicial extends JFrame {
    public TelaInicial() {
        setTitle("Painel de Voo - Escolha o modo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));

        // ====== Título ======
        JLabel titulo = new JLabel("Painel de Voo", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(30, 75, 150));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(400, 60));

        add(titulo, BorderLayout.NORTH);

        // ====== Painel de botões ======
        JPanel botoes = new JPanel(new GridLayout(2, 1, 15, 15));
        botoes.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        botoes.setBackground(new Color(230, 240, 255));

        
        JButton btnAdmin = new JButton("Admin");
        JButton btnCliente = new JButton("Cliente");

        
        btnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));

        botoes.add(btnAdmin);
        botoes.add(btnCliente);
        
        add(botoes, BorderLayout.CENTER);

        // ====== CRIAÇÃO DOS DADOS COMPARTILHADOS ======
        Dados voos = new Dados();
        Dados checkIn = new Dados();

        // ---- Voos iniciais de exemplo (como no Main.java) ----
        Voo teste1 = new Voo("0142", "NULL", "NULL", "NULL", "NULL", 4);
        Voo teste2 = new Voo("5693", "NULL2", "NULL2", "NULL2", "NULL2", 100);

        Passageiro p1 = new Passageiro(teste1, "p1", "234", 3, "tt", "3");
        teste1.addPassagensPendentes(p1);

        Passageiro p2 = new Passageiro(teste1, "p2", "2342", 1, "tt2", "1");
        teste1.addPassagensPendentes(p2);

        voos.adicionarVoos(teste1);
        voos.adicionarVoos(teste2);

        // ====== Ações dos botões ======
        btnCliente.addActionListener(e -> {
            new PainelPrincipalCliente(voos, checkIn);
        });

        btnAdmin.addActionListener(e -> {
            new PainelPrincipalAdmin(voos, checkIn);
        });

        setVisible(true);
    }
}
