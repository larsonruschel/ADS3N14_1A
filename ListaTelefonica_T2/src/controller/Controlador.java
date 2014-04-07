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
	
	private NormalList normalList;
	
//Construtor para inicar as variáveis.
	public Controlador()
	{
		lista = ContactFile.loadContacts();
		menu = new Menu();
		nodo = lista.getHead();
		normalList = new NormalList(lista);
	}
	
// mostra contatos e o menu
	public void mostraContato()
	{
		menu.mostraContato(nodo);
		boolean flagOk = true;
		int option;
		
		
		while(flagOk)
		{
			
			try {
				option = (new Scanner(System.in)).nextInt();
			} catch(Exception ex) { continue; }
			
			switch(option)
			{
			case 1:
				contatoAnterior();
				mostraContato();
				break;
			case 2:
				proximoContato();
				mostraContato();
				break;
			case 3:
				infoContato();
				mostraContato();
				break;
			case 4:
				adicionaContato();
				mostraContato();
				break;
			case 0:
				// Exit
				flagOk = false;
				System.exit(0);
				break;
			}
		}
	}

	//Contato anterior
	private void contatoAnterior()
	{
		Node<Contato> tempContact;
		tempContact = lista.getPrevious(nodo);
		this.nodo = tempContact == null ? lista.getTail() : tempContact;
	}
	
    // pega o próximo contato
	private void proximoContato()
	{
		Node<Contato> tempContact;
		tempContact = nodo.getNext();
		this.nodo = tempContact == null ? lista.getHead() : tempContact;
	}
	
	private void infoContato()
	{
		// Valida infromações
		SortedListUtils sortedUtils = new SortedListUtils(this.lista);
		String ch = (new Scanner(System.in)).nextLine();
		int comparaLista = 0;
		int comparaBinario = 0;
		
		if (ch.length() > 1)
		{
			this.menu.mensagemErro("Informe somente uma letra...");
		}
		else
		{
			Node<Contato> nextNode = normalList.searchContact(ch);
			
			if (nextNode == null)
			{
				this.menu.mensagemErro("Nenhum Registro Encontrado...");
			}
			else {
				this.nodo = nextNode;
			}
		}
		
		comparaLista = sortedUtils.getFromChar(ch, true);
		comparaBinario = normalList.searchContact(ch, true);
		this.menu.mostraLista(comparaLista);
		this.menu.mostraBinarios(comparaBinario);
		
	}
	
	/**
	 * Adds the a contact (List and file)
	 */
	private void adicionaContato()
	{
		// Adicionando contato
		String name, phone;
		Node<Contato> node = new Node<Contato>();
		Contato contact = new Contato();
		
		// Informações
		menu.informaNome();
		name = (new Scanner(System.in)).nextLine();
		menu.informaTelefone();
		phone = (new Scanner(System.in)).nextLine();
		
		contact.setNome(name);
		contact.setTelefone(phone);
		node.setKey(contact);
		
		// Insere
		ContactFile.adicionaContato(contact);
		lista.insert(node);
		
	}
}
