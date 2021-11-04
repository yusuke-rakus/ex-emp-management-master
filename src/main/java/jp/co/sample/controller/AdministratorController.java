package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * Administratorページのコントローラー
 * 
 * @author yusukematsumoto
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession session;

	@Autowired
	private AdministratorService service;

	@ModelAttribute
	LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@RequestMapping("")
	public String toLogin() {
		return "administrator/login";
	}

	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = new Administrator();
		administrator = service.findByMailAddressAndPassword(form.getEmailAddress(), form.getPassword());
		if(administrator == null) {
			String text = "メールアドレスまたはパスワードが間違っています";
			model.addAttribute("errorMessage", text);
			return toLogin();
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	}

	@ModelAttribute
	private InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("nameError", "氏名を入力してください");
			model.addAttribute("mailAddressError", "メールアドレスを正しく入力してください");
			model.addAttribute("passwordError", "8文字以上20文字以内で入力してください");
			return toInsert();
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administrator.setMailAddress(form.getEmailAddress());
		service.insert(administrator);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
