package com.robsonrodrigo.cursomc.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.robsonrodrigo.cursomc.domain.Categoria;
import com.robsonrodrigo.cursomc.repositories.CategoriaRepository;
import com.robsonrodrigo.cursomc.services.exceptions.DataIntegrityException;
import com.robsonrodrigo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos.");
			
		}
	} 
}
