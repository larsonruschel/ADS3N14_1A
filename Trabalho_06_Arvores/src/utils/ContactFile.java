package utils;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import model.*;
import structure.*;

//Cria o arquivo contatos.txt
public class ContactFile {


	public boolean createContacts(int maxContacts) {
		Writer writer = null;

		if (checkFileExists("contacts.txt")) {
			ContactFile.loadContacts();
			return true;
		}

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("contacts.txt"), "utf-8"));

			for (int i = 0; i < maxContacts; i++) {
				writer.write(generateName() + "##" + generatePhone() + "\n");
			}

		} catch (IOException ex) {
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				return false;
			}
		}

		return true;
	}

	// Se já existir, carrega a lista
	public static void saveContacts(Arvore<Contato, String> tree)
	{
		ArrayList<Nodo<Contato, String>> queueNode = new ArrayList<Nodo<Contato, String>>();
		ArrayList<Nodo<Contato, String>> lstNode = new ArrayList<Nodo<Contato, String>>();
		
		// Adiciona
		queueNode.add(tree.getRoot());
		
		// Enquando n for null
		while(!queueNode.isEmpty())
		{
			Nodo<Contato, String> node = queueNode.get(0);
			lstNode.add(node);
			queueNode.remove(node);
			
			if (node.getLeftNode() != null)
			{
				queueNode.add(node.getLeftNode());
			}
			
			if (node.getRightNode() != null)
			{
				queueNode.add(node.getRightNode());
			}
		}
		
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contacts.txt"), "utf-8"));
			
			for (Nodo<Contato, String> item : lstNode)
			{
				writer.write(item.getValue().getNome() + "##" + item.getValue().getTelefone() + "\n");
			}
		}
		catch (IOException ex) {}
		finally 
		{
			try
			{
				writer.close();
			}
			catch (IOException e) {}
		}
	}
	

	public static Arvore<Contato, String> loadContacts() {
		Arvore<Contato, String> ret = new Arvore<Contato, String>();
		Nodo<Contato, String> nRoot = null;
		Contato contact = new Contato();
		String row;

		// Load the contacts files
		try {
			FileReader fReader = new FileReader("contacts.txt");
			BufferedReader textReader = new BufferedReader(fReader);

			// For each row
			while ((row = textReader.readLine()) != null) {
				contact = new Contato();
				String[] rowSplit = row.split("##");
				Nodo<Contato, String> node = new Nodo<Contato, String>();
				
				contact.setNome(rowSplit[0]);
				contact.setTelefone(rowSplit[1]);
				node.setValue(contact);
				node.setKey(rowSplit[0]);
				
				node.setRoot(nRoot);
				
				ret.addNode(node, false);
			}
			
			textReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}

		return ret;
	}

	// Adiciona contatos ao arquivo
	public static void addContact(Contato contact)
	{		
		try(PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter("contacts.txt", true)))) {
		    file.println(contact.getNome().toUpperCase() + "##" + contact.getTelefone().toUpperCase());
		}catch (Exception ex) {}
	}
	

	private boolean checkFileExists(String fPath) {
		File file = new File(fPath);
		return file.exists();
	}

	// Gerardor de contatos
	private String generateName() {
		String ret = "";

		String[] names = { "LARSON", "RAFAEL", "THIAGO", "RONALDO", "DUDA",
		           "MURIEL", "INDIO", "DALESSANDRO", "RENATO", "ALEX", "CHORAO",
		           "DIDA", "RUBINHO", "LEANDRO", "FELIPE", "GEORGE"
		          ,"RINGO", "LEONEL", "PAUL", "JOHN"};

String[] lastnames = { "RUSCHEL", "HARRISON", "STAR", "CALVIN","NAZARIO",
		               "ALFACE", "PATACHO", "CABECON", "RUSSO","BARRICHELO",
		               "DAMIAO", "MASSA", "STAR", "MESSI","LENNON",
		               "MCARTNEY", "HARRISON", "COBAIN", "FENDER","PEIXE"};

//Random 
		ret = names[(new Random()).nextInt(20)] +" "+ lastnames[(new Random()).nextInt(20)];
		return ret;
	}

	/**
	 * Generates a random phone
	 * 
	 * @return Generated phone
	 */
	private String generatePhone() {
		String ret = "";
		String[] areaCode = { "051" };

		// Gets an area code
		ret = areaCode[(new Random()).nextInt(1)];

		for (int i = 0; i < 8; i++) {
			int n = (new Random()).nextInt(9);

			if (i == 0 && n == 0) {
				i -= 1;
				continue;
			} else {
				ret += n;
			}
		}

		return ret;
	}

}
