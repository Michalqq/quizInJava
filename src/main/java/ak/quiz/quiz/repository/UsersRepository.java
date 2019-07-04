package ak.quiz.quiz.repository;

import ak.quiz.quiz.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByDate(Date date);
}
