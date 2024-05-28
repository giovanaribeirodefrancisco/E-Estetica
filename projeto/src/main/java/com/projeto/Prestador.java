//PRESTADOR FEITO POR Akina-Ino - Leila
package com.projeto;

import java.util.ArrayList;
import java.util.List;



public class Prestador{
   public String nome;
   public float avaliacao;
   public List<String> notificacoes;
   public List<Reserva> reservasPendentes;
   public List<Servico> servicos;


  public String getNome(){
    return nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public List<Servico> getServicos(){
    return servicos;
  }

  public List<Reserva> getReservasPendentes(){
    return reservasPendentes;
  }

  public List<String> getNotificacoes(){
    return notificacoes;
  }

  public float getAvaliacao(){
    return avaliacao;
  }

  public void setAvaliacao(float avaliacao){
    this.avaliacao = avaliacao;
  }

   public Prestador(){
     nome = "Não informado";
     avaliacao = 0;

     servicos = new ArrayList<Servico>();
     notificacoes = new ArrayList<String>();
     reservasPendentes = new ArrayList<Reserva>();
   }
   public Prestador(String nome, float avaliacao){
     this.nome = nome;
     this.avaliacao = avaliacao;
     servicos = new ArrayList<Servico>();
     notificacoes = new ArrayList<String>();
     reservasPendentes = new ArrayList<Reserva>();
   }
   public void addServico(Servico servico){
     if(buscar(servico.getNome())!=null){
       System.out.println("Serviço ja existente");
     } else
       servicos.add(servico);
   }

   public void notificar(String notificacao){
     notificacoes.add(notificacao);
   }  

   public void addReservaPendente(Reserva reserva){
     reservasPendentes.add(reserva);
   }

   public void removerReservaPendente(Reserva reserva){
     reservasPendentes.remove(reserva);
   }

   public Reserva buscarReservaPendente(Reserva reserva){
     for(Reserva r: reservasPendentes){
       if(r.equals(reserva)){
         return r;
       }
     }
     return null;
   }

  public Servico buscar(String servico){
    for (Servico s : servicos) {
      if (s.getNome().equals(servico)) {
        return s;
      }
    }
    return null;
  }

}
