package com.MovieApp.data;

import com.MovieApp.movie.Movie;
import com.MovieApp.movie.MovieRepository;
import com.MovieApp.review.Review;
import com.MovieApp.review.ReviewRepository;
import com.MovieApp.role.Role;
import com.MovieApp.role.RoleRepository;
import com.MovieApp.user.User;
import com.MovieApp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
@AllArgsConstructor
public class DataService implements CommandLineRunner {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role user = new Role("USER");
        Role admin = new Role("ADMIN");

        roleRepository.saveAll(List.of(
                user,
                admin
        ));

        Movie theGentlemen = new Movie(
                "The Gentlemen",
                "An American expat tries to sell off his highly profitable marijuana empire in London, triggering plots, schemes, bribery and blackmail in an attempt to steal his domain out from under him.",
                2019,
                "Action",
                "The USA",
                "Guy Ritchie",
                "gentlemen.jpg"
        );

        Movie snatch = new Movie(
                "Snatch",
                "Unscrupulous boxing promoters, violent bookmakers, a Russian gangster, incompetent amateur robbers and supposedly Jewish jewelers fight to track down a priceless stolen diamond.",
                2000,
                "Crime",
                "The UK",
                "Guy Ritchie",
                "snatch.jpg"
        );

        Movie rockAndRolla = new Movie(
                "RockAndRolla",
                "When a Russian mobster orchestrates a crooked land deal, millions of dollars are up for grabs, drawing in the entire London underworld into a feeding frenzy at a time when the old criminal regime is losing turf to a wealthy foreign mob.",
                2008,
                "Crime",
                "The UK",
                "Guy Ritchie",
                "rockandrolla.jpg"
        );

        Movie pulpFiction = new Movie(
                "Pulp Fiction",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                1996,
                "Crime",
                "The USA",
                "Quentin Tarantino",
                "pulp_fiction.jpg"
        );

        Movie djangoUnchained = new Movie(
                "Django Unchained",
                "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.",
                2012,
                "Western",
                "The USA",
                "Quentin Tarantino",
                "django_unchained.jpg"
        );

        Movie noCountryForOldMen = new Movie(
                "No Country for Old Men",
                "Violence and mayhem ensue after a hunter stumbles upon the aftermath of a drug deal gone wrong and over two million dollars in cash near the Rio Grande.",
                2007,
                "Thriller",
                "The USA",
                "Coen brothers",
                "no_country_for_old_men.jpg"
        );

        Movie nocturnalAnimals = new Movie(
                "Nocturnal Animals",
                "A wealthy art gallery owner is haunted by her ex-husband's novel, a violent thriller she interprets as a symbolic revenge tale.A wealthy art gallery owner is haunted by her ex-husband's novel, a violent thriller she interprets as a symbolic revenge tale.",
                2016,
                "Thriller",
                "The USA",
                "Tom Ford",
                "nocturnal_animals.jpg"
        );

        Movie scarface = new Movie(
                "Scarface",
                "In 1980 Miami, a determined Cuban immigrant takes over a drug cartel and succumbs to greed.",
                1983,
                "Crime",
                "The USA",
                "Brian De Palma",
                "scarface.jpg"
        );

        Movie reservoirDogs = new Movie(
                "Reservoir Dogs",
                "When a simple jewelry heist goes horribly wrong, the surviving criminals begin to suspect that one of them is a police informant.",
                1992,
                "Crime",
                "The USA",
                "Quentin Tarantino",
                "reservoir_dogs.jpg"
        );

        Movie jumpStreet21 = new Movie(
                "21 Jump Street",
                "A pair of underachieving cops are sent back to a local high school to blend in and bring down a synthetic drug ring.",
                2012,
                "Comedy",
                "The USA",
                "Phil Lord and Christopher Miller",
                "jump_street_21.jpg"
        );

        movieRepository.saveAll(
                List.of(
                        theGentlemen,
                        snatch,
                        rockAndRolla,
                        pulpFiction,
                        djangoUnchained,
                        noCountryForOldMen,
                        nocturnalAnimals,
                        scarface,
                        reservoirDogs,
                        jumpStreet21)
        );

