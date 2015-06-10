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
    
    private static Menu menuPrincipal, menuCarregarFicheiros, menuQueriesInterativas, menuCarregarGuardar;
    private static StringsMenu stringMenu = new StringsMenu();
    private static CatalogoClientes catalogoClientes = new CatalogoClientes();
    private static CatalogoProdutos catalogoProdutos = new CatalogoProdutos();
    private static Contabilidade contabilidade = new Contabilidade();
    private static Compras compras = new Compras ();
    private static Scanner scannerMain = new Scanner(System.in);
        
    private static void carregaMenus(){
       menuPrincipal = new Menu(stringMenu.getOpcoesMenuPrincipal());
       menuCarregarFicheiros = new Menu(stringMenu.getOpcoesMenuCarregarFicheiros());
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
                    handlerCarregarProdutos();
                    break;
                case 2 :
                    limpaEcran();
                    handlerCarregarClientes();
                    break;
                case 3 :
                    limpaEcran();
                    handlerCarregarComprasStandard();
                    break;
                case 4 :
                    limpaEcran();
                    handlerCarregarCompras1();
                    break;
                case 5 :
                    limpaEcran();
                    handlerCarregarCompras3();
                    break;
                case 6 :
                    mainMenu();
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
    
    private static void handlerCarregarProdutos(){
        String pathFicheiroProdutos = "files/FichProdutos.txt";
        catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
        System.out.println("Ficheiro de Produtos carregado com sucesso\n");
        System.out.println(catalogoProdutos.toString());
        Menu.esperaReturn();
    }
    
    private static void handlerCarregarClientes(){
        String pathFicheiroClientes = "files/FichClientes.txt";
        catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
        System.out.println("Ficheiro de Clientes carregado com sucesso\n");
        System.out.println(catalogoClientes.toString());
        Menu.esperaReturn();
    }
    
    private static void handlerCarregarComprasStandard(){
        String pathFicheiroComprasStandard = "files/Compras.txt";
    }
    
    private static void handlerCarregarCompras1(){
        String pathFicheiroCompras1 = "files/Compras1.txt";
    }
    
    private static void handlerCarregarCompras3(){
        String pathFicheiroCompras3 = "files/Compras3.txt";
    }
    
    public static void main (){
        mainMenu();
    }
}
