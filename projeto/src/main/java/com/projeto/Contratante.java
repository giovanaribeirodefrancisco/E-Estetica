package com.projeto;

import java.util.ArrayList;
import java.util.List;


public class Contratante{
   public String nome;
   public float avaliacao;
   public double saldo;
   public List<Reserva> reservas;
   public List<String> notificacoes;

  public String getNome(){
    return nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public double getSaldo(){
    return saldo;
  }

  public void setSaldo(double valor){
    this.saldo = valor;
  }

  public List<Reserva> getReservas(){
    return reservas;
  }

  public float getAvaliacao(){
    return avaliacao;
  }

  public void setAvaliacao(float avaliacao){
    this.avaliacao = avaliacao;
  }

   public Contratante(){
     nome  = "Não informado";
     avaliacao = 0;
     saldo = 0;
     reservas = new ArrayList<Reserva>();
     notificacoes = new ArrayList<String>();
   }

   public Contratante(String nome, float avaliacao){
     this.nome = nome;
     this.avaliacao = avaliacao;
     saldo = 0;
     reservas = new ArrayList<Reserva>();
     notificacoes = new ArrayList<String>();
   }

   public void addReserva(Reserva reserva){
     reservas.add(reserva);
   }


  public void notificar(String notificacao){
     notificacoes.add(notificacao);
   }  

  public void pagamento(double valor, String forma){
    saldo -= valor;
    System.out.println("Pagamento de " + valor + " em " + forma + " feito\n");
  }

}
