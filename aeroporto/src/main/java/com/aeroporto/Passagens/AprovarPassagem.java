package com.aeroporto.Passagens;

import javax.swing.*;
import com.aeroporto.PainelPrincipal;
import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import com.aeroporto.Voos.Voo;
import java.awt.*;
import java.awt.event.*;

public class AprovarPassagem extends JFrame {
  Colors cor = new Colors(); // Classe com cores padrão

  public AprovarPassagem(Dados voos, Dados checkIn) {
    String titulo = "Aprovar Passagem";
    setTitle(titulo);
    setSize(400, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Layout vertical
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

    // Painel de status: imagem + voos disponíveis/indisponíveis
    JPanel PainelStatus = new JPanel(new BorderLayout());
    PainelStatus.setPreferredSize(new Dimension(350, 45));
    PainelStatus.setMaximumSize(new Dimension(350, 45));
    PainelStatus.setOpaque(true);
    PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
    PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

    ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
    Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
    JLabel labelImagem = new JLabel(new ImageIcon(img));
    PainelStatus.add(labelImagem, BorderLayout.WEST);

    // Grid com contagem de voos disponíveis/indisponíveis
    JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 5));
    JLabel textDisponivel = new JLabel("Disponível:");
    JLabel Disponivel = new JLabel("0");

    int indisponivel = 0;
    for (Voo v : voos.listarVoos()) {
      int cont = 0;
      for (String vaga : v.getAssentos()) {
        if (vaga.equals("disponivel"))
          cont++;
      }
      if (cont == 0)
        indisponivel++;
    }
    JLabel textIndisponivel = new JLabel("Indisponível:");
    JLabel Indisponivel = new JLabel(Integer.toString(indisponivel));
    Disponivel.setText(Integer.toString(voos.listarVoosDisponivel() - indisponivel));

    statusPanel.add(textDisponivel);
    statusPanel.add(Disponivel);
    statusPanel.add(textIndisponivel);
    statusPanel.add(Indisponivel);

    // Container à direita
    JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    containerDireita.add(statusPanel);
    PainelStatus.add(Box.createHorizontalGlue());
    labelImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
    statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    PainelStatus.add(containerDireita, BorderLayout.CENTER);

    add(PainelStatus);
    add(Box.createVerticalStrut(2));

    // Painel para seleção do voo
    JPanel selecaoDeVoo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    selecaoDeVoo.setPreferredSize(new Dimension(350, 28));
    selecaoDeVoo.setMaximumSize(new Dimension(350, 28));
    selecaoDeVoo.setBackground(cor.getCinza());

    JLabel textVoo = new JLabel("Voo:");
    textVoo.setPreferredSize(new Dimension(100, 15));

    String[] numeroDosVoos = new String[voos.getListaVoos().size()];
    int controle = 0;
    for (Voo v : voos.getListaVoos()) {
      numeroDosVoos[controle] = v.getNumero();
      controle++;
    }

    JComboBox<String> comboBox = new JComboBox<>(numeroDosVoos);
    comboBox.setPreferredSize(new Dimension(220, 20));
    selecaoDeVoo.add(textVoo);
    selecaoDeVoo.add(comboBox);

    // Painel onde serão listadas as aprovações
    JPanel PainelAprovacoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    PainelAprovacoes.setPreferredSize(new Dimension(350, 380));
    PainelAprovacoes.setMaximumSize(new Dimension(350, 380));
    PainelAprovacoes.setBackground(cor.getCinza());

    // Atualiza lista de aprovações ao mudar voo selecionado
    comboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String vooSelecionado = (String) comboBox.getSelectedItem();
        PainelAprovacoes.removeAll(); // Limpa aprovações antigas

