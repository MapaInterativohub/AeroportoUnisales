package com.aeroporto;

import javax.swing.SwingUtilities;

import com.aeroporto.Dados.Dados;
import com.aeroporto.Passagens.Passageiro;
import com.aeroporto.Voos.Voo;

public class Main {
    public static void main(String[] args) {
        Dados voos = new Dados();
        Dados checkIn = new Dados();

        Voo teste1 = new Voo("0142", "NULL", "NULL", "NULL", "NULL", 4);
        Voo teste2 = new Voo("5693", "NULL2", "NULL2", "NULL2", "NULL2", 100);

        Passageiro p1 = new Passageiro(teste1, "p1", "234", 3, "tt", "3");
        teste1.addPassagensPendentes(p1);

        Passageiro p2 = new Passageiro(teste1, "p2", "2342", 1, "tt2", "1");
        teste1.addPassagensPendentes(p2);

        voos.adicionarVoos(teste1);
        voos.adicionarVoos(teste2);

        SwingUtilities.invokeLater(() -> new PainelPrincipal(voos, checkIn));
    }
}