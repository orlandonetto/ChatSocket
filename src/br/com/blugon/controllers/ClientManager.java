package br.com.blugon.controllers;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.blugon.views.ScreenManager;
import br.com.blugon.views.screens.ServerScreen;

public class ClientManager {

	private static ClientManager instance;
	private List<Socket> clients;

	private ClientManager() {
		clients = new ArrayList<>();
	}

	public static ClientManager getInstance() {
		if (instance == null)
			instance = new ClientManager();
		return instance;
	}

	public void addClient(Socket socket) {
		this.clients.add(socket);

		// Recebendo Mensagens dos clientes
		new Thread(new Runnable() {
			private Scanner scanner;

			@Override
			public void run() {
				try {
					scanner = new Scanner(socket.getInputStream());

					while (scanner.hasNextLine()) {
						if (ScreenManager.getInstance().getCurrentScreen() instanceof ServerScreen) {

							String text = scanner.nextLine();

							((ServerScreen) ScreenManager.getInstance().getCurrentScreen()).setTextAreaLog(text);

							// System.out.println(text);

							sendToAll(socket, text);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	public void sendToAll(Socket socket, String text) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < clients.size(); i++) {

					if (!clients.get(i).equals(socket)) {
						PrintStream saida;
						try {
							saida = new PrintStream(clients.get(i).getOutputStream());
							saida.println(text);
						} catch (Exception e) {

						}
					}
				}

			}
		}).start();
	}

	public List<Socket> getClients() {
		return this.clients;
	}

}
