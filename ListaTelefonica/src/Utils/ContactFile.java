package Utils;

import java.util.Random;
import java.util.Scanner;
import java.io.*;

import model.*;
import structures.*;

public class ContactFile {

    // Cria o arquivo contatos.txt
	public boolean criaContato(int maxContacts) {
		Writer writer = null;

		if (arquivoExiste("contatos.txt")) {
			ContactFile.loadContacts();
			return true;
		}

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("contatos.txt"), "utf-8"));

			for (int i = 0; i < maxContacts; i++) {
				writer.write(geraNomes() + "##" + geraTelefone() + "\n");
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
	public static SortedList<Contato> loadContacts() {
		SortedList<Contato> ret = new SortedList<Contato>();
		Contato contact = new Contato();
		String row;

		// Carrega Contatos
		try {
			FileReader fReader = new FileReader("contatos.txt");
			BufferedReader textReader = new BufferedReader(fReader);

			while ((row = textReader.readLine()) != null) {
				contact = new Contato();
				
				String[] rowSplit = row.split("##");
				Node<Contato> node = new Node<Contato>();
				// Split Separa Nome do Telefone
				contact.setNome(rowSplit[0]);
				contact.setTelefone(rowSplit[1]);
				node.setKey(contact);
				
				ret.append(node);
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
	public static void adicionaContato(Contato contact)
	{		
		try(PrintWriter arq = new PrintWriter(new BufferedWriter(new FileWriter("contatos.txt", true)))) {
		    arq.println(contact.getNome().toUpperCase() + "##" + contact.getTelefone().toUpperCase());
		}catch (Exception ex) {}
	}
	

	private boolean arquivoExiste(String fPath) {
		File arquivo = new File(fPath);
		return arquivo.exists();
	}

// Gerardor de contatos
	private String geraNomes() {
		String ret = "";

		String[] nome = { "LARSON", "RAFAEL", "THIAGO", "RONALDO", "DUDA",
				           "MURIEL", "INDIO", "DALESSANDRO", "RENATO", "ALEX", "CHORAO",
				           "DIDA", "RUBINHO", "LEANDRO", "FELIPE", "GEORGE"
				          ,"RINGO", "LEONEL", "PAUL", "JOHN"};

		String[] sobrenome = { "RUSCHEL", "HARRISON", "STAR", "CALVIN","NAZARIO",
				               "ALFACE", "PATACHO", "CABECON", "RUSSO","BARRICHELO",
				               "DAMIAO", "MASSA", "STAR", "MESSI","LENNON",
				               "MCARTNEY", "HARRISON", "COBAIN", "FENDER","PEIXE"};

		// Random 
		ret = nome[(new Random()).nextInt(20)] +" "+ sobrenome[(new Random()).nextInt(20)];
		return ret;
	}

	private String geraTelefone() {
		String ret = "";
		String[] ddd = {"051"};
		
		ret = ddd[(new Random()).nextInt(1)];

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
