package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class ReservarCtr{
  private List<Reserva> reservas;

  public ReservarCtr() {
      reservas = new ArrayList<>();
  }

  public Contratante acharContratante(String nome, Usuario usuario){
    for(Contratante contratante : usuario.contratantes){
      if(contratante.getNome().equals(nome)){
        return contratante;
      }
    }
    return null;
  }

  public Prestador acharPrestador(String nome, Usuario usuario){
      for(Prestador prestador : usuario.prestadores){
        if(prestador.getNome().equals(nome)){
          return prestador;
        }
      }
      return null;
    }

  public List<Data> datasDisponiveis(String nome, Prestador prestador){
    for(Servico servico : prestador.servicos){
      if (servico.getNome().equals(nome)){ 
        return servico.agendas.getDatas();
      }
    }
    return null;
  }

  public void mostrarDatasDisponiveis(String servico, Prestador prestador){
    List<Data> lista = datasDisponiveis(servico, prestador);
    for(Data data: lista){
      System.out.println(data.getData());
    }
  }

  public void mostrarHorariodisponiveis(String servico, Data data, Prestador prestador){
  prestador.buscar(servico).getAgendas().getData(data).mostrarHorariosDisponiveis();
  }

  public Data acharData(String data, String servico, Prestador prestador){
    return prestador.buscar(servico).buscarData(data);
  }

  public Servico buscarServico(String nome, Prestador prestador){
    return prestador.buscar(nome);
  }

  public List<Prestador> PrestadoresComServico (String servico, Usuario usuario ){
    List<Prestador> prestadoresServico = new ArrayList<Prestador>();
    for(Prestador prestador : usuario.prestadores){
      if(prestador.buscar(servico) != null){
        prestadoresServico.add(prestador);
      }
    }
    return prestadoresServico;
  }

  public void criarReserva(Contratante contratante, Prestador prestador, Servico servico, Data data, Horario horario, String forma) {
      Reserva reserva = new Reserva(contratante, prestador, horario, servico.getValor(), data, servico);
      contratante.pagamento(servico.getValor(), forma);
      reserva.setStatus("Aguardando Confirmação");
      prestador.addReservaPendente(reserva);
      contratante.addReserva(reserva);
      reservas.add(reserva);
      reserva.getData().mudarStatusHorario(reserva.getHorario(), false);
      

      prestador.notificar("Reserva de " + servico.getNome() + " para " + data.getData() + " às " + horario.getHora());
      System.out.println("\nAguardando confirmação do prestador");
  }


  public List<Reserva> getReservas() {
      return reservas;
  }

}