package team1.spring.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import team1.spring.training.DAO.Controllers.FileController;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Services.FileService;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileService service;

    @Test
    public void whenGetAllFiles_thenReturnJsonArray() throws Exception {

        UploadFile file = new UploadFile("test","location", "time");

        List<UploadFile> allFiles = Arrays.asList(file);

        given(service.getAll()).willReturn(allFiles);

        mvc.perform(get("/file/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(file.getName())));
    }
}
