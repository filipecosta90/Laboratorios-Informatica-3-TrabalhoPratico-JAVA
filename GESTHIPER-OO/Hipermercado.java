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
        
        Scanner scannerMain = new Scanner(System.in);
        Parser par = new Parser();
        String clientes = "files/FichClientes.txt";
        par.lerFichClientes(clientes);
        String produtos = "files/FichProdutos.txt";
        par.lerFichProdutos(produtos);
        String compras = "files/Compras.txt";
        par.lerFichCompras(compras);
        String hiper;
        int x = Input.lerInt(scannerMain);
    }
    
}
