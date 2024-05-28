package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class Usuario{

  public List<Contratante> contratantes = new ArrayList<Contratante>();;
  public List<Prestador> prestadores = new ArrayList<Prestador>();

  public List<Contratante> getContratantes(){
    return contratantes;
  }

  public List<Prestador> getPrestadores(){
    return prestadores;
  }

  public void adicionaContratante(Contratante contratante){
    contratantes.add(contratante);
  }
  public void adicionaPrestador(Prestador prestador){
    prestadores.add(prestador);
  }


}
