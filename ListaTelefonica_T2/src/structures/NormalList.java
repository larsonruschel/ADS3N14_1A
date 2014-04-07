package structures;

import java.util.ArrayList;
import model.*;

public class NormalList {
	ArrayList<Node<Contato>> list = new ArrayList<Node<Contato>>();
	
	/**
	 * Constructor
	 * @param sorted A sorted list
	 */
	public NormalList(SortedList<Contato> sorted)
	{
		Node<Contato> current = sorted.getHead();
		
		// Create an ArrayList out of a sorted list
		while(current != null)
		{
			list.add(current);
			current = current.getNext();
		}
	}

	/**
	 * Search the ArrayList for an object with the starting string
	 * @param ch Starting string
	 * @return Node<Contact> found (null if not found)
	 */
	public Node<Contato> searchContact(String ch){
		boolean flagOk = false;
		int listSize = list.size();
		int currentPos = (int)Math.floor(listSize/2);
		Node<Contato> ret = null;
		int half = currentPos;
		
		while (!flagOk)
		{
			// Checks if the current position matches the search
			if (!list.get(currentPos).getKey().getNome().toUpperCase().startsWith(ch.toUpperCase()))
			{
				int compare = list.get(currentPos).getKey().getNome().substring(0, 1).toUpperCase().compareTo(ch.toUpperCase());
				
				half /= 2;
				if (compare > 0 && half >= 1)
				{
					currentPos -= half;
				}
				else if (compare < 0 && half >= 1)
				{
					currentPos += half;
				}
				else
				{
					flagOk = true;
				}
			}
			else
			{
				ret = list.get(currentPos);
				flagOk = true;
			}
		}
		
		return ret;
	}
	
	/**
	 * Search the ArrayList for an object with the starting string
	 * @param ch Starting string
	 * @return Count of comparisons made
	 */
	public int searchContact(String ch, boolean flag){
		boolean flagOk = false;
		int listSize = list.size();
		int currentPos = (int)Math.floor(listSize/2);
		int half = currentPos;
		int count = 0;
		
		while (!flagOk)
		{
			count++;
			// Checks if the current position matches the search
			if (!list.get(currentPos).getKey().getNome().toUpperCase().startsWith(ch.toUpperCase()))
			{
				int compare = list.get(currentPos).getKey().getNome().substring(0, 1).toUpperCase().compareTo(ch.toUpperCase());
				
				half /= 2;
				if (compare > 0 && half > 1)
				{
					currentPos -= half;
				}
				else if (compare < 0 && half > 1)
				{
					currentPos += half;
				}
				else
				{
					flagOk = true;
				}
			}
			else
			{
				flagOk = true;
			}
		}
		
		return count;
	}
}
