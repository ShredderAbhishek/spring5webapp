package guru.springframework.spring5webapp.bookstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner
{
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
        System.out.println("running in bootstrap");

        Publisher publisher = new Publisher();
        publisher.setAddressLine1("HSR bangalore");
        publisher.setCity("Bangalore");
        publisher.setName("S Chand");

        publisherRepository.save(publisher);

        System.out.println("publisher count : " + publisherRepository.count());

        Author eric = new Author("Eric","Evan");
        Book ddd = new Book("domain driven design", "12312345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        ddd.setPublisher(publisher);
        Author rod = new Author("rod","johnson");
        Book noEJB = new Book("J2EE development without EJB", "1234512");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("No of books : "+ bookRepository.count());
        System.out.println("publisher no of books " + publisherRepository.count());
    }
}
