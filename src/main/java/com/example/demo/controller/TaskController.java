package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	private final CategoryRepository categoryRepository;
	private final TaskRepository taskRepository;
	private final Account account;

	public TaskController(CategoryRepository categoryRepository, TaskRepository taskRepository, Account account) {

		this.categoryRepository = categoryRepository;
		this.taskRepository = taskRepository;
		this.account = account;
	}

	@GetMapping("/tasks")
	public String index(
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String sort,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingdate,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// 商品一覧情報の取得
		List<Task> taskList;

		if (title.length() > 0 && "closingdateAsc".equals(sort)) {

			taskList = taskRepository
					.findByTitleContainingOrderByClosingdateAsc(title);

		} else if (title.length() > 0) {

			taskList = taskRepository.findByTitleContaining(title);

		} else if ("closingdateAsc".equals(sort)) {

			taskList = taskRepository.findAllByOrderByClosingdateAsc();

		} else if (categoryId != null) {

			taskList = taskRepository.findByCategoryId(categoryId);

		} else {

			taskList = taskRepository.findAll();
		}
		model.addAttribute("title", title);

		model.addAttribute("tasks", taskList);

		return "tasks";

	}

	@GetMapping("/tasks/new")
	public String create(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		return "addTasks";
	}

	@PostMapping("/tasks/add")
	public String store(@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingdate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			Model model) {
		List<String> errorList = new ArrayList<>();
		if (title.length() == 0) {
			errorList.add("タイトルは必須です");
		}
		if (closingdate == null) {
			errorList.add("期限は必須です");
		}
		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("title", title);
			model.addAttribute("title", closingdate);
			List<Category> categoryList = categoryRepository.findAll();
			model.addAttribute("categories", categoryList);
			return "addTasks";
		}

		Category category = categoryRepository.findById(categoryId).get();
		Task task = new Task(account.getId(), category, title, closingdate, progress, memo);
		// itemsテーブルへの反映（INSERT）
		taskRepository.save(task);
		// 「/items」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}

	// 更新画面表示
	@GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable Integer id, Model model) {

		// itemsテーブルをID（主キー）で検索
		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);
		return "editTasks";
	}

	// 更新処理
	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable Integer id,
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingdate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			Model model) {

		// Itemオブジェクトの生成
		Category category = categoryRepository.findById(categoryId).get();
		Task task = taskRepository.findById(id).get();

		task.setCategory(category);
		task.setTitle(title);
		task.setClosingdate(closingdate);
		task.setProgress(progress);
		task.setMemo(memo);

		// itemsテーブルへの反映（UPDATE）
		taskRepository.save(task);
		// 「/items」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}

	@PostMapping("/tasks/{id}/delete")
	public String delete(@PathVariable Integer id) {

		// itemsテーブルから削除（DELETE）
		taskRepository.deleteById(id);
		// 「/items」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}

	@GetMapping("/categories/add")
	public String create1() {
		return "addcategory";
	}

	@PostMapping("categories/add")
	public String store(

			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String categoryName) {

		// Itemオブジェクトの生成
		Category category = new Category(categoryId, categoryName);
		// itemsテーブルへの反映（INSERT）
		categoryRepository.save(category);
		// 「/items」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}

}
