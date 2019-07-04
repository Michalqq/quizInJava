package ak.quiz.quiz;

import ak.quiz.quiz.model.UserEntity;
import ak.quiz.quiz.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void whenFindByDate_thenArticles1And2Returned() throws ParseException {
        List<UserEntity> result = usersRepository.findAllByDate(
                new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01"));

        assertEquals(2, result.size());
        assertTrue(result.stream()
                .map(UserEntity::getId)
                .allMatch(id -> Arrays.asList(1, 2).contains(id)));
    }

}