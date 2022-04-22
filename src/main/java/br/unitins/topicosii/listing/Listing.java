package br.unitins.topicosii.listing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.DefaultEntity;
import br.unitins.topicosii.respository.Repository;

public abstract class Listing<T extends DefaultEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String page;
	private Repository<T> repository;
	private List<T> list;

	public Listing(String page, Repository<T> repository) {
		super();
		this.page = page;
		this.repository = repository;
	}

	public void open() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("width", "70%");
		options.put("height", "60%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");

		PrimeFaces.current().dialog().openDynamic(page, options, null);
	}

	public void select(int id) {
		T obj = null;
		try {
			obj = repository.findById(id);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		PrimeFaces.current().dialog().closeDynamic(obj);
	}

	public abstract void pesquisar();

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Repository<T> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T> repository) {
		this.repository = repository;
	}

	public List<T> getList() {
		System.out.println("Lista");
		if (this.list == null)
			this.list = new ArrayList<T>();
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
