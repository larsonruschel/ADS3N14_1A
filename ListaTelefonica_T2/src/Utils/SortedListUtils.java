package Utils;

import model.*;
import structures.*;

public class SortedListUtils {
	private SortedList<Contato> lista;
	

	public SortedListUtils(SortedList<Contato> lista)
	{
		this.lista = lista;
	}

	// Procura na lista de contato e se encontra retornar o primeiro
	public Node<Contato> getFromChar(String chr)
	{
		Node<Contato> current = this.lista.getHead();
		
		while (current != null)
		{
			if (current.getKey().getNome().toLowerCase().startsWith(chr.toLowerCase()))
			{
				return current;
			}
			current = current.getNext();
		}
		
		return null;
	}
	
	// Procura na lista de contato e se encontra retornar o count de comparações
	public int getFromChar(String chr, boolean flag)
	{
		Node<Contato> current = this.lista.getHead();
		int count = 0;
		
		while (current != null)
		{
			count++;
			if (current.getKey().getNome().toLowerCase().startsWith(chr.toLowerCase()))
			{
				break;
			}
			current = current.getNext();
		}
		
		return count;
	}
}
