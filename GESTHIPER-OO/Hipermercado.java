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
import java.text.DecimalFormat;

public class Hipermercado implements Serializable{

  private transient Menu menuPrincipal, menuCarregarFicheiros, menuEstatisticas, menuEstatisticas12, menuQueriesInterativas, menuCarregarGuardar , menuCarregarProdutos , menuCarregarClientes , menuCarregarCompras ;
  private transient StringsMenu stringMenu;
  private CatalogoClientes catalogoClientes ;
  private boolean flagEstruturaClientesCarregada;
  private  CatalogoProdutos catalogoProdutos;
  private  boolean flagEstruturaProdutosCarregada;
  private  Contabilidade contabilidade;
  private  Compras compras;
  private  boolean flagEstruturaComprasCarregada;
  private  boolean necessitaLimparEstruturaCompras;
  private  ComprasInvalidas invalidas;
  private  ParserCompras parserCompras;

  private  transient String standardPathFicheiroProdutos;
  private  transient String standardPathFicheiroClientes;
  private  transient String pathFicheiroComprasStandard;
  private transient  String pathFicheiroCompras1;
  private transient  String pathFicheiroCompras3; 
  private transient String pathFicheiroObjecto;
  private transient int linhasHorizontais;

  public Hipermercado (){

    stringMenu = new StringsMenu();
    catalogoClientes = new CatalogoClientes();
    flagEstruturaClientesCarregada = false;
    catalogoProdutos = new CatalogoProdutos();
    flagEstruturaProdutosCarregada = false;
    contabilidade = new Contabilidade();
    compras = new Compras ();
    flagEstruturaComprasCarregada = false;
    necessitaLimparEstruturaCompras = false;
    invalidas = new ComprasInvalidas();
    parserCompras = null;

    /**
     * paths predefinidos dos ficheiros
     */
    standardPathFicheiroProdutos = "files/FichProdutos.txt";
    standardPathFicheiroClientes = "files/FichClientes.txt";
    pathFicheiroComprasStandard = "files/Compras.txt";
    pathFicheiroCompras1 = "files/Compras1.txt";
    pathFicheiroCompras3 = "files/Compras3.txt";
    pathFicheiroObjecto = "files/hipermercado.obj";

    /**
     * numero de linhas a serem mostradas pelo paginador
     */
    linhasHorizontais = 20;

  }

