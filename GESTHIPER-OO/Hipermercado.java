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
    
    /*public final static void clearConsole(){
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
    }*/
    
    public static void carregaMenus(){
       menuPrincipal = new Menu(stringMenu.getOpcoesMenuPrincipal());
       menuCarregarFicheiros = new Menu(stringMenu.getOpcoesMenuCarregarFicheiros());
       menuQueriesInterativas = new Menu(stringMenu.getOpcoesMenuQueriesInterativas());
       menuCarregarGuardar = new Menu(stringMenu.getOpcoesMenuCarregarGuardar());
    }
    
    public static void carregarFicheiros(){
        menuCarregarFicheiros.executa();
        switch(menuCarregarFicheiros.getOpcao()){
            case 1 :
                System.out.print('\u000C');
                handlerCarregarProdutos();
                break;
            case 2 :
                System.out.print('\u000C');
                handlerCarregarClientes();
                break;
            case 3 :
                System.out.print('\u000C');
                handlerCarregarComprasStandard();
                break;
            case 4 :
                System.out.print('\u000C');
                handlerCarregarCompras1();
                break;
            case 5 :
                System.out.print('\u000C');
                handlerCarregarCompras3();
                break;
            case 6 :
                main();
        }
    }
    
    public static void queriesInterativas(){
        menuQueriesInterativas.executa();
    }
    
    public static void carregarGuardarPrograma(){
        menuCarregarGuardar.executa();
    }
    
    public static void handlerCarregarProdutos(){
        String pathFicheiroProdutos = "files/FichProdutos.txt";
        catalogoProdutos.lerFicheiroProdutos( pathFicheiroProdutos);
        System.out.println("Ficheiro de Produtos carregado com sucesso\n");
        System.out.println(catalogoProdutos.toString());
        System.out.println("\n0-Voltar atrás");
    }
    
    public static void handlerCarregarClientes(){
        String pathFicheiroClientes = "files/FichClientes.txt";
        catalogoClientes.lerFicheiroClientes( pathFicheiroClientes );
        System.out.println("Ficheiro de Clientes carregado com sucesso\n");
        System.out.println(catalogoClientes.toString());
    }
    
    public static void handlerCarregarComprasStandard(){
        String pathFicheiroComprasStandard = "files/Compras.txt";
    }
    
    public static void handlerCarregarCompras1(){
        String pathFicheiroCompras1 = "files/Compras1.txt";
    }
    
    public static void handlerCarregarCompras3(){
        String pathFicheiroCompras3 = "files/Compras3.txt";
    }
    
    public static void main(){
        System.out.print('\u000C');
        carregaMenus();
        //do{
            menuPrincipal.executa();
            switch(menuPrincipal.getOpcao()){
                case 1 :
                    System.out.print('\u000C');
                    carregarFicheiros();
                    break;
                case 2 :
                    System.out.print('\u000C');
                    break;
                case 3 :
                    System.out.print('\u000C');
                    queriesInterativas();
                    break;
                case 4 :
                    System.out.print('\u000C');
                    carregarGuardarPrograma();
            }
        //}while(menuPrincipal.getOpcao()!=0);
    }
}
