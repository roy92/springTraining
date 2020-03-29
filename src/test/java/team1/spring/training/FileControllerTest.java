package team1.spring.training;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import team1.spring.training.DAO.Controllers.FileController;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Services.FileService;

import java.io.File;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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

    @Autowired
    private WebApplicationContext wContext;

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

    @Test
    public void whenPostFile_thenReturnOkResponse() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        MockMultipartFile mockFile = new MockMultipartFile("file",
                "filename.txt", "text/plain", "some xml".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wContext).build();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/file")
                .file(mockFile);
        mockMvc.perform(builder).andExpect(ok)
                .andDo(MockMvcResultHandlers.print());
    }
}