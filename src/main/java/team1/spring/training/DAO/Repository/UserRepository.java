package team1.spring.training.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team1.spring.training.DAO.Models.UploadFile;
import team1.spring.training.DAO.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}