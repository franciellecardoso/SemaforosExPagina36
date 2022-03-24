package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoasCaminhando extends Thread {

	int id;
	private static int chegada;
	private static int saida;
	private Semaphore semaforo;

	public ThreadPessoasCaminhando(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		pessoasAndando();
		try {
			semaforo.acquire();
			chegouPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		cruzouPorta();
	}

	private void pessoasAndando() {
		int dPercorrida = 0;
		int dTotal = 200;
		int deslocamento = (int) ((Math.random() * 2) + 4);
		while (dPercorrida < dTotal) {
			dPercorrida += deslocamento;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A Pessoa #" + id + " já caminhou " + dPercorrida + "m.");
		}
		chegada++;
	}

	private void chegouPorta() {
		System.out.println("A Pessoa #" + id + " foi " + saida + "a. a chegar ");
		int tempo = (int) ((Math.random() * 1000) + 1001);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void cruzouPorta() {
		saida++;
		System.out.println("A Pessoa #" + id + " é " + saida + "a. a abrir e cruzar a porta");
	}
}
