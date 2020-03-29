package team1.spring.training.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team1.spring.training.DAO.Models.UploadFile;

@Repository
public interface FileRepository extends JpaRepository<UploadFile, String> {
}
