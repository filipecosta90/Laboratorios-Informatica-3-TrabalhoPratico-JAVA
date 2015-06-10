
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StringsMenu{
   
    private String[ ] opcoesMenuPrincipal;
    {
        opcoesMenuPrincipal = new String[5];
        opcoesMenuPrincipal[0] = "1-Carregar Ficheiros";
        opcoesMenuPrincipal[1] = "2-Estatísticas";
        opcoesMenuPrincipal[2] = "3-Queries Interativas";
        opcoesMenuPrincipal[3] = "4-Carregar/Guardar estado programa";
        opcoesMenuPrincipal[4] = "0-Sair do Programa";
    }
    
    private String[ ] opcoesMenuCarregarFicheiros;
    {
        opcoesMenuCarregarFicheiros = new String[6];
        opcoesMenuCarregarFicheiros[0] = "1-Carregar ficheiro de Produtos"; 
        opcoesMenuCarregarFicheiros[1] = "2-Carregar ficheiro de Clientes";
        opcoesMenuCarregarFicheiros[2] = "3-Carregar ficheiro de Compras standard";
        opcoesMenuCarregarFicheiros[2] = "4-Carregar ficheiro de Compras1";
        opcoesMenuCarregarFicheiros[2] = "5-Carregar ficheiro de Compras3";
        opcoesMenuCarregarFicheiros[3] = "6-Voltar ao menu anterior";
    }
    
    private String[ ] opcoesMenuQueriesInterativas;
    {
        opcoesMenuQueriesInterativas = new String[11];
        opcoesMenuQueriesInterativas[0] = "1-Lista ordenada com os códigos dos produtos nunca comprados e respectivo total";
        opcoesMenuQueriesInterativas[1] = "2-Lista ordenada com os códigos dos clientes que nunca compraram e seu total;";
        opcoesMenuQueriesInterativas[2] = "3-Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram";
        opcoesMenuQueriesInterativas[3] = "4-Dado um código de cliente, determinar, para cada mês, quantas compras fez, quantos produtos distintos comprou e quanto gastou. Apresentar também o total anual facturado ao cliente";
        opcoesMenuQueriesInterativas[4] = "5-Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado";
        opcoesMenuQueriesInterativas[5] = "6-Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi comprado em modo N e em modo P e respectivas facturações";
        opcoesMenuQueriesInterativas[6] = "7-Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou (e quantos), ordenada por ordem decrescente de quantidade e, para quantidades iguais, por ordem alfabética dos códigos";
        opcoesMenuQueriesInterativas[7] = "8-Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de unidades vendidas) indicando o número total de distintos clientes que o compraram (X é um inteiro dado pelo utilizador)";
        opcoesMenuQueriesInterativas[8] = "9-Determinar os X clientes que compraram um maior número de diferentes produtos, indicando quantos, sendo o critério de ordenação igual a 7";
        opcoesMenuQueriesInterativas[9] = "10-Dado o código de um produto, determinar o conjunto dos X clientes que mais o compraram e qual o valor gasto (ordenação cf. 7)";
        opcoesMenuQueriesInterativas[10] = "11-Voltar ao menu anterior";
    }
    
    private String[ ] opcoesMenuCarregarGuardar;
    {
        opcoesMenuCarregarGuardar = new String[3];
        opcoesMenuCarregarGuardar[0] = "1-Carregar estado programa";
        opcoesMenuCarregarGuardar[1] = "2-Guardar estado programa";
        opcoesMenuCarregarGuardar[2] = "3-Voltar ao menu anterior";
    }
    
    public StringsMenu (){
    
    }
    
    public String[] getOpcoesMenuPrincipal(){
        return this.opcoesMenuPrincipal;
    }
    
    public String[] getOpcoesMenuCarregarFicheiros(){
        return this.opcoesMenuCarregarFicheiros;
    }
    
    public String[] getOpcoesMenuQueriesInterativas(){
        return this.opcoesMenuQueriesInterativas;
    }
    
    public String[] getOpcoesMenuCarregarGuardar(){
        return this.opcoesMenuCarregarGuardar;
    }
}
