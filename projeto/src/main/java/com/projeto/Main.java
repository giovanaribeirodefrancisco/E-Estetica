package com.projeto;


import java.util.List;

public class Main {

    public static void main(String[] args) {
      

      ReservarCtr reservar = new ReservarCtr();
      CancelarReservaCtr cancelarReserva = new CancelarReservaCtr();
      PublicarServicoCtr publicarServico = new PublicarServicoCtr();
      
      Prestador p1 = new Prestador("João", 5);
      publicarServico.adicionarServico("Manicure", 50, p1);
      publicarServico.adicionarData("22/05", "8:00", "11:00", "Manicure", p1);
      
      
      Contratante c1 = new Contratante("Carlos", 5);
      Contratante c2 = new Contratante("Maria", 4);
      Usuario usuario = new Usuario(); // lista de contratantes/prestador (usuario)
      //
      usuario.adicionaContratante(c1);
      usuario.adicionaContratante(c2);
      usuario.adicionaPrestador(p1);
      int a = 0;
      //Login
      while(a != 3){
      System.out.println("1 - Login Contratante");
      System.out.println("2 - Login Prestador");
      System.out.println("3 - Sair");
      System.out.println("Digite a opção:");
      int i = Integer.parseInt(System.console().readLine());
      if(i == 1){
        System.out.println("\nDigite o nome: ");
        String usuarioDigitado = System.console().readLine();
      //input do usuario
      for(Contratante contratante : usuario.contratantes){
        if (contratante.getNome().equals(usuarioDigitado)){
          int inputMenu = 0;
          
            
            while(inputMenu != 4){
              System.out.println("\n1 - Reservar");
              System.out.println("2 - Cancelar");
              System.out.println("3 - Ver Notificações ("+ contratante.notificacoes.size()+")");
              System.out.println("4 - Sair");
              System.out.println("Digite a opção:");
              inputMenu = Integer.parseInt(System.console().readLine());
              if(inputMenu == 1){
                //RESERVAR FEITO POR LeticiaMoraesG - Letícia
                  
                System.out.println("\nServicos Publicados:");
                for(Servico servico : publicarServico.getServicosPublicados())
                System.out.print(servico.getNome() + "\n");
                System.out.println("\nDigite o nome do servico: ");
                String inputservico = System.console().readLine();
                //Faz uma lista de prestadores com o serviço buscado
            List<Prestador> prestadoresServico = reservar.PrestadoresComServico(inputservico, usuario);
            System.out.println("\nLista de prestadores: ");
            for(Prestador prestador : prestadoresServico){
              System.out.println(prestador.getNome());
            }
            System.out.println("\nDigite o nome do prestador: ");
            String inputPrestador = System.console().readLine();
            // Acha o prestador escolhido na lista de prestadores 
            Prestador prestadorescolhido = reservar.acharPrestador(inputPrestador, usuario);
                Servico servicoEscolhido = reservar.buscarServico(inputservico, prestadorescolhido);
                  //Mostra as datas disponíveis do serviço
                System.out.println("\nDatas disponíveis: ");
                reservar.mostrarDatasDisponiveis(inputservico, prestadorescolhido);
                    System.out.println("\nDigite a data: ");
                    String inputData = System.console().readLine();
                    Data dataescolhida = reservar.acharData(inputData, inputservico, prestadorescolhido);
                    
                      if(dataescolhida != null){ 
                        //Mostra os horários disponíveis da data
                        System.out.println("\nHorários Disponíveis: ");
                        reservar.mostrarHorariodisponiveis(inputservico, dataescolhida, prestadorescolhido);
                        System.out.println("\nDigite o horario: ");
                        String inputHorario = System.console().readLine();
                        Horario horarioescolhido = dataescolhida.buscarHorarioDisponivel(inputHorario);
                        
                        if(horarioescolhido != null){
                          //Fazer o pagamento
                          System.out.println("\nÁ pagar: "+ servicoEscolhido.getValor()+ "\n\nFormas de pagamento:\nCartão\nPix");
                          System.out.println("\nDigite a forma de pagamento: ");
                          String formaPagamento = System.console().readLine();

                            //Reservar
                           reservar.criarReserva(contratante, prestadorescolhido, servicoEscolhido, dataescolhida, horarioescolhido, formaPagamento);
                        }
                      }
                  }

            //Cancelar Reserva feito por giovanaribeirodefrancisco - Giovana Ribeiro de Francisco
              else if(inputMenu == 2){
                cancelarReserva.mostrarReservas(contratante);
                System.out.println("\nDigite o numero da reserva: ");
                int inputReserva = Integer.parseInt(System.console().readLine());
                cancelarReserva.cancelarReservaCo(contratante, inputReserva - 1);
  
                
              }
              else if(inputMenu == 3){
                for (String notificacao : contratante.notificacoes) {
                  System.out.println(notificacao);
                }
                contratante.notificacoes.clear();
              }
              else{
                break;
              }
            }
          break;
        }
      }
      
      
      }else if(i == 2){
        System.out.println("\nDigite o nome: ");
          String usuarioDigitado = System.console().readLine();
        //input do usuario
        for(Prestador prestador : usuario.prestadores){
          if (prestador.getNome().equals(usuarioDigitado)){
  
            int inputMenu = 0;
              //Menu Cancelar Reserva 
              while(inputMenu != 4){
                System.out.println("\n1 - Publicar Serviço");
                System.out.println("2 - Cancelar");
                System.out.println("3 - Ver Notificações ("+ prestador.notificacoes.size()+")");
                System.out.println("4 - Sair");
                inputMenu = Integer.parseInt(System.console().readLine());
                if(inputMenu == 1){
                  System.out.println("\nDigite o nome do serviço: ");
                  String inputservico = System.console().readLine();
                  System.out.println("\nDigite o valor: ");
                  float valor = Float.parseFloat(System.console().readLine());
                  publicarServico.adicionarServico(inputservico, valor, prestador);
                  System.out.println("\nDigite a quantidade de dias: ");
                  int inputDias = Integer.parseInt(System.console().readLine());
                  for(int j = 0; j < inputDias; j++){
                  System.out.println("\nDigite a data: ");
                  String inputData = System.console().readLine();
                  
                  System.out.println("\nDigite o horario de inicio (00:00): ");
                  String inputHorario = System.console().readLine();
                  System.out.println("\nDigite o horario de fim (00:00): ");
                  String inputHorarioFim = System.console().readLine();
                  
                  publicarServico.adicionarData(inputData, inputHorario, inputHorarioFim, inputservico, prestador);
                  }
                  
                  System.out.println("\nServiço publicado com sucesso!");
  
                   
                } else if(inputMenu == 2){
                  //Mostrar Reservas do Prestador
                  cancelarReserva.mostrarReservasPre(prestador);
                  System.out.println("\nDigite o numero da reserva: ");
                  int inputReserva = Integer.parseInt(System.console().readLine());
                  //Cancelar Reserva do Prestador
                  cancelarReserva.cancelarReservaPre(prestador, inputReserva - 1);

                  System.out.println("\nReserva cancelada com sucesso");
  
                  
                } else if(inputMenu == 3){

                // Notificar prestador
                  for (String notificacao : prestador.notificacoes) {
                    System.out.println(notificacao);
                  }
                  prestador.notificacoes.clear();
                  
                }else{
                  break;
                }
                
              }
  
              
            
          }
          
        }
        
      }else
      break;
      
    }
    
  
    }
  
   
    
  }
  
