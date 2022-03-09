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

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository rrepository;
	
	@RequestMapping("/categorylist")
	public String categorylist(Model model) {
		model.addAttribute("categories", rrepository.findAll());
		return "categorylist";
	}
	
	@RequestMapping(value = "/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		rrepository.save(category);
		return "redirect:categorylist";

	}
	
	// RESTful service to get all Categorys
    @RequestMapping(value="/Categorys", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategorysRest() {	
        return (List<Category>) rrepository.findAll();
    }    

	// RESTful service to get Category by id
    @RequestMapping(value="/Categorys/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long dId) {	
    	return rrepository.findById(dId);
    } 
    
    // RESTful service to save new Category
    @RequestMapping(value="/Categorys", method = RequestMethod.POST)
    public @ResponseBody Category saveStudentRest(@RequestBody Category Category) {	
    	return rrepository.save(Category);
    }
}

