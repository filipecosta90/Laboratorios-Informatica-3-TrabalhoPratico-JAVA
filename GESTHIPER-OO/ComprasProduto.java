
/**
 * Classe referente as compras de um cliente
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;

public class ComprasProduto implements Serializable{

  private TreeMap <Integer, HashSet<Compra>> listaComprasProduto; //Key = Mês

  public ComprasProduto(){
    this.listaComprasProduto = new TreeMap <Integer, HashSet<Compra>>();
  }

  public ComprasProduto(TreeMap <Integer, HashSet<Compra>> lista){
    HashSet <Compra> novaCompras = new HashSet <> ();
    TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <> ();
    for(HashSet <Compra> cmp : lista.values()){
      for(Compra c : cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
  }

  public ComprasProduto(ComprasProduto cc){
    TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <Integer, HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra> ();
    for(HashSet <Compra> cmp : cc.getListaComprasProduto().values()){
      for(Compra c : cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
  }

  /**
   * Getters and Setters
   */

  /**
   * Perguntar ao prof problema com o clone
   */
  public TreeMap <Integer,HashSet<Compra>> getListaComprasProduto(){
    TreeMap <Integer, HashSet<Compra> > novaLista = new TreeMap <Integer,HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra>();
    for(HashSet<Compra> cmp : this.listaComprasProduto.values()){
      for(Compra c: cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
    return novaLista;
  }

  /**
   * Método auxiliar que junta uma compra ao HashSet
   */
  private HashSet <Compra> addCompraToSet (Compra c){
    HashSet <Compra> compras = new HashSet<>();
    compras.add(c);
    return compras;
  }

  /**
   * Método que adiciona uma compra na estrutura
   */
  public void addCompraToLista(Compra c){
    int mes = c.getMes();
    this.listaComprasProduto.put(mes,addCompraToSet(c));
  }

  /** Método auxiliar Q5 para retornar a String com informação: |Mes|Compras|Produtos|Total Gasto|Total Acumulado */
  public ArrayList <String> getMapComprasMensal(){
    ArrayList<String> listaMesAMes = new ArrayList<>();
    StringBuilder mapaString = new StringBuilder();
    mapaString.append("Mês\t#Numero de vezes Comprado\t#Clientes Distintos\tTotal Facturado\n");
    listaMesAMes.add(mapaString.toString());
    for(Integer mes : this.listaComprasProduto.keySet()){
      mapaString = new StringBuilder();
      HashSet <Compra> comprasMensais = this.listaComprasProduto.get(mes);
      mapaString.append("\n").append(mes);
      mapaString.append("\t").append(numeroVezesComprado(comprasMensais));
      mapaString.append("\t").append(numeroClientesDistintosQueComprouProduto(comprasMensais));
      float totalMensal = getTotalFacturadoMes(comprasMensais);
      mapaString.append("\t").append(totalMensal);
      mapaString.append("\n");
      listaMesAMes.add(mapaString.toString());
    }
    return listaMesAMes;
  }
  
  /**
   * Método auxiliar Q5 que calcula o numero de vezes que um produto foi comprado
   */
  public int numeroVezesComprado (HashSet <Compra> comprasMensais){
    int contaVezes = comprasMensais.size();
    return contaVezes;
  }
  
  /**
   * Método auxiliar Q5 que calcula por quantos clientes distintos um produto foi comprado
   */
  public int numeroClientesDistintosQueComprouProduto(HashSet <Compra> comprasMensais){
      int numeroClientesDistintos;
      TreeSet <String> clientesDistintos = new TreeSet<>();
      for(Compra compraActual : comprasMensais){
          clientesDistintos.add(compraActual.getCodigoCliente());
      }
      numeroClientesDistintos = clientesDistintos.size();
      return numeroClientesDistintos;
  }
  
  /**
   * Método auxiliar Q5 que calcula o total faturado
   */
  public float getTotalFacturadoMes(HashSet <Compra> comprasMensais){
      float totalFaturado = 0;
      for(Compra compraActual : comprasMensais){    
          totalFaturado+=compraActual.getTotalFaturado();
      }
      return totalFaturado;
  }
  
  /**
   * toString
   */
  @Override
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append("\nInformação de Compras");
      s.append("\nCompras do Produto:");
      for(HashSet <Compra> cmp : this.listaComprasProduto.values()){
        for(Compra c : cmp){
          s.append("\n"+cmp.toString());
        }
      }
      return s.toString();
    }

  /**
   * Equals
   */
  @Override
    public boolean equals(Object o) {
      if(this==o) return true;
      if((o==null) || this.getClass()!=o.getClass()) return false;
      else{
        ComprasProduto cp = (ComprasProduto) o;
        if(this.listaComprasProduto.equals(cp.getListaComprasProduto())) return true;
        else return false;
      }
    }

  /**
   * Método clone
   */
  @Override
    public ComprasProduto clone() {
      return new ComprasProduto(this);
    } 
}

