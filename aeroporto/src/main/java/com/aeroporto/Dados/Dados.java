package com.aeroporto.Dados;

import java.util.ArrayList;

import com.aeroporto.Voos.Voo;

public class Dados {
 // Lista que armazena todos os voos cadastrados
 private ArrayList<Voo> listaVoos = new ArrayList<>();

 // Getter da lista de voos
 public ArrayList<Voo> getListaVoos() {
  return listaVoos;
 }

 // Adiciona um voo à lista de voos
 public void adicionarVoos(Voo voo) {
  listaVoos.add(voo);
 }

 // Lista todos os voos cadastrados no sistema e imprime detalhes no console
 public ArrayList<Voo> listarVoos() {
  if (listaVoos.isEmpty()) {
   System.out.println("Nenhum voo cadastrado ainda."); // Mensagem caso não haja voos
  } else {
   for (Voo v : listaVoos) {
    System.out.println("------------------------------");
    System.out.println("Número do voo: " + v.getNumero());
    System.out.println("Origem: " + v.getOrigem());
    System.out.println("Destino: " + v.getDestino());
    System.out.println("Partida: " + v.getHorarioPartida());
    System.out.println("Chegada: " + v.getHorarioChegada());
    System.out.println("Assentos: " + v.getQuantidade());
    System.out.println("Assentos disponíveis: " + v.getAssentos()); // Lista de status dos assentos
   }
  }
  return listaVoos; // Retorna a lista completa de voos
 }

 // Retorna a quantidade de voos cadastrados (disponíveis no sistema)
 public int listarVoosDisponivel() {
  System.out.println(getListaVoos().size()); // Mostra no console o total de voos
  return getListaVoos().size();
 }
}
