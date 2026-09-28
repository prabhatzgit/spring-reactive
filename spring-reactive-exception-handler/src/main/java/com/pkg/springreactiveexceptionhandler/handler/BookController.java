package com.pkg.springreactiveexceptionhandler.handler;

import com.pkg.springreactiveexceptionhandler.dao.BookRepository;
import com.pkg.springreactiveexceptionhandler.dto.Book;
import com.pkg.springreactiveexceptionhandler.exceptionhandler.BookAPIException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository){
            this.repository = repository;
    }

    @GetMapping
    public Flux<Book> getBooks() {
        return repository.getBooks();
    }

    /* If book id is not found, then throw BookAPIException*/
    @GetMapping("/{id}")//21
    public Mono<Book> getBookById(@PathVariable int id) {
        return repository.getBooks()
                .filter(book -> book.getBookId() == id)
                .next()
                .switchIfEmpty(Mono.error(new BookAPIException("Book not found with id " + id)));
    }


}