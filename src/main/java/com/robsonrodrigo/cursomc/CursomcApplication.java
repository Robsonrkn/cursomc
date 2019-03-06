package com.robsonrodrigo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.robsonrodrigo.cursomc.domain.Categoria;
import com.robsonrodrigo.cursomc.domain.Cidade;
import com.robsonrodrigo.cursomc.domain.Cliente;
import com.robsonrodrigo.cursomc.domain.Endereco;
import com.robsonrodrigo.cursomc.domain.Estado;
import com.robsonrodrigo.cursomc.domain.ItemPedido;
import com.robsonrodrigo.cursomc.domain.Pagamento;
import com.robsonrodrigo.cursomc.domain.PagamentoComBoleto;
import com.robsonrodrigo.cursomc.domain.PagamentoComCartao;
import com.robsonrodrigo.cursomc.domain.Pedido;
import com.robsonrodrigo.cursomc.domain.Produto;
import com.robsonrodrigo.cursomc.domain.enums.EstadoPagamento;
import com.robsonrodrigo.cursomc.domain.enums.TipoCliente;
import com.robsonrodrigo.cursomc.repositories.CategoriaRepository;
import com.robsonrodrigo.cursomc.repositories.CidadeRepository;
import com.robsonrodrigo.cursomc.repositories.ClienteRepository;
import com.robsonrodrigo.cursomc.repositories.EnderecoRepository;
import com.robsonrodrigo.cursomc.repositories.EstadoRepository;
import com.robsonrodrigo.cursomc.repositories.ItemPedidoRepository;
import com.robsonrodrigo.cursomc.repositories.PagamentoRepository;
import com.robsonrodrigo.cursomc.repositories.PedidoRepository;
import com.robsonrodrigo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		


	}

}

