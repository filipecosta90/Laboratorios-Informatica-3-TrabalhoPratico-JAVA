/**
 * Write a description of class Compras here.
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779)  
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


public class Compra implements Serializable {
  private String codigoProduto;
  private float preco;
  private int quantidade;
  private String tipoCompra;
  private String codigoCliente;
  private int mes;

  /** Construtores */
  //Vazio
  public Compra(){
    this.codigoProduto= new String();
    this.preco=0.0f;
    this.quantidade=0;
    this.tipoCompra= new String();
    this.codigoCliente = new String();
    this.mes=0;
  }

  //Parametrizado
  public Compra(String codProd, float preco, int quant, String tipo, String codCli, int mes){
    this.codigoProduto=codProd;
    this.preco=preco;
    this.quantidade=quant;
    this.tipoCompra=tipo;
    this.codigoCliente=codCli;
    this.mes=mes;
  }

  //Cópia
  public Compra(Compra c){
    this.codigoProduto=c.getCodigoProduto();
    this.preco=c.getPreco();
    this.quantidade=c.getQuantidade();
    this.tipoCompra=c.getTipoCompra();
    this.codigoCliente=c.getCodigoCliente();
    this.mes=c.getMes();
  }




  /**
   * Getters && Setters
   */
  public String getCodigoProduto(){
    String codigoRetornar = new String (this.codigoProduto);
    return codigoRetornar;
  }

  public float getPreco(){
    return this.preco;
  }

  public int getQuantidade(){
    return this.quantidade;
  }

  public String getTipoCompra(){
    String novoTipo = new String(this.tipoCompra);
    return novoTipo;
  }

  public String getCodigoCliente(){
    String codigoRetornar = new String ( this.codigoCliente );
    return codigoRetornar;
  }

  public int getMes(){
    return this.mes;
  }

  public void setCodigoProduto(String codProd){
    this.codigoProduto=codProd;
  }

  public void setPreco(float preco){
    this.preco=preco;
  }

  public void setQuantidade(int quant){
    this.quantidade=quant;
  }

  public void setTipoCompra(String tipo){
    this.tipoCompra=tipo;
  }

  public void setCodigoCliente(String codCli){
    this.codigoCliente=codCli;
  }

  public void setMes(int mes){
    this.mes=mes;
  }

  /** Método que calcula o total faturado nesta compra */
  public float getTotalFaturado(){
    float total = this.preco*this.quantidade;
    return total;
  }

  /** Método auxiliar que verifica o tipo da compra isto é se é N->normal ou P->promoção */
  public static boolean verificaTipoCompra(String tipo){
    boolean resultado = false;

    if(tipo.length()==1 && ( (tipo.equals("N") || tipo.equals("n") || tipo.equals("P") || tipo.equals("p")) )) {           
      resultado = true;
    }

    return resultado;
  }

  /** Método para gravar CatalogoClientes em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));

    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }

  /** Métodos complementares usuais **/
  //Equals
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
        Compra that = (Compra) o;
        if(this.codigoProduto.equals(that.getCodigoProduto()) && ( this.preco == that.getPreco() ) && ( this.quantidade == that.getQuantidade() )
            && ( this.tipoCompra.equals(that.getTipoCompra()) ) &&  ( this.codigoCliente.equals(that.getCodigoCliente()) ) && ( this.mes == that.getMes()) ) {
          resultado = true;
            }
      }
      return resultado;
    }

  //toString
  @Override
    public String toString() {
      StringBuilder s= new StringBuilder();

      s.append("*** \tInformação da Compra \t***");
      s.append("\n\tEntre Codigo Cliente: "+this.codigoCliente);
      s.append("\te Codigo Produto: "+this.codigoProduto);
      s.append("\n\tQuantidade: "+this.quantidade);
      s.append("\tPreco: "+this.preco);
      s.append("\n\tMes: "+this.mes);
      s.append("\tTipo Compra: "+this.tipoCompra);

      return s.toString();
    }

  //clone
  @Override
    public Compra clone() {
      return new Compra(this);
    }    
}
