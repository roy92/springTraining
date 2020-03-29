package team1.spring.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Repository.FileRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileRepositoryTest {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date = new Date();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FileRepository fileRepository;

    @Test
    public void whenFindByName_thenReturnFile() {
        // given
        UploadFile file = new UploadFile("test", "testFile.txt", formatter.format(date));
        entityManager.persist(file);
        entityManager.flush();

        // when
        UploadFile foundFile = fileRepository.findOne(file.getName());

        // then
        assertEquals(foundFile.getName(), file.getName());
    }

    @Test (expected = NullPointerException.class)
    public void whenFindByNameAfterDelete_thenReturnException() {
        // given
        UploadFile file = new UploadFile("test", "testFile.txt", formatter.format(date));
        entityManager.persist(file);
        entityManager.remove(file);
        entityManager.flush();

        // when
        UploadFile foundFile = fileRepository.findOne(file.getName());

        // then
        assertEquals(foundFile.getName(), file.getName());
    }
}