package br.com.blugon.views.screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import br.com.blugon.controllers.ClientController;
import br.com.blugon.models.Mensagem;
import br.com.blugon.utils.Current;
import br.com.blugon.views.Screen;

public class ChatScreen extends JPanel implements Screen, ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JTextArea areaMensagens;
	private JTextArea areaMsg;
	private JButton buttonEnviar;

	public ChatScreen() {

		setSize(410, 350);
		setBackground(Color.GRAY);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);

		areaMensagens = new JTextArea();
		areaMensagens.setEditable(false);
		areaMensagens.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(areaMensagens);
		scrollPane.setBounds(10, 11, 375, 228);
		add(scrollPane);

		areaMsg = new JTextArea();
		areaMsg.addKeyListener(this);
		areaMsg.setLineWrap(true);

		JScrollPane scrollPaneMsg = new JScrollPane(areaMsg);
		scrollPaneMsg.setBounds(10, 250, 290, 60);
		add(scrollPaneMsg);

		buttonEnviar = new JButton("Enviar");
		buttonEnviar.setBounds(310, 250, 75, 60);
		buttonEnviar.addActionListener(this);
		add(buttonEnviar);
	}

	@Override
	public void fillComponents() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonEnviar)) {
			sendMensage();
		}

	}

	private void sendMensage() {
		Mensagem mensagem = new Mensagem();
		mensagem.setUsuario(Current.USUARIO);
		mensagem.setMsg(areaMsg.getText().toString());
		mensagem.setDate(new Date(System.currentTimeMillis()));

		if (ClientController.getInstance().send(mensagem))
			areaMensagens.setText(areaMensagens.getText().toString() + mensagem.toString() + "\n");

		areaMsg.setText("");
	}

	public String getChat() {
		return areaMensagens.getText().toString();
	}

	public void setTextChat(String msg) {
		this.areaMensagens.setText(areaMensagens.getText().toString() + msg + "\n");
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.isShiftDown() && k.getKeyCode() == KeyEvent.VK_ENTER)
			areaMsg.append("\n");
		else if (k.getKeyCode() == KeyEvent.VK_ENTER && !areaMsg.getText().toString().trim().isEmpty())
			sendMensage();
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (!k.isShiftDown() && k.getKeyCode() == KeyEvent.VK_ENTER)
			areaMsg.setText("");

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}
}
