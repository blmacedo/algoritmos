package com.senac;

import java.util.Scanner;

import com.senac.algoritmos.AvaliadorRPN;
import com.senac.algoritmos.InvalidOperator;
import com.senac.estruturas.PilhaCheia;
import com.senac.estruturas.PilhaOperador;
import com.senac.estruturas.PilhaOperador2;
import com.senac.estruturas.PilhaVazia;

import static java.lang.System.*;
//funciona apenas se tiver espaços entre caracteres ex: 20 * ( 5 + 4 )
public class Planilha {
    PilhaOperador pilha;
    PilhaOperador2 pilha2;
    String saida;
    String dados;
	
	public static void main(String[] args) throws Exception {		
		(new Planilha()).run(args);
	} 
	
	public void run(String[] args)  throws Exception {
		entradaDados();
		
		while(!dados.toUpperCase().equals("FIM") ){
			calcularPilha(new Scanner(dados));
			out.println(AvaliadorRPN.avalia(saida));
			entradaDados();
		}out.print ("Fim do programa"); 
	}
	public void entradaDados(){
		Scanner scan = new Scanner (System.in);  
		out.print ("Digite uma espressão:");  
        dados = scan.nextLine();
	}

	public void calcularPilha(Scanner sc) throws Exception{
        pilha = new PilhaOperador(50);
        pilha2 = new PilhaOperador2(50);
        saida = "";
     
        while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				saida = saida + sc.nextInt() + " ";
			} else {
				String sinal = sc.next();
				switch (sinal) {
				case "+":ordenar(sinal); 
						break;
				case "-":ordenar(sinal); 
						break;
				case "*":ordenar(sinal); 
						break;
				case "/":ordenar(sinal); 
						break;
				case "(":ordenar(sinal); 
						break;
				case ")":calcular(); 
				break;
				}		
			}
        }

        while (!pilha2.isEmpty()) {
            saida = saida + pilha2.pop() + " ";
        }  
	}

	private void calcular() throws Exception {
		String aux = "";
		while(!pilha2.isEmpty()){
			aux = pilha2.pop();
			if(pilha2.pop() != "(") {
				saida = saida + aux + " ";	 break;
			}
		}
	}
	
	private void ordenar(String sinal) throws Exception {
		int prioridade = 0;
		
		if(sinal != "("){
			if ((sinal.equals("/")) || (sinal.equals("*"))){
				prioridade = 2;
			}else{
				prioridade = 1;
			}
		}

		while(!pilha2.isEmpty()){	
			int prioridadePilha;
			String operador = pilha2.pop();
			if(operador.equals("(")){
				 pilha2.push(operador);
				 break;
			}else{ 
				if ((operador.equals("/")) || (operador.equals("*"))){
					prioridadePilha = 2;
				}else{
					prioridadePilha = 1;
				}
				if(prioridadePilha > prioridade){
					pilha2.push(operador);
					break;
				}
			}
			saida = saida + operador + " ";
		}
		pilha2.push(sinal);
	}
}
