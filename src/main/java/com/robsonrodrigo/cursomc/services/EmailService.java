package com.robsonrodrigo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.robsonrodrigo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
