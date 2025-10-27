package com.aeroporto;

import javax.swing.*;

import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;

import java.awt.*;

public class Heder{
 Colors cor = new Colors();

 public Heder(String Titulo , JFrame j) {
  JLabel titulo = new JLabel(Titulo, SwingConstants.CENTER);
  titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
  titulo.setBackground(cor.getAzulTopo());
  titulo.setForeground(Color.WHITE);
  titulo.setOpaque(true);
  titulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // largura expansível, altura fixa
  titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

  j.add(Box.createVerticalStrut(2));
  j.add(titulo);
  j.add(Box.createVerticalStrut(2));

  JPanel PainelStatus = new JPanel(new BorderLayout());
  PainelStatus.setPreferredSize(new Dimension(350, 45));
  PainelStatus.setMaximumSize(new Dimension(350, 45));
  // PainelStatus.setBackground(verde);
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
  // statusPanel.setBackground(azulFundo);

  JLabel textDisponivel = new JLabel("Disponível:");
  Dados a = new Dados();
  
  JLabel Disponivel = new JLabel(Integer.toString(a.listarVoosDisponivel()));
  JLabel textIndisponivel = new JLabel("Indisponível:");
  JLabel Indisponivel = new JLabel("0");

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

  j.add(PainelStatus);
  j.add(Box.createVerticalStrut(2));
 }
}
