
/**
 * Triplo que relacciona Códigos de Clientes -> Unidades de compradas e o valor total gasto
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

public class Triplo_Cliente_Unidades_ValorGasto{

  private String codigoCliente;
  private int unidadesVendidas;
  private float valorGasto;

  public Triplo_Cliente_Unidades_ValorGasto(){
    this.codigoCliente = new String ();
    this.unidadesVendidas = 0;
    this.valorGasto = 0.0f;
  }

  public Triplo_Cliente_Unidades_ValorGasto( String codigoCliente , int unidadesVendidas,  float valorGasto ){
    this.codigoCliente = new String (codigoCliente);
    this.unidadesVendidas = unidadesVendidas;
    this.valorGasto = valorGasto;
  }

  public Triplo_Cliente_Unidades_ValorGasto( Triplo_Cliente_Unidades_ValorGasto copia ){
    this.codigoCliente = copia.getCodigoCliente();
    this.unidadesVendidas = copia.getUnidadesVendidas();
    this.valorGasto = copia.getValorGasto();
  }

  public String getCodigoCliente(){
    return this.codigoCliente;
  }

  public int getUnidadesVendidas(){
    return this.unidadesVendidas;
  }

  public float getValorGasto(){
    return this.valorGasto;
  }

  public void setValorGasto( float valorGasto ){
    this.valorGasto = valorGasto ;
  }

  public void incrementaUnidadesVendidas( int unidadesIncrementar ){
    this.unidadesVendidas += unidadesIncrementar;
  }

  public void incrementaValorGasto ( float valorIncrementar){
    this.valorGasto += valorIncrementar;
  }

  /** toString */
  @Override
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append(codigoCliente).append("\t");
      String faturacao2Casas = String.format("%.2f", valorGasto) ;
      s.append(faturacao2Casas);
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
        Triplo_Cliente_Unidades_ValorGasto that = (Triplo_Cliente_Unidades_ValorGasto) o;
        if(this.codigoCliente.equals(that.getCodigoCliente())) {
          resultado = true;
        }
      }
      return resultado;
    }

  /**
   * Método clone
   */
  public Triplo_Cliente_Unidades_ValorGasto clone() {
    return new Triplo_Cliente_Unidades_ValorGasto(this);
  }
}
