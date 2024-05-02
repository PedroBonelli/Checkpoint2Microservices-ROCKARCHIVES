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

import br.com.fiap.model.IntegranteModel;
import br.com.fiap.repository.BandaRepository;
import br.com.fiap.repository.IntegranteRepository;
import br.com.fiap.repository.SessionSingleton;

@Controller
@RequestMapping("/integrantes")
public class IntegranteController {

	private static final String INTEGRANTES_FOLDER = "integrante/";
	
	@Autowired
	private IntegranteRepository repository;
	
	@Autowired
	private BandaRepository bandaRepository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page, @RequestParam(required = false) Long id, @ModelAttribute("IntegranteModel") IntegranteModel integranteModel, Model model) {
		if("integrante-editar".equals(page)) {
			model.addAttribute("integranteModel", repository.findById(id));
			SessionSingleton.getInstance().getSession().clear();
		}
		
		model.addAttribute("bandas", bandaRepository.findAll());
		
		return INTEGRANTES_FOLDER + page;
	}
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("integrantes", repository.findAll());
		return INTEGRANTES_FOLDER + "integrantes";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("integrante", repository.findById(id));
		return INTEGRANTES_FOLDER + "integrante-detalhe";
	}
	
	@PostMapping()
	public String save(@Valid IntegranteModel integranteModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("bandas", bandaRepository.findAll());
			return INTEGRANTES_FOLDER + "integrante-novo";
		}
		
		repository.saveIntegrante(integranteModel);
		redirectAttributes.addFlashAttribute("messages", "Integrante cadastrado com sucesso!");
		
		return "redirect:/integrantes";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid IntegranteModel integranteModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return INTEGRANTES_FOLDER + "integrante-editar";
		}
		
		integranteModel.setId(id);
		repository.updateIntegrante(integranteModel);
		redirectAttributes.addFlashAttribute("messages", "Integrante alterado com sucesso"); 
		
		return "redirect:/integrantes";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		repository.deleteIntegrante(repository.findById(id));
		redirectAttributes.addFlashAttribute("messages", "Integrante excluído com sucesso!");
		
		return "redirect:/integrantes";
	}
	
}
