package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.AlbumModel;
import br.com.fiap.repository.AlbumRepository;
import br.com.fiap.repository.BandaRepository;
import br.com.fiap.repository.SessionSingleton;

@Controller
@RequestMapping("/albums")
public class AlbumController {

	private static final String ALBUMS_FOLDER = "album/";
	
	@Autowired
	private AlbumRepository repository;
	
	@Autowired
	private BandaRepository bandaRepository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page, @RequestParam(required = false) Long id, @ModelAttribute("AlbumModel") AlbumModel albumModel, Model model) {
		
		if("album-editar".equals(page)) {
			model.addAttribute("albumModel", repository.findById(id));
			SessionSingleton.getInstance().getSession().clear();
		}
		
		model.addAttribute("bandas", bandaRepository.findAll());
		
		return ALBUMS_FOLDER + page;
		
	}
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("albums", repository.findAll());
		return ALBUMS_FOLDER + "albums"; 
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("album", repository.findById(id));
		return ALBUMS_FOLDER + "album-detalhe";
	}
	
	@PostMapping()
	public String save(@Valid AlbumModel albumModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("bandas", bandaRepository.findAll());
			return ALBUMS_FOLDER + "album-novo";
		}
		
		repository.saveAlbum(albumModel);
		redirectAttributes.addFlashAttribute("messages", "Album Cadastrado com sucesso!");
		
		return "redirect:/albums";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid AlbumModel albumModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return ALBUMS_FOLDER + "album-editar";
		}
		
		albumModel.setId(id);
		repository.updateAlbum(albumModel);
		redirectAttributes.addFlashAttribute("messages", "Album alterado com sucesso");
		
		return "redirect:/albums";
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		repository.deleteAlbum(repository.findById(id));
		redirectAttributes.addFlashAttribute("messages", "Album excluído com sucesso!");
		
		return "redirect:/albums";
	}
	
}
