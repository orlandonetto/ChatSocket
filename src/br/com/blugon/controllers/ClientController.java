package br.com.blugon.controllers;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.blugon.models.Mensagem;
import br.com.blugon.views.ScreenManager;
import br.com.blugon.views.screens.ChatScreen;

public class ClientController {

	private static ClientController instance;
	private Socket socket;

	private ClientController() {

	}

	public static ClientController getInstance() {
		if (instance == null)
			instance = new ClientController();
		return instance;
	}

	public void connect(String ip, Integer port) {
		try {
			socket = new Socket(ip, port);
			receive();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean send(Mensagem mensagem) {
		PrintStream saida;
		try {
			saida = new PrintStream(socket.getOutputStream());
			saida.println(mensagem.toString());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void receive() {
		new Thread(new Runnable() {
			private Scanner scanner;

			@Override
			public void run() {
				try {
					scanner = new Scanner(socket.getInputStream());

					while (scanner.hasNextLine()) {
						String text = scanner.nextLine();

						if (ScreenManager.getInstance().getCurrentScreen() instanceof ChatScreen)
							((ChatScreen) ScreenManager.getInstance().getCurrentScreen()).setTextChat(text);

						System.out.println(text);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
