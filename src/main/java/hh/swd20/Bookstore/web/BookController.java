package hh.swd20.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository rrepository;
	
	 @RequestMapping(value={"/", "/index"})
		public String indexSecure() {
			return "index";
		}  
	    
	    
	    @RequestMapping(value="/login")
		public String login() {
			return "login";
		}    
	
	
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> BookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	
    @RequestMapping(value="/Books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long BookId) {	
    	return repository.findById(BookId);
    }      
    
    @RequestMapping(value="/Books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book Book) {	
    	return repository.save(Book);
    }
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String Books(Model model) {
		model.addAttribute("categories", rrepository.findAll());
		model.addAttribute("books", repository.findAll());
		return "booklist";

	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", rrepository.findAll());
		return "addbook";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";

	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletebook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
}
	
	@RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
	public String editbook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
        return "editbook";
	
}
	
}

