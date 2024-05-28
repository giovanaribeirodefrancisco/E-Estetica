//Feito por giovanaribeirodefrancisco - Giovana Ribeiro de Francisco

package com.projeto;

import java.util.ArrayList;
import java.util.List;


public class CancelarReservaCtr{
  private List<Reserva> reservasCanceladas;

  public CancelarReservaCtr() {
      reservasCanceladas = new ArrayList<>();
  }

  public List<Reserva> FazerListaReservas(Contratante contratante){
    List<Reserva> reservasContratante = new ArrayList<>();

    for(Reserva reserva : contratante.getReservas()){
      if(reserva.getStatus().equals("Pendente") || reserva.getStatus().equals("Aguardando Confirmação"))
        reservasContratante.add(reserva);
    }
    return reservasContratante;
  }


  public void mostrarReservas(Contratante contratante){
    int num = 0;
    List<Reserva> reservasContratante = FazerListaReservas(contratante);
    for(Reserva reserva : reservasContratante){
      num++;
      System.out.println(num + " " + reserva.toString());
    }
  }

  public void mostrarReservasPre(Prestador prestador){
    int num = 0;
    for(Reserva reserva : prestador.reservasPendentes){
      System.out.println(num + " " + reserva.toString());
    }
  }

  public void cancelarReservaCo(Contratante contratante, int reservaIndex) {
    List<Reserva> reservasContratante = FazerListaReservas(contratante);
    Reserva reserva = reservasContratante.get(reservaIndex);
    reserva.setStatus("Cancelada");
    reserva.getPrestador().notificar(contratante.getNome() + " cancelou a reserva de " + reserva.getServico().getNome() + " para " + reserva.getData().getData() + " às " + reserva.getHorario().getHora());
  reserva.getData().mudarStatusHorario(reserva.getHorario(), true);
    reserva.getPrestador().removerReservaPendente(reserva);
    contratante.setSaldo(reserva.getValor()+contratante.getSaldo());
    reservasCanceladas.add(reserva);


  }

  public void cancelarReservaPre(Prestador prestador, int reservaIndex) {
      Reserva reserva = prestador.getReservasPendentes().get(reservaIndex);
      reserva.setStatus("Cancelada");
      reserva.getContratante().notificar(prestador.getNome() + " cancelou a reserva de " + reserva.getServico().getNome() + " para " + reserva.getData().getData() + " às " + reserva.getHorario().getHora());
      reserva.getData().mudarStatusHorario(reserva.getHorario(), true);
      prestador.removerReservaPendente(reserva);
      reserva.getContratante().setSaldo(reserva.getValor()+reserva.getContratante().getSaldo());
      reservasCanceladas.add(reserva);



  }

}
