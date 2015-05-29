/**
 * Classe responsável pelo parser dos ficheiros de input.
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.String;

public class Parser implements Serializable{
    
    private static Catalogo_Produtos catP;
    private static Catalogo_Clientes catC;
    private static Compras compras;
    private static Contabilidade cont;
    
    private static int prodValidados;
    private static int prodRejeitados;
    
    private static int cliValidados;
    private static int cliRejeitados;
    
    private static int compValidadas;
    private static int compRejeitadas;
    
    public Parser(){
        this.catP=new Catalogo_Produtos();
        this.catC=new Catalogo_Clientes();
        this.prodValidados=0;
        this.prodRejeitados=0;
        this.cliValidados=0;
        this.cliRejeitados=0;
    }
    
    /**
     * Getters
     */
    public Catalogo_Produtos getCatProd(){
        return catP;
    }
    
    public Catalogo_Clientes getCatCli(){
        return catC;
    }
    
    public int getProdValidados(){
        return this.prodValidados;
    }
    
    public int getProdRejeitadas(){
        return this.prodRejeitados;
    }
    
    public int getCliValidados(){
        return this.cliValidados;
    }
    
    public int getCliRejeitadas(){
        return this.cliRejeitados;
    }
    
    /**
     * Método que lê o ficheiro clientes
     */
    /*public void lerFichClientes(String file) {
        Scanner sFile = null;
        try{
            sFile = new Scanner(new FileReader(file));
            sFile.useDelimiter("\n");
            while(sFile.hasNext()){
                String linha = sFile.nextLine();
                if (verificaCodClientes(linha)==true){
                    catC.addCodToCatalCli(linha);
                    cliValidados++;
                }
                else{
                    cliRejeitados++;
                }
            }
            System.out.println(catC.toString());
            System.out.println("Clientes Validadas: "+cliValidados+"\n");
            System.out.println("Clientes Rejeitados: "+cliRejeitados+"\n");
        }
        catch(IOException e){
             System.out.println(e.getMessage());
        }
        finally{
            if (sFile!=null) sFile.close();
        }
    }*/
    public void lerFichClientes(String file){
        File fich = new File(file);
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String codigo;
            while(((codigo = br.readLine())!=null)){
                
                if (verificaCodClientes(codigo)==true){
                    //String newCod = new String ( codigo );
                    catC.addCodToCatalCli(codigo);
                    cliValidados++;
                }
                else{
                    cliRejeitados++;
                }
            }
            System.out.println(catC.toString());
            System.out.println("Clientes Validados: "+cliValidados+"\n");
            System.out.println("Clientes Rejeitados: "+cliRejeitados+"\n");
            System.out.println("TreeSet Size: "+ catC.getCataCli().size() +"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Método que lê o ficheiro produtos
     */    
    /*public void lerFichProdutos(String file){
        Scanner sFile = null;
        try{
            sFile = new Scanner(new FileReader(file));
            sFile.useDelimiter("\n");
            while(sFile.hasNext()){
                String linha = sFile.nextLine();
                if (verificaCodProdutos(linha)==true){
                    catP.addCodToCatalProd(linha);
                    prodValidados++;
                }
                else{
                    prodRejeitados++;
                }
            }
            System.out.println(catP.toString());
            System.out.println("Produtos Validados: "+prodValidados+"\n");
            System.out.println("Produtos Rejeitados: "+prodRejeitados+"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (sFile!=null) sFile.close();
        }
    }*/
    public void lerFichProdutos(String file){
        File fich = new File(file);
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String codigo;
            while(((codigo = br.readLine())!=null)){
                if (verificaCodProdutos(codigo)==true){
                    catP.addCodToCatalProd(codigo);
                    prodValidados++;
                }
                else{
                    prodRejeitados++;
                }
            }
            System.out.println(catP.toString());
            System.out.println("Produtos Validados: "+prodValidados+"\n");
            System.out.println("Produtos Rejeitados: "+prodRejeitados+"\n");
            System.out.println("TreeSet Size: "+ catP.getCataProd().size() +"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
        /**
     * Método que lê o ficheiro compras
     */
    /*public void lerFichCompras(String file){
        Scanner sFile = null;
        try{
            sFile = new Scanner(new FileReader(file));
            sFile.useDelimiter(" ");
            int count = 0;
            int validadas = 0;
            int rejeitadas = 0;
            while(sFile.hasNext()){
                String linha = sFile.nextLine();
                if(parserLinhaCompras(linha)==true){validadas++;}
                else{rejeitadas++;}
                count++;
            }
            System.out.println("Total: "+count+"\n");
            System.out.println("Total validadas: "+validadas+"\n");
            System.out.println("Total rejeitadas: "+rejeitadas+"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (sFile!=null) sFile.close();
        }
    }*/
    public void lerFichCompras(String file){
        File fich = new File(file);
        int count=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fich));
            String linha;
            while(((linha = br.readLine())!=null)){
                if(parserLinhaCompras(linha)==true){compValidadas++;}
                else{compRejeitadas++;}
                count++;
            }
            System.out.println("Total: "+count+"\n");
            System.out.println("Total validadas: "+compValidadas+"\n");
            System.out.println("Total rejeitadas: "+compRejeitadas+"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Mêtodo auxiliar que faz o parser a cada linha do ficheiro de compras
     */
    private boolean parserLinhaCompras (String linha){
        Scanner sFile = null;
        String prod = null;
        float preco = 0;
        int quant = 0;
        String tipo = null;
        String cli = null;
        int mes = 0;
        boolean erro = false;
        StringTokenizer st = new StringTokenizer(linha);
        while (st.hasMoreTokens() && !erro) {
            prod=st.nextToken();
            if ((verificaCodProdutos(prod)==true) && (verificaSeExisteNoCatalProd(prod)==true)){
                preco=Float.parseFloat(st.nextToken());
                quant=Integer.parseInt(st.nextToken());
                tipo=st.nextToken();
                if(verificaTipoCompra(tipo)==true){
                    cli=st.nextToken();
                    if ((verificaCodClientes(cli)==true)&&(verificaSeExisteNoCatalCli(cli))){
                        mes=Integer.parseInt(st.nextToken());
                        if(mes>=1 && mes<=12){
                            erro=true;
                            System.out.println("Produto: "+prod+" Preco "+preco+" Quantidade: "+quant+" Tipo: "+tipo+" Cliente "+cli+" Mes: "+mes+"\n");
                    
                        }
                        else{
                            erro=false;
                        }
                    }
                    else{
                        erro=false;
                    }
                }
                else{
                    erro=false;
                }
           }
           else{
                erro=false;
           }
        }
        if(erro==true){return true;}
        else{return false;}
    }
    
    /**
     * Método auxiliar que verifica se o codigo de um produto de uma compra existe no catalogo de produtos
     */
    private boolean verificaSeExisteNoCatalProd(String cod){
        if(this.catP.existeProd(cod)==true){return true;}
        else{return false;}
    }
    
    /**
     * Método auxiliar que verifica se o codigo de um cliente de uma compra existe no catalogo de clientes
     */
    private boolean verificaSeExisteNoCatalCli(String cod){
        if(this.catC.existeCli(cod)){return true;}
        else{return false;}
    }
    
    /**
     * Método auxiliar que verifica o tipo da compra isto é se é N->normal ou P->promoção
     */
    private boolean verificaTipoCompra(String tipo){
        if(tipo.length()==1 && (tipo.equals("N") || tipo.equals("n") || tipo.equals("P") || tipo.equals("p"))) {           
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Método auxiliar que verifica se o codigo do clientes é um código válido
     */
    private boolean verificaCodClientes(String codCliente){
        char[] cod = codCliente.toCharArray(); 
        if(codCliente.length()==5){
            if((Character.isLetter(cod[0])==true) && (Character.isLetter(cod[1])==true)){
                if((Character.isDigit(cod[2])==true) && (Character.isDigit(cod[3])==true) && (Character.isDigit(cod[4])==true)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }       
        }
        else{
            return false;
        }
    }
    
    /**
     * Método auxiliar que verifica se o código do produto é um código válido
     */    
    private boolean verificaCodProdutos(String codProduto){
        char[] cod = codProduto.toCharArray(); 
        if(codProduto.length()==6){
            if((Character.isLetter(cod[0])==true) && (Character.isLetter(cod[1])==true)){
                if((Character.isDigit(cod[2])==true) && (Character.isDigit(cod[3])==true) && (Character.isDigit(cod[4])==true) && (Character.isDigit(cod[5])==true)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }       
        }
        else{
            return false;
        }
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nClientes Validados: "+this.cliValidados);
        s.append("\nClientes Rejeitados: "+this.cliRejeitados);
        s.append(this.catC.toString());
        s.append("\nTreeSet Size: "+catC.getCataCli().size());
        s.append("\n\n");
        s.append("\nProdutos Validados: "+this.prodValidados);
        s.append("\nProdutos Rejeitados: "+this.prodRejeitados);
        s.append(this.catP.toString());
        s.append("\nTreeSet Size: "+catP.getCataProd().size());
        s.append("\n\n");
        return s.toString();
    }
}