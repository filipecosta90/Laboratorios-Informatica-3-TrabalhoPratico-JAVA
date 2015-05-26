
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
        par.getCatCli().toString();
        String produtos = "files/FichProdutos.txt";
        par.lerFichProdutos(produtos);
        par.getCatProd().toString();
        //String compras = "files/Compras.txt";
        //lerFichCompras(compras);
    }
    
}
