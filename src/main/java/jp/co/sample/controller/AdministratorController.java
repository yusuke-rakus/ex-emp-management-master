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
	
	/** 管理者ログイン画面へのルーティング */
	@RequestMapping("")
	public String toLogin() {
		return "administrator/login";
	}

	/** ログイン処理（DBのadministratorテーブルからメールアドレスとパスワードの一致を確認） */
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
		return "redirect:/employee/showList?page=0";
	}
	
	
	@ModelAttribute
	private InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/** 管理者追加画面へのルーティング */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/** 管理者の新規登録処理（入力された値に対してバリデーション処理） */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toInsert();
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administrator.setMailAddress(form.getEmailAddress());
		service.insert(administrator);
		return "redirect:/";
	}
	
	/** ログアウト処理（session情報の削除後、ログイン画面へ遷移） */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
