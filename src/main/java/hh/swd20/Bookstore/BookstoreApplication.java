package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository rrepository) {
		return (args) -> {
			log.info("save a couple of categories");
			Category category1 = new Category("Fantasy");
			rrepository.save(category1);
			Category category2 = new Category("Romance");
			rrepository.save(category2);
			Category category3 = new Category("Drama");
			rrepository.save(category3);
			
			log.info("save a couple of books");
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9780679783268", 12.99, category2));
            repository.save(new Book("No Longer Human", "Osamu Dazai", 1973, "9780811204811", 12.99, category3));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("fetch all categories");
			for (Category category : rrepository.findAll()) {
				log.info(category.toString());
			}

		};
	}
}