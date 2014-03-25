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
}
