package com.miguel.examen.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miguel.examen.models.Network;
import com.miguel.examen.models.TvShow;
import com.miguel.examen.models.User;
import com.miguel.examen.services.TvShowService;
import com.miguel.examen.services.UserService;
import com.miguel.examen.validator.UserValidator;

@Controller
public class MainController {
	private UserService userService;
	private UserValidator userValidator;
	private TvShowService tvShowService;

	public MainController(UserService userService, UserValidator userValidator, TvShowService tvShowService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.tvShowService = tvShowService;
	}

	@RequestMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}

	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "index.jsp";
		}

		// si el resultado tiene errores, retornar a la página de registro (no se
		// preocupe por las validaciones por ahora)
		// si no, guarde el usuario en la base de datos, guarde el id del usuario en el
		// objeto Session y redirija a /home
		User u = userService.registerUser(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/shows";

	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {

		// Si el usuario está autenticado, guarde su id de usuario en el objeto Session
		// sino agregue un mensaje de error y retorne a la página de inicio de sesión.

		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {

			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/shows";
		} else {
			model.addAttribute("user", new User());
			model.addAttribute("error", "Credenciales invalidas. Intente otra vez.");
			return "index.jsp";
		}

	}

	@RequestMapping("/shows")
	public String home(HttpSession session, Model model) {

		// Obtener el usuario desde session, guardarlo en el modelo y retornar a la
		// página principal

		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);

		return "shows.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidar la sesión
		// redireccionar a la página de inicio de sesión.

		session.invalidate();
		return "redirect:/";

	}

	@RequestMapping("/shows/new")
	public String NewTvShow(@ModelAttribute("tvShow") TvShow tvShow, Model model) {
		List<Network> networks = tvShowService.findAllNetwork();
		model.addAttribute("networks", networks);
		return "/newShow.jsp";
	}

	@PostMapping("/shows/new")
	public String createTvShow(@Valid @ModelAttribute("tvShow") TvShow tvShow, BindingResult result) {
		if (result.hasErrors()) {
			return "/newShow.jsp";
		} else {
			tvShowService.createTvShow(tvShow);
			return "redirect:/shows/new";
		}
	}

	@RequestMapping("/networks/new")
	public String newNetwork(@ModelAttribute("network") Network network) {
		return "newNetwork.jsp";
	}

	@PostMapping("/networks/new")

	public String createNetwork(@Valid @ModelAttribute("network") Network network, BindingResult result) {
		if (result.hasErrors()) {
			return "newNetwork.jsp";
		} else {
			tvShowService.createNetwork(network);
			return "redirect:/networks/new";
		}
	}

}