package controller;

import java.util.Scanner;

import Utils.ContactFile;
import Utils.SortedListUtils;
import model.Contato;
import view.Menu;
import structures.*;

public class Controlador {
	
	private Menu menu;
	private Node<Contato> nodo;
	private SortedList<Contato> lista;
	
//Construtor para inicar as variáveis.
	public Controlador()
	{
		lista = ContactFile.loadContacts();
		menu = new Menu();
		nodo = lista.getHead();
	}
	
// Mostra um contato e o menu
	public void mostraContato()
	{
		menu.mostraContato(nodo);
		boolean flagOk = true;
		int option;
		Node<Contato> tempContact;
		
		while(flagOk)
		{
			
			try {
				option = (new Scanner(System.in)).nextInt();
			} catch(Exception ex) { continue; }
			
			switch(option)
			{
			case 1:
				// Adiciona Contato
				String nome, telefone;
				Node<Contato> node = new Node<Contato>();
				Contato contato = new Contato();
				
				// Insere Dados
				menu.informaNome();
				nome = (new Scanner(System.in)).nextLine();
				menu.informaTelefone();
				telefone = (new Scanner(System.in)).nextLine();
				
				contato.setNome(nome);
				contato.setTelefone(telefone);
				node.setKey(contato);
				
				ContactFile.adicionaContato(contato);
				lista.insert(node);
				
				mostraContato();
				
				break;
				
			case 2:
				// Insert 1 letra
				SortedListUtils sortedUtils = new SortedListUtils(this.lista);
				String letra = (new Scanner(System.in)).nextLine();
				
				if (letra.length() > 1)
				{
					this.menu.mensagemErro("Digite somente uma letra!");
				}
				else
				{
					Node<Contato> nextNode = sortedUtils.getFromChar(letra);
					
					if (nextNode == null)
					{
						this.menu.mensagemErro("Nenhum Registro Encontrado...");
					}
					else {
						this.nodo = nextNode;
					}
				}
				
				mostraContato();

				break;
			case 3:
				// Próximo Contato
				tempContact = nodo.getNext();
				this.nodo = tempContact == null ? lista.getHead() : tempContact;
				
				mostraContato();
				break;
			case 4:

				// Contato Anterior
				tempContact = lista.getPrevious(nodo);
				this.nodo = tempContact == null ? lista.getTail() : tempContact;
				
				mostraContato();
				break;

			case 0:
				// Sair
				System.exit(0);
				break;
			}
		}
	}
}
