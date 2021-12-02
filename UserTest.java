//Tested by Jimmy Morea
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    
    @Test
    void testCreateUser() {
        User user = new User("firstName", "lastName", "email", "password");
        assertNotNull(user);
    }

    @Test
    void testGetFirstName() {
        User user = new User("firstName", "lastName", "email", "password");
        assertEquals("firstName",user.getFirstName());
    }

    @Test
    void testGetLastName() {
        User user = new User("firstName", "lastName", "email", "password");
        assertEquals("lastName",user.getLastName());
    }

    @Test
    void testGetEmail() {
        User user = new User("firstName", "lastName", "email", "password");
        assertEquals("email",user.getEmail());
    }

    @Test
    void testGetPassword() {
        User user = new User("firstName", "lastName", "email", "password");
        assertEquals("password",user.getPassword());
    }
    
}
