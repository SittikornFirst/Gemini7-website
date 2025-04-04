package th.ac.mahidol.ict.gemini_backend.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import th.ac.mahidol.ict.gemini_backend.entity.User;
import th.ac.mahidol.ict.gemini_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;

@Component

public class DataInitializer{

    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public void run(String... args) throws Exception {
//        createTestUsers();
//        System.out.println("✅ createTestUsers() executed.");
//    }


    @PostConstruct
    public void initializeData() {
        // Only add test data if the repository is empty
        if (userRepository.count() == 0) {
            createTestUsers();
        }
    }



    private void createTestUsers() {
        // Create an Astronomer user
        User astronomer = new User();
        astronomer.setUserId("astro1");
        astronomer.setName("Jane Smith");
        astronomer.setEmail("jane@example.com");
        astronomer.setPassword("password123");
        astronomer.setRole("Astronomer");
        astronomer.setExperienceYears(5);
        astronomer.setDataAccessLevel("Medium");
        userRepository.save(astronomer);

        // Create a Science Observer user
        User observer = new User();
        observer.setUserId("observer1");
        observer.setName("John Doe");
        observer.setEmail("john@example.com");
        observer.setPassword("password123");
        observer.setRole("Science Observer");
        observer.setExperienceYears(3);
        observer.setDataAccessLevel("Low");
        userRepository.save(observer);

        // Create a System Admin user
        User admin = new User();
        admin.setUserId("admin1");
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setRole("System Admin");
        admin.setExperienceYears(10);
        admin.setDataAccessLevel("High");
        userRepository.save(admin);

        System.out.println("Test users created successfully!");
    }
}