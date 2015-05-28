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
     * Getters && Setters
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
    @Override
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
    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\n\tInformação da Compra");
        s.append("\n\tCodigo Produto: "+this.codProduto);
        s.append("\n\tPreco: "+this.preco);
        s.append("\n\tQuantidade: "+this.quantidade);
        s.append("\n\tTipo: "+this.tipo);
        s.append("\n\tCodigo Cliente: "+this.codCliente);
        s.append("\n\tMes: "+this.mes);
        return s.toString();
    }
    
    /**
     * clone
     */
    @Override
    public Compra clone() {
        return new Compra(this);
    }    
}