package view;

import static java.lang.System.out;
import structures.*;
import model.*;

public class Menu {
	
	//Mostra um contato e apresenta o menu de opções.
	public void mostraContato(Node<Contato> node) {
		
		
		out.println("--------------------------------");
		out.println("Informe a Opção:");
		out.println("1 - Adicionar Contato");
		out.println("2 - Digitar uma letra do Contato");
		out.println("3 - Próximo Contato");
		out.println("4 - Contato Anterior ");
		out.println("0 - Sair");
		out.println("----------- Contatos -----------");
		out.println("Nome: " + node.getKey().getNome());
		out.println("Telefone: " + node.getKey().getTelefone());
		out.println("--------------------------------");
	}
	
    // Apresenta Mensagem de Erro!
	public void mensagemErro(String msg)
	{
		out.println(msg);
	}

   // Informar o nome
	public void informaNome()
	{
		out.println("Digite o Nome: ");
	}
	
    // Informar o telefone
	public void informaTelefone()
	{
		out.println("Digite o Telefone: ");
	}
}