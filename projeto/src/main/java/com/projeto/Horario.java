package com.projeto;

public class Horario{
  public String hora;
  public boolean disponivel;

  public Horario(String hora) {
      this.hora = hora;
      this.disponivel = true;
  }

  public String getHora() {
      return hora;
  }

  public boolean isDisponivel() {
      return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
      this.disponivel = disponivel;
  }

}

