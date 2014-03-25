package App;

import controller.Controlador;
import Utils.ContactFile;
import structures.*;
import model.*;

public class Main {
	public static void main(String[] args) {
		
		//Chama o Controlador para carregar op��es
		Controlador ctr = new Controlador();
		//Passa o n�mero de contatos para a lista
		(new ContactFile()).criaContato(20);
		//Mostra os contatos
		ctr.mostraContato();
		
	}
}