package com.robsonrodrigo.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.robsonrodrigo.cursomc.domain.Cliente;
import com.robsonrodrigo.cursomc.domain.ItemPedido;
import com.robsonrodrigo.cursomc.domain.PagamentoComBoleto;
import com.robsonrodrigo.cursomc.domain.Pedido;
import com.robsonrodrigo.cursomc.domain.enums.EstadoPagamento;
import com.robsonrodrigo.cursomc.repositories.ClienteRepository;
import com.robsonrodrigo.cursomc.repositories.ItemPedidoRepository;
import com.robsonrodrigo.cursomc.repositories.PagamentoRepository;
import com.robsonrodrigo.cursomc.repositories.PedidoRepository;
import com.robsonrodrigo.cursomc.repositories.ProdutoRepository;
import com.robsonrodrigo.cursomc.security.UserSS;
import com.robsonrodrigo.cursomc.services.exceptions.AuthorizationException;
import com.robsonrodrigo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteRepository.findOne(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
}
