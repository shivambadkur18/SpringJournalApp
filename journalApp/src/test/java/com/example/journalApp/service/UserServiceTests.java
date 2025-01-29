package com.example.journalApp.service;
import com.example.journalApp.entity.User;
import com.example.journalApp.repo.UserRepo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private UserService userService ;

   /* @Test
    public void testFindByUserName(){
        assertNotNull(userRepo.findByUserName("rAm"));
    }*/

    @ParameterizedTest
    @ArgumentsSource(UserArguementsProvider.class)
    public void saveUser(User user){
        assertTrue(userService.createUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "5,4,9"
    })
    public void testFindByUserName(int a , int b , int expected ){
        assertEquals((expected), a+b , "failed for ==> " + (a + " "+ b+ " " +   expected)) ;
    }
}
