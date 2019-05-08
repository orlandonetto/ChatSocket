package br.com.blugon.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensagem {

	private Usuario usuario;
	private String msg;
	private Date date;

	public Mensagem() {

	}

	public Mensagem(Usuario usuario, String msg, Date date) {
		this.usuario = usuario;
		this.msg = msg;
		this.date = date;
	}

	public Usuario usuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public String convertDateToString(Date date) {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return convertDateToString(date) + "  " + usuario.getNome().toUpperCase() + "  Disse: " + msg;
	}

}
