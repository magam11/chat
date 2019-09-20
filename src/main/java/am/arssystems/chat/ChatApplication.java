package am.arssystems.chat;

import am.arssystems.chat.model.User;
import am.arssystems.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication
public class ChatApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${image.folder.group}")
    private String groupFolderPath;
    @Value("${image.folder.files}")
    private String filesFolderPath;


    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findById(1) == null) {
            userRepository.saveAll(Arrays.asList(
                    User.builder()
                            .email("poxos@gmail.com")
                            .password(passwordEncoder.encode("123456"))
                            .firstName("Poxos")
                            .lastName("Poxosyan")
                            .build(),

                    User.builder()
                            .email("petros@gmail.com")
                            .password(passwordEncoder.encode("123456"))
                            .firstName("Petros")
                            .lastName("Petrosyan")
                            .build(),

                    User.builder()
                            .email("valod@gmail.com")
                            .password(passwordEncoder.encode("123456"))
                            .firstName("Valod")
                            .lastName("Valodyan")
                            .build(),

                    User.builder()
                            .email("poxosuhi@gmail.com")
                            .password(passwordEncoder.encode("123456"))
                            .firstName("Poxosuhi")
                            .lastName("Poxosyan")
                            .build()));
        }
        File group = new File(groupFolderPath);
        File files = new File(filesFolderPath);
        if (!group.exists()) {
            group.mkdirs();
            files.mkdirs();
        }

    }
}
