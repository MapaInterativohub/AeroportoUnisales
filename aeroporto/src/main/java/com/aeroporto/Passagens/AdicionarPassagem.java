package com.aeroporto.Passagens;

import javax.swing.*;
import com.aeroporto.PainelPrincipal;
import com.aeroporto.Dados.Colors;
import com.aeroporto.Dados.Dados;
import com.aeroporto.Voos.Voo;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdicionarPassagem extends JFrame {
    Colors cor = new Colors(); // Classe para cores padrão
    private String poutrona; // Guarda o número do assento selecionado

    public AdicionarPassagem(Dados voos, Dados checkIn) {
        String titulo = "Adicionar Passagem";
        setTitle(titulo);
        setSize(400, 600);
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
        Titulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Largura expansível, altura fixa
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(2));
        add(Titulo);
        add(Box.createVerticalStrut(2));

        // Painel de status com imagem e contagem de voos disponíveis/indisponíveis
        JPanel PainelStatus = new JPanel(new BorderLayout());
        PainelStatus.setPreferredSize(new Dimension(350, 60));
        PainelStatus.setMaximumSize(new Dimension(350, 60));
        PainelStatus.setOpaque(true);
        PainelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        PainelStatus.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Imagem representativa
        ImageIcon icone = new ImageIcon("src\\main\\java\\com\\aeroporto\\Dados\\image.png");
        Image img = icone.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(img));
        PainelStatus.add(labelImagem, BorderLayout.WEST);

        // Painel de status em grid para labels
        JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 5));
        JLabel textDisponivel = new JLabel("Disponível:");
        JLabel Disponivel = new JLabel("0");

        // Conta voos totalmente indisponíveis
        int indisponivel = 0;
        for (Voo v : voos.listarVoos()) {
            int cont = 0;
            for (String vaga : v.getAssentos()) {
                if (vaga.equals("disponivel")) {
                    cont++;
                }
            }
            if (cont == 0) { // voo cheio
                indisponivel++;
            }
        }
        JLabel textIndisponivel = new JLabel("Indisponível:");
        JLabel Indisponivel = new JLabel("0");
        Indisponivel.setText(Integer.toString(indisponivel));
        Disponivel.setText(Integer.toString(voos.listarVoosDisponivel() - indisponivel));

        Indisponivel.setBackground(cor.getVermelho());
        Indisponivel.setOpaque(true);
        Disponivel.setBackground(cor.getVerde());
        Disponivel.setOpaque(true);
        statusPanel.setBackground(cor.getBranco());
        statusPanel.setOpaque(true);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        statusPanel.add(textDisponivel);
        statusPanel.add(Disponivel);
        statusPanel.add(textIndisponivel);
        statusPanel.add(Indisponivel);

        // Container para alinhar painel de status à direita
        JPanel containerDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        containerDireita.add(statusPanel);
        PainelStatus.add(Box.createHorizontalGlue());
        labelImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
        statusPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        PainelStatus.add(containerDireita, BorderLayout.CENTER);

        add(PainelStatus);
        add(Box.createVerticalStrut(2));

        // Painel de seleção do voo e data
        JPanel PaineSelecaoVoo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PaineSelecaoVoo.setPreferredSize(new Dimension(350, 250));
        PaineSelecaoVoo.setMaximumSize(new Dimension(350, 250));
        PaineSelecaoVoo.setBackground(cor.getCinza());
        PaineSelecaoVoo.setOpaque(true);
        PaineSelecaoVoo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textVoo = new JLabel("Voo:");
        textVoo.setPreferredSize(new Dimension(100, 15));

        // Preenche comboBox com números dos voos
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

        // Seletor de data da passagem
        JLabel textData = new JLabel("Data:");
        textData.setPreferredSize(new Dimension(100, 15));
        SpinnerDateModel modelo = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(modelo);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        PaineSelecaoVoo.add(textData);
        PaineSelecaoVoo.add(spinner);

        // Painel de assentos
        JPanel PainelAcentos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PainelAcentos.setPreferredSize(new Dimension(340, 200));
        PainelAcentos.setMaximumSize(new Dimension(340, 200));
        PainelAcentos.setBackground(cor.getCinzaEscuro());
        PainelAcentos.setOpaque(true);
        PainelAcentos.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona o scrollPane ao painel de seleção de voo (NÃO o painel interno)

        ButtonGroup grupoAssentos = new ButtonGroup(); // Garante seleção única

        // Atualiza botões de assento quando muda o voo selecionado
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String vooSelecionado = (String) comboBox.getSelectedItem();

                // Remove tudo do painel antes de reconstruir
                PainelAcentos.removeAll();

                // Busca o voo correspondente
                for (Voo v : voos.getListaVoos()) {
                    if (vooSelecionado.equals(v.getNumero())) {

                        for (int x = 1; x <= v.getQuantidade(); x++) {
                            JRadioButton assento = new JRadioButton(String.valueOf(x));
                            assento.setPreferredSize(new Dimension(60, 30));

                            if (!v.getAssentos().get(x - 1).equals("disponivel")) {
                                assento.setEnabled(false);
                            }

                            assento.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    poutrona = assento.getText();
                                }
                            });

                            grupoAssentos.add(assento);
                            PainelAcentos.add(assento);
                        }
                    }
                }

                // Atualiza o layout do Painel de Assentos
                PainelAcentos.revalidate();
                PainelAcentos.repaint();

                // Remove JScrollPane anterior (para evitar duplicar)
                for (Component c : PaineSelecaoVoo.getComponents()) {
                    if (c instanceof JScrollPane) {
                        PaineSelecaoVoo.remove(c);
                    }
                }

                // Cria e adiciona um novo JScrollPane
                JScrollPane scrollPane = new JScrollPane(PainelAcentos);
                scrollPane.setPreferredSize(new Dimension(340, 190));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                PaineSelecaoVoo.add(scrollPane, BorderLayout.CENTER);

                // 🔥 FORÇA O PAINEL PRINCIPAL A REATUALIZAR O LAYOUT
                PaineSelecaoVoo.revalidate();
                PaineSelecaoVoo.repaint();

                PainelAcentos.revalidate();
                PainelAcentos.repaint();
                scrollPane.getViewport().revalidate();
                scrollPane.getViewport().repaint();

                // 🔽 força rolar até o fim
                SwingUtilities.invokeLater(() -> {
                    JScrollBar barra = scrollPane.getVerticalScrollBar();
                    // posiciona no máximo real do momento
                    barra.setValue(barra.getMaximum());
                });
            }
        });

        PaineSelecaoVoo.add(PainelAcentos);
        add(PaineSelecaoVoo);
        add(Box.createVerticalStrut(2));

        // Painel para dados do passageiro
        JPanel PainelPassageiro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PainelPassageiro.setPreferredSize(new Dimension(350, 110));
        PainelPassageiro.setMaximumSize(new Dimension(350, 110));
        PainelPassageiro.setOpaque(true);
        PainelPassageiro.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos do passageiro
        JLabel labNome = new JLabel("Nome:");
        labNome.setPreferredSize(new Dimension(100, 15));
        JTextField textNome = new JTextField(20);
        PainelPassageiro.add(labNome);
        PainelPassageiro.add(textNome);

        JLabel labIdade = new JLabel("Idade:");
        labIdade.setPreferredSize(new Dimension(100, 15));
        JSpinner numIdade = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        numIdade.setPreferredSize(new Dimension(200, 23));
        PainelPassageiro.add(labIdade);
        PainelPassageiro.add(numIdade);

        JLabel labCpf = new JLabel("CPF:");
        labCpf.setPreferredSize(new Dimension(100, 15));
        JTextField textCpf = new JTextField(20);
        PainelPassageiro.add(labCpf);
        PainelPassageiro.add(textCpf);

        JLabel labEmail = new JLabel("E-mail:");
        labEmail.setPreferredSize(new Dimension(100, 15));
        JTextField textEmail = new JTextField(20);
        PainelPassageiro.add(labEmail);
        PainelPassageiro.add(textEmail);

        add(PainelPassageiro);
        add(Box.createVerticalStrut(2));

        // Painel com botões Salvar e Cancelar
        JPanel btnSalvarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvarCancelar.setPreferredSize(new Dimension(350, 45));
        btnSalvarCancelar.setMaximumSize(new Dimension(350, 45));
        JButton salvar = new JButton("Salvar");
        btnSalvarCancelar.add(salvar);

        // Evento do botão Salvar
        salvar.addActionListener(e -> {
            String nome = textNome.getText();
            String cpf = textCpf.getText();
            int idade = (int) numIdade.getValue();
            String email = textEmail.getText();

            // Validação básica dos campos
            if (nome.isBlank() || cpf.isBlank() || idade <= 0 || email.isBlank()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha todos os campos corretamente.",
                        "Campos obrigatórios",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            PainelAcentos.removeAll(); // Limpa painel de assentos

            // Localiza voo selecionado
            Voo vooSelecionado = null;
            for (Voo v : voos.getListaVoos()) {
                if (v.getNumero().equals(comboBox.getSelectedItem())) {
                    vooSelecionado = v;
                    break;
                }
            }

            if (vooSelecionado != null) {
                ArrayList<String> atualizacaoDeAssentos = vooSelecionado.getAssentos();
                int numeroAssento = Integer.parseInt(poutrona);

                // Atualiza assento com nome do passageiro
                if (numeroAssento > 0 && numeroAssento <= atualizacaoDeAssentos.size()) {
                    atualizacaoDeAssentos.set(numeroAssento - 1, nome);
                    vooSelecionado.setAssentos(atualizacaoDeAssentos);
                }
            }

            Passageiro p = new Passageiro(vooSelecionado, nome, cpf, idade, email, poutrona);
            vooSelecionado.addPassagensPendentes(p);

            PainelAcentos.revalidate();
            PainelAcentos.repaint();
            Disponivel.setText(Integer.toString(voos.listarVoosDisponivel()));
        });

        JButton cancelar = new JButton("Cancelar");
        btnSalvarCancelar.add(cancelar);
        add(btnSalvarCancelar);

        cancelar.addActionListener(e -> {
            abrirTela(new PainelPrincipal(voos, checkIn));
        });
    }

    // Método auxiliar para abrir outra tela e fechar esta
    private void abrirTela(JFrame tela) {
        dispose();
        tela.setVisible(true);
    }
}
