/**
 * Classe agregadora de todo o projeto
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;

public class Hipermercado implements Serializable{

  private static Menu menuPrincipal, menuCarregarFicheiros, menuEstatisticas, menuEstatisticas12, menuQueriesInterativas, menuCarregarGuardar 
    , menuCarregarProdutos , menuCarregarClientes , menuCarregarCompras ;

  private static StringsMenu stringMenu = new StringsMenu();
  private static CatalogoClientes catalogoClientes = new CatalogoClientes();
  private static boolean flagEstruturaClientesCarregada = false;
  private static CatalogoProdutos catalogoProdutos = new CatalogoProdutos();
  private static boolean flagEstruturaProdutosCarregada = false;
  private static Contabilidade contabilidade = new Contabilidade();
  private static Compras compras = new Compras ();
  private static boolean flagEstruturaComprasCarregada = false;
  private static boolean necessitaLimparEstruturaCompras = false;
  private static ComprasInvalidas invalidas = new ComprasInvalidas();
  private static Scanner scannerMain = new Scanner(System.in);
  private static ParserCompras parserCompras = null;
  
  private static String standardPathFicheiroProdutos = "files/FichProdutos.txt";
  private static String standardPathFicheiroClientes = "files/FichClientes.txt";
  private static String pathFicheiroComprasStandard = "files/Compras.txt";
  private static String pathFicheiroCompras1 = "files/Compras1.txt";
  private static String pathFicheiroCompras3 = "files/Compras3.txt";

  /**
   * numero de linhas a serem mostradas pelo paginador
   */
  private static int linhasHorizontais = 20;

  private static void carregaMenus(){
    menuPrincipal = new Menu(stringMenu.getOpcoesMenuPrincipal());
    menuCarregarFicheiros = new Menu(stringMenu.getOpcoesMenuCarregarFicheiros());
    menuCarregarProdutos = new Menu(stringMenu.getOpcoesMenuCarregarProdutos());
    menuCarregarClientes = new Menu(stringMenu.getOpcoesMenuCarregarClientes());
    menuCarregarCompras = new Menu(stringMenu.getOpcoesMenuCarregarCompras());
    menuQueriesInterativas = new Menu(stringMenu.getOpcoesMenuQueriesInterativas());
    menuCarregarGuardar = new Menu(stringMenu.getOpcoesMenuCarregarGuardar());
    menuEstatisticas = new Menu(stringMenu.getOpcoesMenuEstatisticas());
    menuEstatisticas12 = new Menu(stringMenu.getOpcoesMenuEstatisticas12());
  }

  private static void limpaEcran(){
    System.out.print('\u000C');
    if (necessitaLimparEstruturaCompras){
      StringBuilder aviso = new StringBuilder();
      aviso.append("/********************************************************/\n");
      aviso.append("/*    Aviso: Necessita Re-carregar ficheiro Compras     */\n");
      aviso.append("/*   Motivo: Carregou ficheiro de Produtos ou Clientes  */\n");
      aviso.append("/*           após ter carregado ficheiro de Compras     */\n");
      aviso.append("/********************************************************/\n");
      System.out.println(aviso.toString());
    }

  }

  public static void limpaEstruturaProdutos(){
    if ( flagEstruturaProdutosCarregada ){
      catalogoProdutos = new CatalogoProdutos();
      flagEstruturaProdutosCarregada = false;
      if ( flagEstruturaComprasCarregada ){
        necessitaLimparEstruturaCompras = true;
      }
    }
  }

  public static void limpaEstruturaClientes(){
    if ( flagEstruturaClientesCarregada ){
      catalogoClientes = new CatalogoClientes();
      flagEstruturaClientesCarregada = false;
      if ( flagEstruturaComprasCarregada ){
        necessitaLimparEstruturaCompras = true;
      }
    }
  }

  public static void limpaEstruturaCompras(){
    if ( flagEstruturaComprasCarregada ){
      contabilidade = new Contabilidade();
      compras = new Compras ();
      invalidas = new ComprasInvalidas();
      flagEstruturaComprasCarregada = false;
      necessitaLimparEstruturaCompras = false;
    }
  }

  private static void mainMenu(){
    carregaMenus();
    int opcao = -1;
    while (opcao != 0 ){
      limpaEcran();
      menuPrincipal.executa();
      switch(opcao = menuPrincipal.getOpcao()){
        case 1 :
          limpaEcran();
          carregarFicheiros();
          break;
        case 2 :
          limpaEcran();
          menuEstatisticas();
          break;
        case 3 :
          limpaEcran();
          queriesInterativas();
          break;
        case 4 :
          limpaEcran();
          carregarGuardarPrograma();
      }
    }
  }

  private static void carregarFicheiros(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarFicheiros.executa();
      switch(opcao = menuCarregarFicheiros.getOpcao()){
        case 1 :
          limpaEcran();
          carregarProdutos();
          break;
        case 2 :
          limpaEcran();
          carregarClientes();
          break;
        case 3 :
          limpaEcran();
          carregarCompras();
          break;
        case 4 :
          mainMenu();
          break;
      }
    }
  }

  private static void carregarProdutos(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarProdutos.executa();
      switch(opcao = menuCarregarProdutos.getOpcao()){
        case 1 :
          limpaEcran();
          handlerCarregarProdutos( standardPathFicheiroProdutos );
          break;
        case 2 :
          limpaEcran();
          System.out.println("Insira o nome do ficheiro de produtos:");
          String nomeFicheiro = new String();
          nomeFicheiro = Input.lerString();
          handlerCarregarProdutos( nomeFicheiro );
          break;
        case 3 :
          carregarFicheiros();
          break;
      }
    }
  }

  private static void carregarClientes(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarClientes.executa();
      switch(opcao = menuCarregarClientes.getOpcao()){
        case 1 :
          limpaEcran();
          handlerCarregarClientes( standardPathFicheiroClientes );
          break;
        case 2 :
          limpaEcran();
          System.out.println("Insira o nome do ficheiro de clientes:");
          String nomeFicheiro = new String();
          nomeFicheiro = Input.lerString();
          handlerCarregarClientes( nomeFicheiro );
          break;
        case 3 :
          carregarFicheiros();
          break;
      }
    }
  }

  private static void carregarCompras(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarCompras.executa();
      switch(opcao = menuCarregarCompras.getOpcao()){
        case 1 :
          limpaEcran();
          handlerCarregarCompras( pathFicheiroComprasStandard );
          break;
        case 2 :
          limpaEcran();
          handlerCarregarCompras( pathFicheiroCompras1 );
          break;
        case 3 :
          limpaEcran();
          handlerCarregarCompras( pathFicheiroCompras3 );
          break;
        case 4 :
          limpaEcran();
          System.out.println("Insira o nome do ficheiro de compras:");
          String nomeFicheiro = new String();
          nomeFicheiro = Input.lerString();
          handlerCarregarCompras( nomeFicheiro );
          break;
        case 5 :
          carregarFicheiros();
          break;
      }
    }
  }
  
  private static void menuEstatisticas(){
    int opcao = -1;
    while (opcao != 0 ){
        limpaEcran();
        menuEstatisticas.executa();
        switch(opcao = menuEstatisticas.getOpcao()){
            case 1 :
            {
                limpaEcran();
                System.out.println(querie11());
                Menu.esperaReturn();
                break;
            }
            case 2 :
                limpaEcran();
                menuEstatisticas12();
                break;
        }
    }
  }
  
  private static void menuEstatisticas12(){
      int opcao = -1;
    while (opcao != 0 ){
        limpaEcran();
        menuEstatisticas12.executa();
        switch(opcao = menuEstatisticas12.getOpcao()){
            case 1:
            {
                ArrayList <String> paginas = compras.estatisticas_1_2_P1 ();
                paginador(paginas, "Número total de compras por mês (não é a facturação)", "",false,false);
                break;
            }
            case 2: 
            {
                ArrayList <String> paginas = contabilidade.estatisticas_1_2_P2 ();
                paginador(paginas, "Facturação total por mês (valor total das compras/vendas) e total global", "",false,false);
            break;
            }
            case 3:
            {
                ArrayList <String> paginas = compras.estatisticas_1_2_P3 ();
                paginador(paginas, "Número de distintos clientes que compraram em cada mês", "",false,false);
                break;
            }
            case 4:
            {
                ArrayList <String> paginas = invalidas.estatisticas_1_2_P4 ();
                paginador(paginas, "Total de registos de compras inválidos", "",false,false);
                System.out.println("Pretende gravar os registos de compras em ficheiro? (s/n)");
                String gravarFicheiro= Input.lerString();
                if ( gravarFicheiro.equals("s") || gravarFicheiro.equals("S")){
                                System.out.println("Indique o nome do ficheiro:");
                                String nomeFicheiro= Input.lerString();
                                try {
                                invalidas.gravaComprasInvalidasTXT(nomeFicheiro);
                                System.out.println("Ficheiro gravado com sucesso!");
                            }
                                 catch (IOException e ){ 
      System.out.println("Erro ao gravar ficheiro!");            
      System.out.println(e.getMessage());
    }
                            }
                                Menu.esperaReturn();
                                                break;
                            }

            }
            
        }
    }

  private static void queriesInterativas(){
    int opcao = -1;
    while (opcao != 0 ){
      limpaEcran();
      menuQueriesInterativas.executa();
      switch(opcao = menuQueriesInterativas.getOpcao()){
        case 1 :
          { 
            Crono.start();
            ArrayList <String> resultadoQuerie1 = contabilidade.querie1(catalogoProdutos);
            Crono.stop();
            String tempoComputacao1 = new String();
            tempoComputacao1=Crono.print();
            paginador(resultadoQuerie1 , "Lista ordenada com os códigos dos produtos nunca comprados e respectivo total ", "Codigo Produto",true,true,tempoComputacao1);
            break;
          }
        case 2 :
          {
            Crono.start();
            ArrayList <String> resultadoQuerie2 = compras.querie2(catalogoClientes);
            Crono.stop();
            String tempoComputacao2 = new String();
            tempoComputacao2=Crono.print();
            paginador(resultadoQuerie2,"Lista ordenada com os códigos dos clientes que nunca compraram e seu total","Código Cliente",true,true,tempoComputacao2);
            break;
          }
        case 3 :
          {
            limpaEcran();
            System.out.println("Indique um mes [1-12]:");
            int mes = Input.lerInt();
            Crono.start();
            ArrayList <String> resultadoQuerie3 = compras.querie3(mes);
            Crono.stop();
            String tempoComputacao3 = new String();
            tempoComputacao3=Crono.print();
            paginador(resultadoQuerie3,"Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram","Total Compras\tClientes Distintos",false,false,tempoComputacao3);
            break;
          }
        case 4 :
          {
            limpaEcran();
            System.out.println("Indique um codigo de cliente válido (Ex: AA000):");
            String codigoCliente= Input.lerString();
            Crono.start();
            ArrayList <String> resultadoQuerie4 = compras.querie4(codigoCliente);
            Crono.stop();
            String tempoComputacao4 = new String();
            tempoComputacao4=Crono.print();
            paginador(resultadoQuerie4,"Dado um código de cliente, determinar, para cada mês, quantas compras fez,quantos produtos distintos comprou e quanto gastou. Apresentar também o total anual facturado ao cliente","",false,true,tempoComputacao4);
            break;
          }
        case 5 :
          {
            limpaEcran();
            System.out.println("Indique um codigo de produto válido (Ex: AA0000):");
            String codigoProduto= Input.lerString();
            String descricao = new String();
            descricao = "Produto em análise: " + codigoProduto;
            Crono.start();
            ArrayList <String> resultadoQuerie5 = contabilidade.querie5(codigoProduto);
            Crono.stop();
            String tempoComputacao5 = new String();
            tempoComputacao5=Crono.print();
            paginador(resultadoQuerie5,"Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado",descricao,false,true,tempoComputacao5);
            break;
          }
        case 6 :
          {
            limpaEcran();
            System.out.println("Indique um codigo de produto válido (Ex: AA0000):");
            String codigoProd= Input.lerString();
            String descricao = new String();
            descricao = "Produto em análise: " + codigoProd;
            Crono.start();
            ArrayList <String> resultadoQuerie6 = contabilidade.querie6(codigoProd);
            Crono.stop();
            String tempoComputacao6 = new String();
            tempoComputacao6=Crono.print();
            paginador(resultadoQuerie6,"Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado em modo N e em modo P e respectivas facturações",descricao,false,true,tempoComputacao6);
            break;
          }
        case 7:
          {
            limpaEcran();
            System.out.println("Indique um codigo de cliente válido (Ex: AA000):");
            String codigoCliente= Input.lerString();
            String descricao = new String();
            descricao = "Cliente em análise: " + codigoCliente;
            Crono.start();
            ArrayList <String> resultadoQuerie7 = compras.querie7(codigoCliente);
            Crono.stop();
            String tempoComputacao7 = new String();
            tempoComputacao7=Crono.print();
            paginador(resultadoQuerie7,"Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou (e quantos), ordenada por ordem decrescente de quantidade e, para quantidades iguais, por ordem alfabética dos códigos",descricao,false,true,tempoComputacao7);
            break;
          }
        case 8:
        {
            limpaEcran();
            System.out.println("Indique o numero de unidades vendidas que deseja consultar:");
            int numero= Input.lerInt();
            Crono.start();
            ArrayList <String> resultadoQuerie8 = contabilidade.querie8(numero);
            Crono.stop();
            String tempoComputacao8 = new String();
            tempoComputacao8=Crono.print();
            paginador( resultadoQuerie8 ,"Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de unidades vendidas) indicando o número total de distintos clientes que o compraram (X é um inteiro dado pelo utilizador)","",true,true,tempoComputacao8);
            break;
        }
        case 9:
        {
            limpaEcran();
            System.out.println("Indique o numero de clientes que deseja consultar:");
            int numero= Input.lerInt();
            Crono.start();
            ArrayList <String> resultadoQuerie9 = compras.querie9(numero);
            Crono.stop();
            String tempoComputacao9 = new String();
            tempoComputacao9=Crono.print();
            paginador(resultadoQuerie9,"Determinar os X clientes que compraram um maior número de diferentes produtos, indicando quantos","",true,true,tempoComputacao9);
            break;
        }
        case 10:
        {
            limpaEcran();
            System.out.println("Indique um codigo de produto válido (Ex: AA0000):");
            String codigoProd= Input.lerString();
            limpaEcran();
            System.out.println("Indique o numero de clientes que deseja consultar:");
            int numero= Input.lerInt();
            String descricao = new String();
            Crono.start();
            ArrayList <String> resultadoQuerie10 = contabilidade.querie10(codigoProd,numero);
            Crono.stop();
            String tempoComputacao10 = new String();
            tempoComputacao10=Crono.print();
            paginador(resultadoQuerie10,"Dado o código de um produto, determinar o conjunto dos X clientes que mais o compraram e qual o valor gasto","",true,true,tempoComputacao10);
            break;
        }
      }
    }
  }

  private static void carregarGuardarPrograma(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarGuardar.executa();
      switch(opcao = menuCarregarGuardar.getOpcao()){
        case 3 :
          mainMenu();
          break;
      }
    }
  }

  private static void handlerCarregarProdutos(String pathFicheiroProdutos){
    limpaEstruturaProdutos();        
    try{ 
      catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
      System.out.println("Ficheiro de Produtos carregado com sucesso\n");
      System.out.println(catalogoProdutos.toString());
      flagEstruturaProdutosCarregada = true;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Produtos\n");            
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private static void handlerCarregarClientes( String pathFicheiroClientes ){
    limpaEstruturaClientes();
    try{ 
      catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
      System.out.println("Ficheiro de Clientes carregado com sucesso\n");
      System.out.println(catalogoClientes.toString());
      flagEstruturaClientesCarregada = true;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Clientes\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private static void handlerCarregarCompras( String pathFicheiroCompras ){
    limpaEstruturaCompras();
    try{ 
      parserCompras = new ParserCompras ( pathFicheiroCompras , catalogoProdutos, catalogoClientes , invalidas, compras , contabilidade );
      parserCompras.lerFicheiroCompras();
      System.out.println("Ficheiro de Compras carregado com sucesso\n");
      System.out.println(compras.toString());
      System.out.println(contabilidade.toString());
      System.out.println(invalidas.toString());
      flagEstruturaComprasCarregada = true;
      necessitaLimparEstruturaCompras = false;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Compras\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private static void paginador(ArrayList <String> linhas, String titulo , String cabecalho , boolean mostraNumeroLinha , boolean contadorElementos, String tempoComputacao ){
    int posActual, limiteSuperiorActual, limiteInferiorActual, tamanhoLido; 
    boolean flagEXIT=false;
    boolean paginaUnica = false;
    String opcaoInterna = new String();
    limiteInferiorActual = 0;
    tamanhoLido = linhas.size();
    while ( flagEXIT == false ){
      limpaEcran();
      posActual = limiteInferiorActual;
      limiteSuperiorActual = limiteInferiorActual + linhasHorizontais;
      if(limiteSuperiorActual > tamanhoLido ){ 
        limiteSuperiorActual = tamanhoLido; 
        paginaUnica = true;
      }
      StringBuilder pagina = new StringBuilder ();
      pagina.append("/**********************************************************************\n");
      pagina.append("/*\t").append(titulo).append("\n");
      if ( contadorElementos ){
        pagina.append("/*\tTotal de elementos lidos: ").append(tamanhoLido).append("\n");
        if ( !paginaUnica ){
          pagina.append("/*\tMostrando elementos ").append(limiteInferiorActual).append(" a ").append(limiteSuperiorActual).append("\n");
        }
      }
      pagina.append("/**********************************************************************\n");
      pagina.append(cabecalho).append("\n");
      while ( posActual <  limiteSuperiorActual ){
        if ( mostraNumeroLinha ){
          pagina.append(posActual+1).append("|\t");
        }
        pagina.append(linhas.get(posActual)).append("\n");
        posActual++;
      }
      pagina.append("/**********************************************************************\n");
      pagina.append("Tempo de Computação: "+tempoComputacao+" segundos\n");
      pagina.append("/**********************************************************************\n");
      pagina.append("/*\tPara terminar prima 'q'\n" );
      if ( !paginaUnica ){
        pagina.append("/*\tPara avançar 20 elemento prima 'd'\n" );
        pagina.append("/*\tPara avançar 100 elementos prima 'f'\n" );
        pagina.append("/*\tPara recuar 20 elemento prima 's'\n" );
        pagina.append("/*\tPara recuar 100 elementos prima 'a'\n" );
      }
      pagina.append("/**********************************************************************\n");
      pagina.append("opção:\n");
      System.out.println(pagina.toString());
      opcaoInterna = Input.lerString();
      if ( !paginaUnica ){
        if (opcaoInterna.equals("s")){ limiteInferiorActual-=20; if( limiteInferiorActual < 1 ){ limiteInferiorActual = 0; } }
        if (opcaoInterna.equals("d")){ limiteInferiorActual+=20; if( limiteInferiorActual + linhasHorizontais >= tamanhoLido ){ limiteSuperiorActual = tamanhoLido; limiteInferiorActual = limiteSuperiorActual - linhasHorizontais; } }
        if (opcaoInterna.equals("f")){ limiteInferiorActual+=100; if( ( limiteInferiorActual + linhasHorizontais ) >= tamanhoLido ){ limiteSuperiorActual = tamanhoLido; limiteInferiorActual = limiteSuperiorActual - linhasHorizontais;  } }
        if (opcaoInterna.equals("a")){ limiteInferiorActual-=100; if( limiteInferiorActual < 1 ){ limiteInferiorActual = 0; } }
      }
      if (opcaoInterna.equals("q")){ flagEXIT = true; }
    }
  }
  
  /**
   * Querie estatisticas 1.1
   */
  private static String querie11(){
      String listaQuerie11 = new String();
      StringBuilder querie11Info = new StringBuilder();
      querie11Info.append("\nNome do Ficheiro Compras Lido: "+parserCompras.estatisticas_1_1_P0());
      querie11Info.append("\nNome do Ficheiro Produtos Lido: "+catalogoProdutos.estatisticas_1_1_P0());
      querie11Info.append("\nNome do Ficheiro Clientes Lido: "+catalogoClientes.estatisticas_1_1_P0());
      querie11Info.append("\nProdutos");
      querie11Info.append("\nNumero Total de Produtos: "+catalogoProdutos.estatisticas_1_1_P1());
      querie11Info.append("\nNumero Total de diferentes Produtos comprados: "+contabilidade.estatisticas_1_1_P2());
      querie11Info.append("\nNumero Total de Produtos não comprados: "+(catalogoProdutos.estatisticas_1_1_P1()-contabilidade.estatisticas_1_1_P2()));
      querie11Info.append("\nClientes");
      querie11Info.append("\nNumero Total de Clientes: "+catalogoClientes.estatisticas_1_1_P4());
      querie11Info.append("\nNumero Total de Clientes que realizaram Compras: "+compras.estatisticas_1_1_P5());
      querie11Info.append("\nNumero Total de Clientes que nada compraram: "+(catalogoClientes.estatisticas_1_1_P4()-compras.estatisticas_1_1_P5()));
      querie11Info.append("\nClientes");
      querie11Info.append("\nTotal de Compras de valor total igual a 0: "+contabilidade.estatisticas_1_1_P7());
      querie11Info.append("\nFacturação Total: "+contabilidade.estatisticas_1_1_P8());
      listaQuerie11=querie11Info.toString();
      return listaQuerie11;
  }

  public static void main (){
      mainMenu();
  }
}
