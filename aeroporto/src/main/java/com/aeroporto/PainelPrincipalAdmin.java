package com.aeroporto;

import com.aeroporto.Dados.Dados;
import javax.swing.*;

public class PainelPrincipalAdmin extends PainelPrincipal {
    public PainelPrincipalAdmin(Dados voos, Dados checkIn) {
        super(voos, checkIn);
        setTitle("Painel de Voo - Administração");

        // Esconde botão de compra
        for (JButton botao : getBotoesAdministrativos()) {
            if (botao.getText().equals("Comprar Passagem")) {
                botao.setVisible(false);
            }
        }
    }
}
