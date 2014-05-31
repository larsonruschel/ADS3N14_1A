package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import utils.*;
import structure.*;
import static java.lang.System.out;


public class view 
{
	//Msg Erro
	public void mostraMsgErro(String msg)
	{
		out.println("==================================");
		out.println(msg);
		out.println("==================================");
	}
	
	//Menu Inicial
	public int inicioApp()
	{
		int op = -1;

		out.println("------ Informe uma das Opções ------");
		out.println("1 - Criar Lista Aleatória");
		out.println("2 - Criar Lista Organizada");
		out.println("3 - Criar Lista Organizada Revertida");
		out.println("0 - Sair.");
		out.println("------------------------------------");
		
		try
		{
			op = (new Scanner(System.in)).nextInt();
		}
		catch (Exception ex) {}
		
		return op;
	}
	

    // Menu Ações
	public int mostraPrincipal()
	{
		int op = -1;
		out.println("---- Informe uma das Opções ----");
		out.println("1 - Adicionar Elemento.");
		out.println("2 - Excluir Elemento");
		out.println("0 - Sair.");
		out.println("--------------------------------");
		
		try
		{
			op = (new Scanner(System.in)).nextInt();
		}
		catch (Exception ex) {}
		
		return op;
	}
	
	//Mostra lista de números
	public void mostraNumeros(ArrayList<Integer> list)
	{
		out.println("Números: ");
		for (Integer item : list)
		{
			out.print(item + " ");
		}
		
		out.println("");
		out.println("");
	}

    // Adiciona no Nodo
	public Integer retInteger()
	{
		boolean flagOk = false;
		Integer value = Integer.valueOf(0);
		
		do
		{
			System.out.println("Informe o valor: ");
			try {
				value = Integer.valueOf((new Scanner(System.in).nextInt()));
				flagOk = true;
			} catch (Exception ex)
			{
				mostraMsgErro("Informe um valor válido!");
			}
		} while(!flagOk);
		
		return value;
	}
	

	
    //Calcula comparações
	public void mostraTotal(boolean isAVL, int comp, int rot)
	{
		if (isAVL) mostraMsgErro("AVL:\nComparações Totais: " + comp + "\nRotações totais: " + rot); 
		else mostraMsgErro("RBT:\nComparações Totais: " + comp + "\nRotações totais: " + rot);
	}
}
