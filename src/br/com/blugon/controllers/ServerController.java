package br.com.blugon.controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.blugon.utils.Current;

public class ServerController implements Runnable {

	private static ServerController instance;
	private ServerSocket serverSocket;
	private Thread thread;

	private ServerController() {
		initComponents();
	}

	public static ServerController getInstance() {
		if (instance == null) {
			instance = new ServerController();
		}
		return instance;
	}

	private void initComponents() {
		try {
			serverSocket = new ServerSocket(Current.PORT);
			thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			while (true) {
				System.out.println("Aguardando conexões");
				Socket socket = serverSocket.accept();
				System.out.println("Nova conexão com o cliente " + socket.getInetAddress().getHostName() + " | "
						+ socket.getInetAddress().getHostAddress());

				ClientManager.getInstance().addClient(socket);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
