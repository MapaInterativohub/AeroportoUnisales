package com.aeroporto.Voos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.aeroporto.Passagens.Passageiro;

public class Voo {
  private String numero;
  private String origem;
  private String destino;
  private String horarioPartida;
  private String horarioChegada;
  private int quantidade;

  // Lista de assentos do voo, armazenando o status "disponivel" ou outro
  private ArrayList<String> assentos = new ArrayList<>();

  // Lista de passageiros que fizeram reserva, mas ainda não foram confirmados
  private LinkedList<Passageiro> reservasPendentes = new LinkedList<>();

  // Pilha de passageiros que já fizeram check-in
  private Stack<Passageiro> checkIn = new Stack<>();

  public Stack<Passageiro> getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(Stack<Passageiro> checkIn) {
    this.checkIn = checkIn;
  }

  public Voo() {
    // Construtor padrão
  }

  // Construtor principal do voo
  public Voo(String numero, String origem, String destino, String horarioPartida, String horarioChegada,
      int quantidade) {
    this.numero = numero;
    this.origem = origem;
    this.destino = destino;
    this.horarioPartida = horarioPartida;
    this.horarioChegada = horarioChegada;
    this.quantidade = quantidade;

    // Inicializa todos os assentos como "disponivel"
    ArrayList<String> assentos = new ArrayList<>();
    for (int x = 0; x < quantidade; x++) {
      assentos.add("disponivel");
    }
    this.assentos = assentos;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getOrigem() {
    return origem;
  }

  public void setOrigem(String origem) {
    this.origem = origem;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public String getHorarioPartida() {
    return horarioPartida;
  }

  public void setHorarioPartida(String horarioPartida) {
    this.horarioPartida = horarioPartida;
  }

  public String getHorarioChegada() {
    return horarioChegada;
  }

  public void setHorarioChegada(String horarioChegada) {
    this.horarioChegada = horarioChegada;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public ArrayList<String> getAssentos() {
    return assentos;
  }

  public void setAssentos(ArrayList<String> assentos) {
    this.assentos = assentos;
  }

  public LinkedList<Passageiro> getReservasPendentes() {
    return reservasPendentes;
  }

  public void setReservasPendentes(LinkedList<Passageiro> reservasPendentes) {
    this.reservasPendentes = reservasPendentes;
  }

  // Adiciona um passageiro à lista de reservas pendentes
  public void addPassagensPendentes(Passageiro p) {
    reservasPendentes.add(p);
  }

  @Override
  public String toString() {
    // Retorna uma representação textual completa do voo, útil para debug
    return "Voo{" +
        "numero='" + numero + '\'' +
        ", origem='" + origem + '\'' +
        ", destino='" + destino + '\'' +
        ", horarioPartida='" + horarioPartida + '\'' +
        ", horarioChegada='" + horarioChegada + '\'' +
        ", quantidade=" + quantidade +
        ", assentos=" + assentos +
        '}';
  }
}
