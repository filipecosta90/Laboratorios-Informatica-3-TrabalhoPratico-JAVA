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
    private static Parser par = new Parser();
    
    public final static void clearConsole(){
    try{
        final String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }
    catch (IOException e){
        System.out.println(e.getMessage());
    }
}
    
    public static void carregaMenus(){
       menuPrincipal = new Menu(stringMenu.getOpcoesMenuPrincipal());
       menuCarregarFicheiros = new Menu(stringMenu.getOpcoesMenuCarregarFicheiros());
       menuQueriesInterativas = new Menu(stringMenu.getOpcoesMenuQueriesInterativas());
       menuCarregarGuardar = new Menu(stringMenu.getOpcoesMenuCarregarGuardar());
    }
    
    public static void mainMenu(){
        carregaMenus();
        do{
            menuPrincipal.executa();
            switch(menuPrincipal.getOpcao()){
                case 1 :
                    carregarFicheiros();
                    break;
                case 2 :
                    clearConsole();
                    break;
                case 3 :
                    queriesInterativas();
                    break;
                case 4 :
                    carregarGuardarPrograma();
            }
        }while(menuPrincipal.getOpcao()!=0);
    }
    
    public static void carregarFicheiros(){
        clearConsole();
        menuCarregarFicheiros.executa();
        switch(menuCarregarFicheiros.getOpcao()){
            case 1 : 
                clearConsole();
                handlerCarregarProdutos();
                break;
            case 2 :
                clearConsole();
                handlerCarregarClientes();
                break;
            case 3 :
                handlerCarregarComprasStandard();
                break;
            case 4 :
                handlerCarregarCompras1();
                break;
            case 5 :
                handlerCarregarCompras3();
                break;
        }
    }
    
    public static void queriesInterativas(){
        clearConsole();
        menuQueriesInterativas.executa();
    }
    
    public static void carregarGuardarPrograma(){
        clearConsole();
        menuCarregarGuardar.executa();
    }
    
    public static void handlerCarregarProdutos(){
        String pathFicheiroProdutos = "files/FichProdutos.txt";
        catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
        System.out.println("Ficheiro de Produtos carregado com sucesso\n");
        catalogoProdutos.toString();
    }
    
    public static void handlerCarregarClientes(){
        String pathFicheiroClientes = "files/FichClientes.txt";
        catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
        System.out.println("Ficheiro de Clientes carregado com sucesso\n");
        catalogoClientes.toString();
    }
    
    public static void handlerCarregarComprasStandard(){
        
    }
    
    public static void handlerCarregarCompras1(){
        
    }
    
    public static void handlerCarregarCompras3(){
        
    }
    
    public static void main(){
       String pathFicheiroCompras = "files/Compras.txt";
       mainMenu();
    }
    
}
