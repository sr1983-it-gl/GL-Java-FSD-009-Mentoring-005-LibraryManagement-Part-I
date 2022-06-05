package com.greatlearning.javafsd.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.javafsd.library.entity.Book;
import com.greatlearning.javafsd.library.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// add mapping for "/list"
	@RequestMapping("/list")
	public String handleBooksListing(Model theModel) {

		// get Books from db
		List<Book> theBooks = bookService.findAll();

		// add to the spring model
		theModel.addAttribute("Books", theBooks);

		return "book-lister";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Book theBook = new Book();

		theModel.addAttribute("Book", theBook);

		return "Book-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {

		// get the Book from the service
		Book theBook = bookService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Book", theBook);

		// send over to our form
		return "Book-form";
	}

	@PostMapping("/save")
	public String handleSaveBook(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("author") String author) {

		System.out.println(id);
		Book theBook;
		if (id != 0) {
			theBook = bookService.findById(id);
			theBook.setName(name);
			theBook.setCategory(category);
			theBook.setAuthor(author);
		} else
			theBook = new Book(name, category, author);
		// save the Book
		bookService.save(theBook);

		// use a redirect to prevent duplicate submissions
		return "redirect:/books/list";

	}

	@RequestMapping("/delete")
	public String handleDelete(@RequestParam("bookId") int theId) {

		// delete the Book
		bookService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/books/list";

	}

	@RequestMapping("/search")
	public String handleSearch(@RequestParam("name") String name, @RequestParam("author") String author, Model theModel) {

		// check names, if both are empty then just give list of all Books

		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:/books/list";
		} else {
			// else, search by first name and last name
			List<Book> theBooks = bookService.searchBy(name, author);

			// add to the spring model
			theModel.addAttribute("Books", theBooks);

			// send to list-Books
			return "book-lister";
		}

	}
}
