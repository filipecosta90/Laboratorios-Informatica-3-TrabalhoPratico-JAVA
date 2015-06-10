/**
 * Classe agregadora de todo o projeto
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;

public class Hipermercado implements Serializable{
    
    /*public static void gravar (String fich) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(fich));
        try{
            oos.writeObject(this);
        }
        catch(IOException e){
            throw e;
            System.out.println(e.getMessage());
        }
        oos.flush();
        oos.close();
    } */
    
    public static void main(){
        
        CatalogoClientes catalogoClientes = new CatalogoClientes();
        CatalogoProdutos catalogoProdutos = new CatalogoProdutos();
        Contabilidade contabilidade = new Contabilidade();
        Compras compras = new Compras ();
        
        Scanner scannerMain = new Scanner(System.in);
        Parser par = new Parser();
        
        String pathFicheiroClientes = "files/FichClientes.txt";
        String pathFicheiroProdutos = "files/FichProdutos.txt";
        String pathFicheiroCompras = "files/Compras.txt";
        
       catalogoClientes = par.lerFichClientes( pathFicheiroClientes , catalogoClientes );
       catalogoProdutos = par.lerFichProdutos( pathFicheiroProdutos , catalogoProdutos);
       par.lerFichCompras( pathFicheiroCompras , catalogoClientes , catalogoProdutos, contabilidade , compras );

    }
    
}
