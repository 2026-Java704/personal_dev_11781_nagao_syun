package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {
	private final UserRepository userRepository;
	private final HttpSession session;
	private final Account account;

	public UserController(UserRepository userRepository, HttpSession session, Account account) {

		this.userRepository = userRepository;
		this.session = session;
		this.account = account;
	}

	@GetMapping({ "/", "/login", "logout" })
	public String index() {
		session.invalidate();
		return "login";
	}

	@GetMapping("/users/new")
	public String create() {
		return "userForm";
	}

	@PostMapping("/users/add")
	public String add(
			@RequestParam String email,
			@RequestParam String name,
			@RequestParam String password,
			Model model) {
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		} else {
			// メールアドレス存在チェック
			List<User> userList = userRepository.findByEmail(email);
			if (userList != null && userList.size() > 0) {
				// 登録済みのメールアドレスが存在した場合
				errorList.add("登録済みのメールアドレスです");
			}
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("email", email);

			return "userForm";
		}

		User user = new User(email, name, password);

		model.addAttribute("user", user);

		userRepository.save(user);

		return "redirect:/login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String checkpassword,
			Model model) {
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "メールアドレスとパスワードを入力してください");
			return "login";
		}
		if (!password.equals(checkpassword)) {
			model.addAttribute("message", "パスワードが一致しません");
			return "login";
		}
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList == null || userList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		User user = userList.get(0);

		// セッション管理されたアカウント情報にIDと名前をセット
		account.setName(user.getName());
		account.setId(user.getId());

		// 「/items」へのリダイレクト
		return "redirect:/tasks";
	}
}
