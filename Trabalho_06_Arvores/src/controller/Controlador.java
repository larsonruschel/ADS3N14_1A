package controller;

import java.util.ArrayList;
import java.util.Random;

import view.*;
import structure.*;
import utils.*;
import model.*;


public class Controlador {
	
	
	private view view;
	private AVL<Integer, Integer> avl;
	private RBT<Integer, Integer> rbt;
	private Nodo<Integer, Integer> nodo;
	private ArrayList<Integer> numeros;
	

	//Construtor
	
	public Controlador()
	{
		view = new view();
		numeros = new ArrayList<Integer>();
	}
	
	//Cria lista de numeros randomia e reversa
	private void criaNumeros(boolean random, boolean reverse)
	{
		if (random)
		{
			for (int i = 0; i < 50; i++)
			{
				numeros.add(Integer.valueOf((new Random().nextInt(100))));
			}
		}
		else
		{
			if (reverse)
			{
				for (int i = 50; i > 0; i--)
				{
					numeros.add(Integer.valueOf(i));
				}
			}
			else
			{	
				for (int i = 1; i < 51; i++)
				{
					numeros.add(Integer.valueOf(i));
				}
			}
		}
	}
	
//mostra lista dos numeros
	private void mostraNumeros()
	{
		view.mostraNumeros(numeros);
	}
	
	
	public void initApp()
	{
		int op = view.inicioApp();
		
		switch(op)
		{
		case 1:
			criaNumeros(true, false);
			mostraNumeros();
			CriaArvore();
			mostraArvore();
			mostraPrincipal();
			break;
		case 2:
			criaNumeros(false, false);
			mostraNumeros();
			CriaArvore();
			mostraArvore();
			mostraPrincipal();
			break;
		case 3:
			criaNumeros(false, true);
			mostraNumeros();
			CriaArvore();
			mostraArvore();
			mostraPrincipal();
			break;
		case 0:
			System.exit(0);
		default:
			view.mostraMsgErro("Informe uma opção válida!");
			initApp();
			break;
		}
	}
	
	
	// Adiciona nodo na arvore
	private void adicionaNodo()
	{
		Integer num = view.retInteger();
		Nodo<Integer, Integer> nodoAVL = new Nodo<Integer, Integer>();
		Nodo<Integer, Integer> nodoRBT = new Nodo<Integer, Integer>();
		nodoAVL.setValue(num);
		nodoAVL.setKey(num);
		
		nodoRBT.setValue(num);
		nodoRBT.setKey(num);
		nodoRBT.setBlack(false);
		
		avl.addNodeAVL(nodoAVL, true);
		rbt.addNodeRBT(nodoRBT, true);
		
		mostraPrincipal();
	}
	
	// Deleta nodo da arvore
	private void deletaNodo()
	{
		Integer num = view.retInteger();
		Nodo<Integer, Integer> delNodoAVL = avl.searchNode(num);
		Nodo<Integer, Integer> delNodoRBT = rbt.searchNode(num);
		
		if (delNodoAVL == null)
		{
			view.mostraMsgErro("Nenhum elemento encontrado!");
		}
		else
		{
			avl.deleteNode(delNodoAVL);
			rbt.deleteNode(delNodoRBT);
		}
		
		mostraPrincipal();
	}

	// Cria a Arvore
	private void CriaArvore()
	{
		avl = new AVL<Integer, Integer>();
		rbt = new RBT<Integer, Integer>();
		
		for (Integer item : numeros)
		{
			Nodo<Integer, Integer> nodoAVL = new Nodo<Integer, Integer>();
			Nodo<Integer, Integer> nodoRBT = new Nodo<Integer, Integer>();
			nodoAVL.setValue(item);
			nodoAVL.setKey(item);
			
			nodoRBT.setValue(item);
			nodoRBT.setKey(item);
			nodoRBT.setBlack(false);
			
			avl.addNodeAVL(nodoAVL, false);
			rbt.addNodeRBT(nodoRBT, false);
		}
		
		view.mostraTotal(true, avl.compTotal, avl.rotTotal);
		view.mostraTotal(false, rbt.compTotal, rbt.rotTotal);
	}
	
	//monta menu principal
		public void mostraPrincipal()
		{
			//showTrees();
			int op = view.mostraPrincipal();
			
			switch(op)
			{
			case 1:
				adicionaNodo();
				break;
			case 2:
				deletaNodo();
				break;
			case 0:
				System.exit(0);
			default:
				view.mostraMsgErro("Informe uma opção válida!");
				mostraPrincipal();
				break;
			}
		}

	//Mostra Arvore
	private void mostraArvore()
	{
		avl.showTree();
		rbt.showTree();
	}
}
