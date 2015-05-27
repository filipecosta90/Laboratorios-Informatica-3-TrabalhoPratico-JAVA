/**
 * Write a description of class Compras here.
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779)  
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Compra implements Serializable{
    private String codProduto;
    private float preco;
    private int quantidade;
    private String tipo;
    private String codCliente;
    private int mes;
    
    /**
     * Construtores
     */
    public Compra(){
        this.codProduto="";
        this.preco=0;
        this.quantidade=0;
        this.tipo=null;
        this.codCliente=null;
        this.mes=0;
    }
    
    public Compra(String codProd, float preco, int quant, String tipo, String codCli, int mes){
        this.codProduto=codProd;
        this.preco=preco;
        this.quantidade=quant;
        this.tipo=tipo;
        this.codCliente=codCli;
        this.mes=mes;
    }
    
    public Compra(Compra c){
        this.codProduto=c.getCodProd();
        this.preco=c.getPreco();
        this.quantidade=c.getQuantidade();
        this.tipo=c.getTipo();
        this.codCliente=c.getCodCli();
        this.mes=c.getMes();
    }
    
    /**
     * Gets && Sets
     */
    public String getCodProd(){return this.codProduto;}
    public float getPreco(){return this.preco;}
    public int getQuantidade(){return this.quantidade;}
    public String getTipo(){return this.tipo;}
    public String getCodCli(){return this.codCliente;}
    public int getMes(){return this.mes;}
    
    public void setCodProd(String codProd){this.codProduto=codProd;}
    public void setPreco(float preco){this.preco=preco;}
    public void setQuantidade(int quant){this.quantidade=quant;}
    public void setTipo(String tipo){this.tipo=tipo;}
    public void setCodCli(String codCli){this.codCliente=codCli;}
    public void setMes(int mes){this.mes=mes;}
    
    /**
     * Método que calcula o total faturado nesta compra
     */
    public float totalFaturadoCompra(){
        float total = this.preco*this.quantidade;
        return total;
    }
    
    /**
     * equals
     */    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;
        else{
            Compra c = (Compra) o;
            if(this.codProduto.equals(c.getCodProd()) && this.preco==c.getPreco() && this.quantidade==c.getQuantidade() && this.tipo.equals(c.getTipo()) && this.codCliente.equals(c.getCodCli()) && this.mes==c.getMes()) return true;
            else return false;
        }
    }
    
    /**
     * toString
     */
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nInformação da Compra");
        s.append("\nCodigo Produto: "+this.codProduto);
        s.append("\nPreco: "+this.preco);
        s.append("\nQuantidade: "+this.quantidade);
        s.append("\nTipo: "+this.tipo);
        s.append("\nCodigo Cliente: "+this.codCliente);
        s.append("\nMes: "+this.mes);
        return s.toString();
    }
    
    /**
     * clone
     */
    public Compra clone() {
        return new Compra(this);
    }    
}