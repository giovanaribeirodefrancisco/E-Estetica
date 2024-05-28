//SERVIÇO FEITO POR Akina-Ino - Leila
package com.projeto;


public class Servico{
  public Agenda agendas;
  public String nome;
  public double valor;

  public String getNome(){
    return nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public Agenda getAgendas(){
    return agendas;
  }

  public double getValor(){
    return valor;
  }

  public void setValor(double valor){
    this.valor = valor;
  }

  public Servico(){
    this.agendas = new Agenda();
    this.nome = "Não informado";
    this.valor = 0;
  }

  public Servico(String nome, float valor){
    this.nome = nome;
    this.agendas = new Agenda();
    this.valor = valor;
  }

  public void addData(Data data){
    if(buscarData(data.getData())!=null)
    agendas.addData(data);
  }
  public Data buscarData(String data){
    for(Data d: agendas.getDatas()){
      if(d.getData().equals(data)){
        return d;
      }
    }
    return null;

  }
}
