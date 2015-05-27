
/**
 * Write a description of class Hipermercado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hipermercado{
    public static void main(){
        Parser par = new Parser();
        String clientes = "files/FichClientes.txt";
        par.lerFichClientes(clientes);
        String produtos = "files/FichProdutos.txt";
        par.lerFichProdutos(produtos);
        String compras = "files/Compras.txt";
        par.lerFichCompras(compras);
    }
    
}
