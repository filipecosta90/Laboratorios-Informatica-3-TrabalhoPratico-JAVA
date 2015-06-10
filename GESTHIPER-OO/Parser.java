/**
 * Classe responsável pelo parser dos ficheiros de input.
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.String;

public class Parser implements Serializable{
    
    public Parser( ){

    }
    
    /**
     * Mêtodo auxiliar que faz o parser a cada linha do ficheiro de compras
     */
    
    /*
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
                    if ((verificaCodClientes(cli)==true)&&(verificaSeExisteNoCatalCli(cli)==true)){
                        mes=Integer.parseInt(st.nextToken());
                        if(mes>=1 && mes<=12){
                            erro=true;
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
    
    */
    
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
}