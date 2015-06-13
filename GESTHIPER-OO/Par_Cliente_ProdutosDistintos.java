
/**
 * Classe que relacciona clientes e o numero de produtos distintos comprados
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

public class Par_Cliente_ProdutosDistintos
{
  private String codigoCliente;
  private int numeroProdutosDistintos;

  public Par_Cliente_ProdutosDistintos(){
    this.codigoCliente = new String ();
    this.numeroProdutosDistintos = 0;
  }

  public Par_Cliente_ProdutosDistintos( String codigoCliente , int numeroProdutos ){
    this.codigoCliente = new String (codigoCliente);
    this.numeroProdutosDistintos = numeroProdutos;
  }

  public Par_Cliente_ProdutosDistintos( Par_Cliente_ProdutosDistintos copia ){
    this.codigoCliente = copia.getCodigoCliente();
    this.numeroProdutosDistintos = copia.getNumeroProdutosDistintos();
  }

  public String getCodigoCliente(){
    return this.codigoCliente;
  }

  public int getNumeroProdutosDistintos(){
    return this.numeroProdutosDistintos;
  }

  public void setNumeroProdutosDistintos( int numeroProdutos ){
    this.numeroProdutosDistintos = numeroProdutos ;
  }

  /** toString */
  @Override
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append(codigoCliente).append("\t");
      s.append(numeroProdutosDistintos).append(" prod.");
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
        Par_Cliente_ProdutosDistintos that = (Par_Cliente_ProdutosDistintos) o;
        if(this.codigoCliente.equals(that.getCodigoCliente())) {
          resultado = true;
        }
      }
      return resultado;
    }

  /**
   * Método clone
   */
  public Par_Cliente_ProdutosDistintos clone() {
    return new Par_Cliente_ProdutosDistintos(this);
  }
}

