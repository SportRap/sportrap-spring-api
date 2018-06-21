package br.com.sportrap.api.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.sportrap.api.model.Evento;

public class Funcoes {

	public static boolean enviarEmail(String emailDestinatario, StringBuilder mensagem, String assunto) {
		String de = emailDestinatario;
		String para = "suportesportrap@gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.user", "SportRap");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.EnableSSL.enable", "true");

		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallbac k", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("suportesportrap@gmail.com", "sportrap123");
			}
		};

		Session session = Session.getDefaultInstance(properties, authenticator);

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(para));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(de));
			message.setSubject(assunto);
			message.setContent(mensagem.toString(), "text/html");

			Transport.send(message);

			System.out.println("Mensagem enviada com sucesso.");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	public static boolean timeAceitaParticipantes(Evento evento, int time) {
		int participantePorTime = evento.getEsporteEscolhidoEnum().getJogadoresPorTime();
				
		if(time == 1){
			return participantePorTime < evento.getMembrosTime1().size();
		}else if(time == 2){
			return participantePorTime < evento.getMembrosTime2().size();
		}
			
		return false;
	}

}
