/**
 * Classe agregadora de todo o projeto
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Hipermercado implements Serializable{

  private static Menu menuPrincipal, menuCarregarFicheiros, menuQueriesInterativas, menuCarregarGuardar 
  , menuCarregarProdutos , menuCarregarClientes , menuCarregarCompras ;
  
  private static StringsMenu stringMenu = new StringsMenu();
  private static CatalogoClientes catalogoClientes = new CatalogoClientes();
  private static CatalogoProdutos catalogoProdutos = new CatalogoProdutos();
  private static Contabilidade contabilidade = new Contabilidade();
  private static Compras compras = new Compras ();
  private static ComprasInvalidas invalidas = new ComprasInvalidas();
  private static Scanner scannerMain = new Scanner(System.in);
  
  
  private static String standardPathFicheiroProdutos = "files/FichProdutos.txt";
  private static String standardPathFicheiroClientes = "files/FichClientes.txt";
  private static String pathFicheiroComprasStandard = "files/Compras.txt";
  private static String pathFicheiroCompras1 = "files/Compras1.txt";
  private static String pathFicheiroCompras3 = "files/Compras3.txt";

  private static void carregaMenus(){
    menuPrincipal = new Menu(stringMenu.getOpcoesMenuPrincipal());
    menuCarregarFicheiros = new Menu(stringMenu.getOpcoesMenuCarregarFicheiros());
        menuCarregarProdutos = new Menu(stringMenu.getOpcoesMenuCarregarProdutos());
            menuCarregarClientes = new Menu(stringMenu.getOpcoesMenuCarregarClientes());
                menuCarregarCompras = new Menu(stringMenu.getOpcoesMenuCarregarCompras());
    menuQueriesInterativas = new Menu(stringMenu.getOpcoesMenuQueriesInterativas());
    menuCarregarGuardar = new Menu(stringMenu.getOpcoesMenuCarregarGuardar());

  }

  private static void limpaEcran(){
    System.out.print('\u000C');
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

  private static void queriesInterativas(){
    int opcao = -1;
    while (opcao != 0 ){
      limpaEcran();
      menuQueriesInterativas.executa();
      switch(opcao = menuQueriesInterativas.getOpcao()){
        case 11 :
          mainMenu();
          break;
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
    catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
    System.out.println("Ficheiro de Produtos carregado com sucesso\n");
    System.out.println(catalogoProdutos.toString());
    Menu.esperaReturn();
  }

  private static void handlerCarregarClientes( String pathFicheiroClientes ){
    catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
    System.out.println("Ficheiro de Clientes carregado com sucesso\n");
    System.out.println(catalogoClientes.toString());
    Menu.esperaReturn();
  }

  private static void handlerCarregarCompras( String pathFicheiroCompras ){
      ParserCompras parserCompras = new ParserCompras ( pathFicheiroCompras , catalogoProdutos, catalogoClientes , invalidas, compras , contabilidade );
      parserCompras.lerFicheiroCompras();
      System.out.println("Ficheiro de Compras carregado com sucesso\n");
      System.out.println(compras.toString());
            System.out.println(contabilidade.toString());
            System.out.println(invalidas.toString());
                Menu.esperaReturn();
  }


  public static void main (){
    mainMenu();
  }
}
