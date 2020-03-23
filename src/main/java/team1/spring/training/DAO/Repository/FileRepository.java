package team1.spring.training.DAO.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team1.spring.training.DAO.Models.UploadFile;

@Repository
public interface FileRepository extends CrudRepository<UploadFile, String> {
}
