package model;


public class Contato implements Comparable<Contato> {
	// Atributos
	private String nome;
	private String telefone;

	// Getters & Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String phone) {
		this.telefone = phone;
	}

	// CompareTo
	@Override
	public int compareTo(Contato contact) {
		return this.getNome().trim().toLowerCase().compareTo(contact.getNome().trim().toLowerCase());
	}
	
	/**
	 * Return a 'toString' String (Name + Phone)
	 */
	public String toString()
	{
		String ret = "===========================\n";
		ret += "Nome: " + getNome() + "\n";
		ret += "Telefone: " + getTelefone() + "\n";
		ret += "===========================\n";
		
		return ret;
	}
}
