package com.inispring.spring5webapp.bootstrap;

import com.inispring.spring5webapp.domain.Author;
import com.inispring.spring5webapp.domain.Book;
import com.inispring.spring5webapp.domain.Publisher;
import com.inispring.spring5webapp.repositories.AuthorRepository;
import com.inispring.spring5webapp.repositories.BookRepository;
import com.inispring.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("INI Publishing");
        publisher.setCity("Bengaluru");
        publisher.setZip("560002");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1231231453");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rob = new Author("Rob", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "5343532342");
        rob.getBooks().add(noEJB);
        noEJB.getAuthors().add(rob);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rob);
        bookRepository.save(noEJB);

        System.out.println("Starting bootstrap");
        System.out.println("Number of books " + bookRepository.count());
        System.out.println("Publisher Number of books " + publisher.getBooks().size());
    }
}
