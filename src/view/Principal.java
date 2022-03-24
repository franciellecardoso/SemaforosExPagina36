package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoasCaminhando;

public class Principal {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		for (int id = 1; id < 5; id++) {
			Thread t = new ThreadPessoasCaminhando(id, semaforo);
			t.start();
		}
	}
}
