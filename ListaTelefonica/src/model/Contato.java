package model;

// Definição de atributos do contato
public class Contato implements Comparable<Contato> {
	
	private String nome;
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int compareTo(Contato contact) {
		return this.getNome().toLowerCase().compareTo(contact.getNome().toLowerCase());
	}
	

}