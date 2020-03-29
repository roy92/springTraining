package team1.spring.training.DAO.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.Exceptions.FileIsEmptyException;
import team1.spring.training.Exceptions.FileWithThatNameExistException;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Repository.FileRepository;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    private final String DIRECTORY_NAME = "uploads\\";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date = new Date();

    @Autowired
    private FileRepository fileRepository;

    public List<UploadFile> getAll() {
        return fileRepository.findAll();
    }

    public UploadFile getByName(String name) {
        return fileRepository.findOne(name);
    }

    public void deleteAll() {
        fileRepository.deleteAll();
    }

    public void deleteByName(String name) {
        fileRepository.delete(name);
    }

    public byte[] showFileByName(String name) throws IOException {
        String filePath = getByName(name).getLocation();
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }

    public void addFile(MultipartFile multipartFile) throws Exception {
        if (!multipartFile.isEmpty()) {

            if(! new File(DIRECTORY_NAME).exists())
                new File(DIRECTORY_NAME).mkdir();

            convertMultiToFile(multipartFile);

            String filePath = DIRECTORY_NAME + multipartFile.getOriginalFilename();
            UploadFile uploadFile = new UploadFile(getNameFromPath(multipartFile.getOriginalFilename()),
                                                                        filePath, formatter.format(date));
            checkIfFileWithThatNameExist(uploadFile);

            fileRepository.save(uploadFile);
        } else {
            throw new FileIsEmptyException();
        }
    }

    public void convertMultiToFile(MultipartFile file) {
        Path filepath = Paths.get(DIRECTORY_NAME, file.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameFromPath(String path) {
        int index = path.indexOf('.');

        return path.substring(0, index);
    }

    public void checkIfFileWithThatNameExist(UploadFile uploadFile) throws FileWithThatNameExistException {
        List<UploadFile> allFiles = getAll();

        for (UploadFile currentFile : allFiles) {
            if (currentFile.getName().equals(uploadFile.getName()))
                throw new FileWithThatNameExistException();
        }
    }
}