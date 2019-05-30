package com.efro.springit;

import com.efro.springit.domain.Comment;
import com.efro.springit.domain.Link;
import com.efro.springit.repository.CommentRepository;
import com.efro.springit.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing                  // To create Auditing data like CreatedBy and LastModifiedDate
public class SpringitApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringitApplication.class, args);
    }

    /**
     * CommandLineRunner is a @FunctionalInterface.  It will be executed right after Spring App start up.
     *
     * A CommandLineRunner is an interface that contains a single method.
     * It is because of this single method that it is marked as a Functional Interface and
     * we can therefore implement with a Lambda and can avoid creating a separate class to do so.
     *
     * Autowire in the Repositories to save initial data.
     */
    // Comment out the @Bean to stop adding new records on load.
    // @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            // 1. Create and save a Link
            Link link = new Link("Blackbaud Web Site", "https://blackbaud.com");
            linkRepository.save(link);

            // 2. Create and save a Comment
            Comment comment = new Comment("One of the best Cloud companies!!!", link);
            commentRepository.save(comment);

            // 3. Add the Comment to Link's Comments list
            link.addComment(comment);
        };
    }
}
