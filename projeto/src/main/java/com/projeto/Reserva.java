package com.projeto;


public class Reserva{
  public Contratante contratante;
  public Prestador prestador;
  public Horario horario;
  public double valor;
  public Servico servico;
  public Data data;
  public String status;

  public Reserva(){
    this.contratante = new Contratante();
    this.prestador = new Prestador();
    this.horario = null;
    this.valor = 0;  
    this.data = null;
    this.servico = null;
    this.status = "Pendente";
  }
  public Reserva(Contratante contratante, Prestador prestador, Horario horario, double valor, Data data, Servico servico){
    this.contratante = contratante;
    this.prestador = prestador;
    this.horario = horario;
    this.servico = servico;
    this.valor = valor;
    this.data = data;
    this.status = "Pendente";
  }

  public void setContratante(Contratante contratante){
    this.contratante = contratante;
  }
  public void setPrestador(Prestador prestador){
    this.prestador = prestador;
  }
  public void setHorario(Horario horario){
    this.horario = horario;
  }
  public void setValor(double valor){
    this.valor = valor;
  }
  public void setData(Data data){
    this.data = data;
  }
  public void setStatus(String status){
    this.status = status;
  }
  public void setServico(Servico servico){
    this.servico = servico;
  }
  public Contratante getContratante(){
    return contratante;
  }
  public Prestador getPrestador(){
    return prestador;
  }
  public Horario getHorario(){
    return horario;
  }
  public double getValor(){
    return valor;
  }
  public Data getData(){
    return data;
  }
  public Servico getServico(){
    return servico;
  }
  public String getStatus(){
    return status;
  }

  public void mudarStatus(String status){
    this.status = status;//Redundante

  }

  @Override
  public String toString(){
    return "Contratante: " + contratante.getNome() + "\nPrestador: " + prestador.getNome()+"\nServico: "+ servico.getNome() + "\nHorario: " + horario.getHora() + "\nValor: " + valor + "\nData: " + data.getData() + "\nStatus: " + status;
  }
}
