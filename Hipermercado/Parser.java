/**
 * Write a description of class Parser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.String;

public class Parser{
    
    public static void lerFichClientes(String file){
        Scanner sFile = null;
        try{
            sFile = new Scanner(new FileReader(file));
            sFile.useDelimiter("\n");
            int validadas = 0;
            int rejeitadas = 0;
            while(sFile.hasNext()){
                String linha = sFile.nextLine();
                if (verificaCodClientes(linha)==true){
                    System.out.println(linha);
                    validadas++;
                }
                else{
                    rejeitadas++;
                }
            }
            System.out.println("Total validadas: "+validadas+"\n");
            System.out.println("Total rejeitadas: "+rejeitadas+"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (sFile!=null) sFile.close();
        }
    }
    
        public static void lerFichProdutos(String file){
        Scanner sFile = null;
        try{
            sFile = new Scanner(new FileReader(file));
            sFile.useDelimiter("\n");
            int validadas = 0;
            int rejeitadas = 0;
            while(sFile.hasNext()){
                String linha = sFile.nextLine();
                if (verificaCodProdutos(linha)==true){
                    System.out.println(linha);
                    validadas++;
                }
                else{
                    rejeitadas++;
                }
            }
            System.out.println("Total validadas: "+validadas+"\n");
            System.out.println("Total rejeitadas: "+rejeitadas+"\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (sFile!=null) sFile.close();
        }
    }
    
    public static boolean parserLinhaCompras (String linha){
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
            if (verificaCodProdutos(prod)==true){
                preco=Float.parseFloat(st.nextToken());
                quant=Integer.parseInt(st.nextToken());
                tipo=st.nextToken();
                if(verificaTipoCompra(tipo)==true){
                    cli=st.nextToken();
                    if (verificaCodClientes(cli)==true){
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
    
    public static void lerFichCompras(String file){
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
    }
    
    public static boolean verificaTipoCompra(String tipo){
        if(tipo.length()==1 && (tipo.equals("N") || tipo.equals("n") || tipo.equals("P") || tipo.equals("p"))) {           
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean verificaCodClientes(String codCliente){
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
    
        public static boolean verificaCodProdutos(String codProduto){
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
    
    public static void main(){
        //String clientes = "files/FichClientes.txt";
        //lerFichClientes(clientes);
        //String produtos = "files/FichProdutos.txt";
        //lerFichProdutos(produtos);
        String compras = "files/Compras.txt";
        lerFichCompras(compras);
    }
}