        for (Voo v : voos.getListaVoos()) {
          if (vooSelecionado.equals(v.getNumero())) {
            int contado = 0;

            // Para cada passageiro pendente
            for (Passageiro p : v.getReservasPendentes()) {
              JPanel Passagem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
              Passagem.setPreferredSize(new Dimension(340, 65));
              Passagem.setMaximumSize(new Dimension(340, 65));
              Passagem.setBackground(cor.getBranco());

              // Label com informações do passageiro
              JLabel descricao = new JLabel("<html>"
                  + "<div style='font-family:Arial; font-size:11px; line-height:1.4em;'>"
                  + "<b>Nome:</b> " + p.getNome() + "<br>"
                  + "<b>CPF:</b> " + p.getCPF() + "<br>"
                  + "<b>Voo:</b> " + p.getVoo().getNumero() + " &nbsp;&nbsp; "
                  + "<b>Assento:</b> " + p.getAcento()
                  + "</div></html>");
              descricao.setPreferredSize(new Dimension(220, 55));
              descricao.setOpaque(true);
              descricao.setBackground(Color.WHITE);

              JScrollPane scroll = new JScrollPane(descricao);
              Passagem.add(scroll);

              // Painel para botões Aprovar/Reprovar
              JPanel PanelBtnAprovacao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
              PanelBtnAprovacao.setPreferredSize(new Dimension(100, 50));
              PanelBtnAprovacao.setMaximumSize(new Dimension(100, 50));
              PanelBtnAprovacao.setBackground(cor.getBranco());

              JButton btnaprovar = new JButton("Aprovar");
              btnaprovar.setActionCommand(Integer.toString(contado));
              btnaprovar.setFont(new Font("Arial", Font.BOLD, 11));
              btnaprovar.setPreferredSize(new Dimension(90, 18));

              JButton reprovar = new JButton("Reprovar");
              reprovar.setActionCommand(Integer.toString(contado));
              reprovar.setFont(new Font("Arial", Font.BOLD, 11));
              reprovar.setPreferredSize(new Dimension(90, 18));

              // Define aparência dependendo do status da compra
              switch (p.getStatusDeCompra()) {
                case "cancelada":
                  JLabel cancelado = new JLabel("<html><b>Compra</b> Cancelada</html>");
                  Passagem.setBackground(cor.getVermelho());
                  descricao.setBackground(cor.getVermelho());
                  PanelBtnAprovacao.setBackground(cor.getVermelho());
                  PanelBtnAprovacao.add(cancelado);
                  break;
                case "confirmada":
                  JLabel confirmado = new JLabel("<html><b>Compra</b> Aprovada</html>");
                  PanelBtnAprovacao.add(confirmado);
                  Passagem.setBackground(cor.getVerde());
                  descricao.setBackground(cor.getVerde());
                  PanelBtnAprovacao.setBackground(cor.getVerde());
                  break;
                default:
                  PanelBtnAprovacao.add(btnaprovar);
                  PanelBtnAprovacao.add(reprovar);
              }

              Passagem.add(PanelBtnAprovacao);
              PainelAprovacoes.add(Passagem);
              contado++;

              // Evento do botão Aprovar
              btnaprovar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  PainelAprovacoes.removeAll();
                  int id = Integer.parseInt(e.getActionCommand());
                  p.setStatusDeCompra("confirmada");
                  v.getCheckIn().push(v.getReservasPendentes().get(id));
                  PainelAprovacoes.revalidate();
                  PainelAprovacoes.repaint();
                }
              });

              // Evento do botão Reprovar
              reprovar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  PainelAprovacoes.removeAll();
                  // int id = Integer.parseInt(e.getActionCommand());
                  p.setStatusDeCompra("cancelada");
                  PainelAprovacoes.revalidate();
                  PainelAprovacoes.repaint();
                }
              });
            }
          }
        }

        PainelAprovacoes.revalidate();
        PainelAprovacoes.repaint();
      }
    });

    add(selecaoDeVoo);
    add(Box.createVerticalStrut(2));
    add(PainelAprovacoes);
    add(Box.createVerticalStrut(2));

    // Painel botão Cancelar
    JPanel btnSalvarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    btnSalvarCancelar.setPreferredSize(new Dimension(350, 45));
    btnSalvarCancelar.setMaximumSize(new Dimension(350, 45));

    JButton cancelar = new JButton("Cancelar");
    btnSalvarCancelar.add(cancelar);
    add(btnSalvarCancelar);

    cancelar.addActionListener(e -> abrirTela(new PainelPrincipal(voos, checkIn)));
  }

  private void abrirTela(JFrame tela) {
    dispose();
    tela.setVisible(true);
  }
}
