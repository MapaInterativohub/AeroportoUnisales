package com.aeroporto;

import com.aeroporto.Dados.Dados;
import javax.swing.*;

public class PainelPrincipalCliente extends PainelPrincipal {
    public PainelPrincipalCliente(Dados voos, Dados checkIn) {
        super(voos, checkIn);
        setTitle("Painel de Voo - Cliente");

        // Esconde botões do administrador
        for (JButton botao : getBotoesAdministrativos()) {
            if (!botao.getText().equals("Comprar Passagem")) {
                botao.setVisible(false);
            }
        }
    }
}
