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

import br.com.fiap.model.BandaModel;
import br.com.fiap.repository.BandaRepository;
import br.com.fiap.repository.SessionSingleton;

@Controller
@RequestMapping("/bandas")
public class BandaController {

	private static final String BANDAS_FOLDER = "banda/";
	
	@Autowired
	private BandaRepository repository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page,
	@RequestParam(required = false) Long id,
	@ModelAttribute("BandaModel") BandaModel bandaModel,
	Model model) {
		
		
		if("banda-editar".equals(page)) {
			model.addAttribute("bandaModel", repository.findById(id));
			SessionSingleton.getInstance().getSession().clear();
		}
		
		return BANDAS_FOLDER + page;
		
	}
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("bandas", repository.findAll());
		return BANDAS_FOLDER + "bandas";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("banda", repository.findById(id));
		return BANDAS_FOLDER + "banda-detalhe";
	}
	
	@PostMapping()
	public String save(@Valid BandaModel bandaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return BANDAS_FOLDER + "banda-nova";
		}
		
		repository.saveBanda(bandaModel);
		redirectAttributes.addFlashAttribute("messages", "Banda cadastrada com sucesso!");
		
		return "redirect:/bandas";
		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid BandaModel bandaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return BANDAS_FOLDER + "banda-editar";
		}
		
		bandaModel.setId(id);
		repository.updateBanda(bandaModel);
		redirectAttributes.addFlashAttribute("messages", "Banda alterada com sucesso!"); 
		
		return "redirect:/bandas";
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		repository.deleteBanda(repository.findById(id));
		SessionSingleton.getInstance().getSession().clear();
		redirectAttributes.addFlashAttribute("messages", "Banda excluída com sucesso!");
		
		return "redirect:/bandas";
	}
	
}
