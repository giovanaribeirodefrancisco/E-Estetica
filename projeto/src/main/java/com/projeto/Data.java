//Feito por LeticiaMoraesG e Akino - Leila

package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class Data{
  public String data;

  public List<Horario> horariosDisponiveis;
  public List<Horario> horariosOcupados;

  public Data(String data) {
      this.data = data;
      this.horariosDisponiveis = new ArrayList<Horario>();
      this.horariosOcupados = new ArrayList<Horario>();
  }

  public String getData() {
      return data;
  }

  public List<Horario> getHorariosDisponiveis() {
      return horariosDisponiveis;
  }

  public List<Horario> getHorariosOcupados() {
      return horariosOcupados;
  }

  public void mostrarHorariosDisponiveis(){
    for(Horario horario: horariosDisponiveis){
      System.out.println(horario.getHora());
    }
  }

  public Horario buscarHorarioDisponivel(String hora){
    for(Horario horario: horariosDisponiveis){
      if(horario.getHora().equals(hora)){
        return horario;
      }
    }
    return null;
  }

  public void addHorario(Horario horario) {
      if (horario.isDisponivel()) {
          horariosDisponiveis.add(horario);
      } else {
          horariosOcupados.add(horario);
      }
  }

  public void addHorariosRange(String startTime, String endTime) {
      int startHour = Integer.parseInt(startTime.split(":")[0]);
      int endHour = Integer.parseInt(endTime.split(":")[0]);
      for (int hour = startHour; hour <= endHour; hour++) {
          String hora = String.format("%02d:00", hour);
          addHorario(new Horario(hora));
      }
  }

  public void mudarStatusHorario(Horario hora, boolean disponivel) {
      for (Horario horario : horariosDisponiveis) {
          if (horario.equals(hora)) {
              horario.setDisponivel(disponivel);
              if (!horario.isDisponivel()) {
                  horariosDisponiveis.remove(horario);
                  horariosOcupados.add(horario);
              }
              return;
          }
      }
      for (Horario horario : horariosOcupados) {
          if (horario.equals(hora)) {
              horario.setDisponivel(disponivel);
              if (horario.isDisponivel()) {
                  horariosOcupados.remove(horario);
                  horariosDisponiveis.add(horario);
              }
              return;
          }
      }
  }

}
