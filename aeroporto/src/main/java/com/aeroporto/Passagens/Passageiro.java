package com.aeroporto.Passagens;

import com.aeroporto.Voos.Voo;

public class Passageiro {
    // Referência ao voo que o passageiro vai embarcar
    private Voo voo;

    // Dados pessoais do passageiro
    private String nome;
    private String cpf;
    private int idade;
    private String email;

    // Número do assento escolhido pelo passageiro
    private String acento;

    // Status da compra: "aguardando", "confirmada", "cancelada"
    private String statusDeCompra = "aguardando";

    // Status do check-in: "aguardando" ou "realizado"
    private String checkInRelisado = "aguardando";

    // Construtor principal, inicializa os dados do passageiro e o voo
    public Passageiro(Voo voo, String nome, String cpf, int idade, String email, String acento) {
        this.voo = voo;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.acento = acento;
    }

    // Getters e Setters para acesso e alteração dos campos

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public String getAcento() {
        return acento;
    }

    public void setAcento(String acento) {
        this.acento = acento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusDeCompra() {
        return statusDeCompra;
    }

    public void setStatusDeCompra(String statusDeCompra) {
        this.statusDeCompra = statusDeCompra;
    }

    public String getCheckInRelisado() {
        return checkInRelisado;
    }

    public void setCheckInRelisado(String checkInRelisado) {
        this.checkInRelisado = checkInRelisado;
    }

    // Sobrescrevendo toString para facilitar debug e exibição de informações do
    // passageiro
    @Override
    public String toString() {
        return "Passageiro {" +
                "Voo='" + voo.getNumero() + ": " + voo.getDestino() + '\'' +
                "Nome='" + nome + '\'' +
                ", CPF='" + cpf + '\'' +
                ", Idade=" + idade +
                ", Email='" + email + '\'' +
                ", Assento='" + acento + '\'' +
                '}';
    }
}
