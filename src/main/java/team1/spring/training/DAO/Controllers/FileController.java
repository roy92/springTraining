package team1.spring.training.DAO.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Services.FileService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/all")
    public List<UploadFile> getAllFiles() {
        return fileService.getAll();
    }

    @GetMapping("/{id}")
    public UploadFile getById(@PathVariable Long id) {
        return fileService.getById(id);
    }

    /**
     * this method return the file content
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/show/{id}", produces = {MediaType.ALL_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] showById(@PathVariable Long id) throws IOException {
        return fileService.showFileById(id);
    }

    @PostMapping("")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        fileService.addFile(file);
    }

    @PostMapping("/delete")
    public void deleteAll() {
        fileService.deleteAll();
    }

    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        fileService.deleteById(id);
    }
}