  private  void carregaMenus(){
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

  private  void limpaEcran(){
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

  public  void limpaEstruturaProdutos(){
    if ( flagEstruturaProdutosCarregada ){
      catalogoProdutos = new CatalogoProdutos();
      flagEstruturaProdutosCarregada = false;
      if ( flagEstruturaComprasCarregada ){
        necessitaLimparEstruturaCompras = true;
      }
    }
  }

  public  void limpaEstruturaClientes(){
    if ( flagEstruturaClientesCarregada ){
      catalogoClientes = new CatalogoClientes();
      flagEstruturaClientesCarregada = false;
      if ( flagEstruturaComprasCarregada ){
        necessitaLimparEstruturaCompras = true;
      }
    }
  }

  public  void limpaEstruturaCompras(){
    if ( flagEstruturaComprasCarregada ){
      contabilidade = new Contabilidade();
      compras = new Compras ();
      invalidas = new ComprasInvalidas();
      flagEstruturaComprasCarregada = false;
      necessitaLimparEstruturaCompras = false;
    }
  }

  private  void mainMenu(){
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

  private  void carregarFicheiros(){
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

  private  void carregarProdutos(){
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

  private  void carregarClientes(){
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

  private  void carregarCompras(){
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

  private  void menuEstatisticas(){
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

  private  void menuEstatisticas12(){
    int opcao = -1;
    while (opcao != 0 ){
      limpaEcran();
      menuEstatisticas12.executa();
      switch(opcao = menuEstatisticas12.getOpcao()){
        case 1:
          {
            Crono.start();
            ArrayList <String> paginas = compras.estatisticas_1_2_P1 ();
            Crono.stop();
            String tempoComputacao1 = new String();
            tempoComputacao1 = Crono.print();
            paginador(paginas, "Número total de compras por mês (não é a facturação)", "",false,false , tempoComputacao1 );
            break;
          }
        case 2: 
          {
            Crono.start();
            ArrayList <String> paginas = contabilidade.estatisticas_1_2_P2 ();
            Crono.stop();
            String tempoComputacao1 = new String();
            tempoComputacao1 = Crono.print();
            paginador(paginas, "Facturação total por mês (valor total das compras/vendas) e total global", "",false,false , tempoComputacao1);
            break;
          }
        case 3:
          {
            Crono.start();
            ArrayList <String> paginas = compras.estatisticas_1_2_P3 ();
            Crono.stop();
            String tempoComputacao1 = new String();
            tempoComputacao1 = Crono.print();
            paginador(paginas, "Número de distintos clientes que compraram em cada mês", "",false,false , tempoComputacao1 );
            break;
          }
        case 4:
          {
            Crono.start();
            ArrayList <String> paginas = invalidas.estatisticas_1_2_P4 ();
            Crono.stop();
            String tempoComputacao1 = new String();
            tempoComputacao1 = Crono.print();
            paginador(paginas, "Total de registos de compras inválidos", "Tipo Invalidez\t\t\t#Registos\n-------------------------------------",false,false , tempoComputacao1);
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

  private  void queriesInterativas(){
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

  private  void carregarGuardarPrograma(){
    int opcao = -1;
    while (opcao != 0){
      limpaEcran();
      menuCarregarGuardar.executa();
      switch(opcao = menuCarregarGuardar.getOpcao()){
        case 1:
          {
            limpaEcran();
            carregarObjecto( pathFicheiroObjecto );
            break;
          }
        case 2:
          { 
            limpaEcran();
            System.out.println("Insira o nome do ficheiro a ser carregado:");
            String nomeFicheiro = new String();
            nomeFicheiro = Input.lerString();
            carregarObjecto( nomeFicheiro );
            break;
          }
        case 3:
          {
            limpaEcran();
            gravarObjecto( pathFicheiroObjecto );
            break;
          }
        case 4 :
          {
            limpaEcran();
            System.out.println("Insira o nome do ficheiro a ser gravado:");
            String nomeFicheiro = new String();
            nomeFicheiro = Input.lerString();
            gravarObjecto( nomeFicheiro );
            break;
          }
        case 5 :
          mainMenu();
          break;
      }
    }
  }

  private  void handlerCarregarProdutos(String pathFicheiroProdutos){
    limpaEstruturaProdutos();        
    try{ 
      System.out.println("Carregando ficheiro de produtos de: " + pathFicheiroProdutos + "!\n");
      Crono.start();
      catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
      Crono.stop();
      String tempoComputacao = new String();
      tempoComputacao = Crono.print();
      System.out.println("Ficheiro de Produtos carregado com sucesso\n");
      System.out.println(catalogoProdutos.toString());
      System.out.println("Tempo de computação : "+ tempoComputacao +" segundos\n");
      flagEstruturaProdutosCarregada = true;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Produtos\n");            
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private  void handlerCarregarClientes( String pathFicheiroClientes ){
    limpaEstruturaClientes();
    try{ 
      System.out.println("Carregando ficheiro de clientes de: " + pathFicheiroClientes + "!\n");
      Crono.start();
      catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
      Crono.stop();
      String tempoComputacao = new String();
      tempoComputacao = Crono.print();
      System.out.println("Ficheiro de Clientes carregado com sucesso\n");
      System.out.println(catalogoClientes.toString());
      System.out.println("Tempo de computação : "+ tempoComputacao +" segundos\n");
      flagEstruturaClientesCarregada = true;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Clientes\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private  void handlerCarregarCompras( String pathFicheiroCompras ){
    limpaEstruturaCompras();
    try{ 
      System.out.println("Carregando ficheiro de compras de: " + pathFicheiroCompras + "!\n");
      Crono.start();
      parserCompras = new ParserCompras ( pathFicheiroCompras , catalogoProdutos, catalogoClientes , invalidas, compras , contabilidade );
      parserCompras.lerFicheiroComprasScanner();
      Crono.stop();
      String tempoComputacao = new String();
      tempoComputacao = Crono.print();
      System.out.println("Ficheiro de Compras carregado com sucesso\n");
      System.out.println(compras.toString());
      System.out.println(contabilidade.toString());
      System.out.println(invalidas.toString());
      System.out.println("Tempo de computação : "+ tempoComputacao +" segundos\n");
      flagEstruturaComprasCarregada = true;
      necessitaLimparEstruturaCompras = false;
    }
    catch (IOException e ){ 
      System.out.println("Erro a carregar Ficheiro Compras\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  private  void paginador(ArrayList <String> linhas, String titulo , String cabecalho , boolean mostraNumeroLinha , boolean contadorElementos, String tempoComputacao ){
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
  private  String querie11(){
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
    querie11Info.append("\nCompras");
    querie11Info.append("\nTotal de Compras de valor total igual a 0: "+contabilidade.estatisticas_1_1_P7());
    float faturacaoTotal =   contabilidade.estatisticas_1_1_P8();
    String faturacao2Casas = String.format("%.2f", faturacaoTotal) ;
    querie11Info.append("\nFacturação Total: " + faturacao2Casas );
    listaQuerie11=querie11Info.toString();
    return listaQuerie11;
  }

  public  void gravarObjecto(String ficheiro) {
    try{ 
      System.out.println("Iniciada a gravação por objecto em: " + ficheiro + "!\n");
      Crono.start();
      ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
      objStreamOut.writeObject(catalogoClientes);
      objStreamOut.writeObject(flagEstruturaClientesCarregada);
      objStreamOut.writeObject(catalogoProdutos);
      objStreamOut.writeObject(flagEstruturaProdutosCarregada);
      objStreamOut.writeObject(contabilidade);
      objStreamOut.writeObject(compras);
      objStreamOut.writeObject(flagEstruturaComprasCarregada);
      objStreamOut.writeObject(necessitaLimparEstruturaCompras);
      objStreamOut.writeObject(invalidas);
      objStreamOut.writeObject(parserCompras);
      objStreamOut.flush();
      objStreamOut.close();
      Crono.stop();
      String tempoComputacao = new String();
      tempoComputacao = Crono.print();
      System.out.println("Objecto gravado com sucesso em: " + ficheiro + "!\n");
      System.out.println("Tempo de computação : "+ tempoComputacao +" segundos\n");
    }
    catch (IOException e ){ 
      System.out.println("Erro ao gravar a estrutura em: " + ficheiro + "!\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  public  void carregarObjecto(String ficheiro) {
    try{ 
      System.out.println("Carregando objecto de: " + ficheiro + "!\n");
      Crono.start();
      FileInputStream fis = new FileInputStream(ficheiro);
      ObjectInputStream objStreamIn = new ObjectInputStream(fis);
      catalogoClientes = (CatalogoClientes) objStreamIn.readObject() ;
      flagEstruturaClientesCarregada = (boolean) objStreamIn.readObject() ;
      catalogoProdutos = (CatalogoProdutos) objStreamIn.readObject();
      flagEstruturaProdutosCarregada = (boolean) objStreamIn.readObject();
      contabilidade = (Contabilidade) objStreamIn.readObject();
      compras = (Compras) objStreamIn.readObject();
      flagEstruturaComprasCarregada = (boolean) objStreamIn.readObject();
      necessitaLimparEstruturaCompras = (boolean) objStreamIn.readObject();
      invalidas = (ComprasInvalidas) objStreamIn.readObject();
      parserCompras = (ParserCompras) objStreamIn.readObject();
      objStreamIn.close();
      Crono.stop();
      String tempoComputacao = new String();
      tempoComputacao = Crono.print();
      System.out.println("Objecto carregado com sucesso de: " + ficheiro + "!\n");
      System.out.println("Tempo de computação : "+ tempoComputacao +" segundos\n");

    }
    catch (Exception e ){ 
      System.out.println("Erro ao carregar a estrutura de: " + ficheiro + "!\n");
      System.out.println(e.getMessage());
    }
    Menu.esperaReturn();
  }

  public static void main (){
    Hipermercado hyper = new Hipermercado(  );
    hyper.mainMenu();
  }
}