        User johnDoe = new User(
                "John Doe",
                "john_doe",
                passwordEncoder.encode("P@ssw0rd123"),
                "john.doe@example.com",
                List.of(user)
        );

        User janeSmith = new User(
                "Jane Smith",
                "jane.smith",
                passwordEncoder.encode("P@ssW0rd!"),
                "jane.smith@gmail.com",
                List.of(user)
        );

        User ekaterinaPetukhova = new User(
                "Ekaterina Petukhova",
                "sadkote",
                passwordEncoder.encode("Pea12122000!"),
                "katepthv12@gmail.com",
                List.of(admin)
        );

        User tomKidman = new User(
                "Tom Kidman",
                "tom123",
                passwordEncoder.encode("P!sswo1d"),
                "tomkidman123@gmail.com",
                List.of(user)
        );

        userRepository.saveAll(
                List.of(
                        johnDoe,
                        janeSmith,
                        ekaterinaPetukhova,
                        tomKidman
                )
        );

        Review theGentelmenReview = new Review(
                "Guy Ritchie’s Return to Form: A Stylish Crime Caper",
                "Guy Ritchie’s \"The Gentlemen\" marks a triumphant return to his crime caper roots, delivering a film brimming with wit, style, and engaging twists. Matthew McConaughey shines as Mickey Pearson, a suave drug lord looking to cash out of his marijuana empire. The film’s standout performance comes from Hugh Grant, who plays the sleazy private investigator Fletcher with unexpected charm and humor. While the plot can be convoluted at times, the stellar cast and sharp dialogue make for an entertaining ride. Fans of Ritchie’s early work will find much to enjoy in this slick, high-stakes thriller.",
                5,
                LocalDate.of(2024, Month.MAY, 25),
                theGentlemen,
                johnDoe
        );

        Review scarfaceReview = new Review(
                "Scarface: A Timeless Tale of Ambition and Tragedy",
                "Scarface, directed by Brian De Palma and starring Al Pacino as Tony Montana, is a gripping and iconic crime drama. The film chronicles Tony's rise from a Cuban immigrant to a powerful Miami drug lord, capturing his unrelenting ambition and eventual downfall. Pacino's intense performance, coupled with Oliver Stone's sharp screenplay, creates a compelling and tragic character study. The film's brutal violence and raw depiction of the drug trade make it a standout in the gangster genre, leaving a lasting impact on its audience.",
                4,
                LocalDate.of(2024, Month.MAY, 29),
                scarface,
                tomKidman
        );

        Review noCountryForOldManReview = new Review(
                "No Country for Old Men: A Taut Thriller",
                "In \"No Country for Old Men,\" the Coen Brothers craft a nail-biting thriller that's as intense as it is unpredictable. Set in the rugged landscapes of West Texas, the film follows a hunter who stumbles upon a fortune and unwittingly triggers a deadly game of cat and mouse with a relentless hitman. With stellar performances from Javier Bardem and Tommy Lee Jones, along with its gritty atmosphere and shocking twists, this is one ride you won't want to miss.",
                5,
                LocalDate.of(2024, Month.MAY, 31),
                noCountryForOldMen,
                janeSmith
        );

        Review jumpStreet21Review = new Review(
                "21 Jump Street: Missed Comedy Opportunities",
                "\"21 Jump Street\" misses the mark as a comedy flick. Despite its promising concept, the humor feels forced and juvenile, with Jonah Hill and Channing Tatum failing to spark genuine laughs. The plot lacks coherence, jumping between improbable scenarios without a solid foundation. Overall, it's a forgettable movie that fails to deliver on its comedic potential.",
                2,
                LocalDate.of(2024, Month.MAY, 31),
                jumpStreet21,
                janeSmith
        );

        reviewRepository.saveAll(
                List.of(
                        theGentelmenReview,
                        scarfaceReview,
                        noCountryForOldManReview,
                        jumpStreet21Review
                )
        );
    };
}
