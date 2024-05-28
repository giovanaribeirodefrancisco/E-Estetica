package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class PublicarServicoCtr{
  private List<Servico> servicosPublicados;

  public PublicarServicoCtr() {
    servicosPublicados = new ArrayList<>();
  }

  public List<Servico> getServicosPublicados(){
    return servicosPublicados;
  }

  public void adicionarServico(String nome, float valor, Prestador prestador){
    Servico servico = new Servico(nome, 50);
    servico.setValor(valor);
    prestador.addServico(servico);
    servicosPublicados.add(servico);
    
    
  }

  public void adicionarData(String dia, String inicio, String fim, String servico, Prestador prestador){

    Data data = new Data(dia);
    prestador.buscar(servico).agendas.addData(data);
    data.addHorariosRange(inicio, fim);
    
  }



}