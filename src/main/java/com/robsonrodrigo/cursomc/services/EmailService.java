package com.robsonrodrigo.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.robsonrodrigo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendEmailHtml(MimeMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
}
