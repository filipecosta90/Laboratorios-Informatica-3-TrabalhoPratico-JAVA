
/**
 * Write a description of class ParCodigoProdutoNumeroVendas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Triplo_Produto_Unidades_Vendas 
{
  private String codigoProduto;
  private int numeroUnidadesVendidas;
  private int numeroVendas;

  public Triplo_Produto_Unidades_Vendas(){
    codigoProduto = new String ();
    numeroUnidadesVendidas = 0;
    numeroVendas = 0;
  }

  public Triplo_Produto_Unidades_Vendas( String codigoProd , int numeroUnidades , int numeroV ){
    codigoProduto = new String (codigoProd);
    numeroUnidadesVendidas = numeroUnidades;
    numeroVendas = numeroV;
  }

  public Triplo_Produto_Unidades_Vendas( Triplo_Produto_Unidades_Vendas copia ){
    codigoProduto = copia.getCodigoProduto();
    numeroUnidadesVendidas = copia.getNumeroUnidadesVendidas();
    numeroVendas = copia.getNumeroVendas();
  }

  public String getCodigoProduto(){
    return this.codigoProduto;
  }

  public int getNumeroUnidadesVendidas(){
    return this.numeroUnidadesVendidas;
  }

  public int getNumeroVendas(){
    return this.numeroVendas;
  }

  public void adicionaUnidadesVendidas ( int adicionar ){
    this.numeroUnidadesVendidas += adicionar;
  } 

  public void incrementaNumeroVendas (){
    this.numeroVendas++;
  }

  /** toString */
  @Override
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append(codigoProduto).append("\t");
      s.append(numeroUnidadesVendidas).append(" unid.").append("\t");
      s.append(numeroVendas).append(" vendas").append("\n");
      return s.toString();
    }

  /**
   * equals
   */
  @Override    
    public boolean equals(Object o) {
      boolean resultado = false;
      //mesmo objecto
      if(this==o) {
        resultado = true;
      }
      // objecto nulo ou de classe diferente
      else if((o==null) || this.getClass()!=o.getClass()) {
        resultado = false; 
      }
      // objecto mesma classe
      else {
        Triplo_Produto_Unidades_Vendas that = (Triplo_Produto_Unidades_Vendas) o;
        if(this.codigoProduto.equals(that.getCodigoProduto())) {
          resultado = true;
        }
      }
      return resultado;
    }

  /**
   * Método clone
   */
  public Triplo_Produto_Unidades_Vendas clone() {
    return new Triplo_Produto_Unidades_Vendas(this);
  }

}
