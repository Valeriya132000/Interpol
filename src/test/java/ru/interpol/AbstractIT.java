package ru.interpol;

import com.radcortez.flyway.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import ru.interpol.repositories.DescriptionRepository;
import ru.interpol.repositories.DeveloperRepository;
import ru.interpol.repositories.InterpolArchiveRepository;
import ru.interpol.repositories.InterpolRepository;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@FlywayTest(clean = false)
public class AbstractIT {
    @Autowired
    public TestRestTemplate testRestTemplate;
    @Autowired
    public InterpolRepository interpolRepository;
    @Autowired
    public InterpolArchiveRepository interpolArchiveRepository;
    @Autowired
    public DescriptionRepository descriptionRepository;
    @Autowired
    public DeveloperRepository developerRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:15.2-alpine"))
            .withUsername("interpol")
            .withPassword("testSecret")
            .withDatabaseName("interpol");

    @BeforeAll
    static void setup(){
        postgres.start();
    }
}
