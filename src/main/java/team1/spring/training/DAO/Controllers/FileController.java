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
    private FileService fileService;

    @GetMapping("/all")
    public List<UploadFile> getAllFiles() {
        return fileService.getAll();
    }

    @GetMapping("/{name}")
    public UploadFile getByName(@PathVariable String name) {
        return fileService.getByName(name);
    }

    /**
     * This method returns the file content
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/show/{name}", produces = {MediaType.ALL_VALUE,
                                        MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] showByName(@PathVariable String name) throws IOException {
        return fileService.showFileByName(name);
    }

    @PostMapping("")
    public void uploadFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
        fileService.addFile(file);
    }

    @PostMapping("/delete")
    public void deleteAll() {
        fileService.deleteAll();
    }

    @PostMapping("/delete/{name}")
    public void deleteByName(@PathVariable String name) {
        fileService.deleteByName(name);
    }
}