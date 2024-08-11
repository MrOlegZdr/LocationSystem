package com.home.project.locationsystem.service

import com.home.project.locationsystem.entity.User
import com.home.project.locationsystem.repository.UserRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService
    
    @SpringBean
    UserRepository userRepository = Mock()

    def "should register a new user successfully"() {
        given:
        User user = new User(name: "John Doe", email: "john.doe@example.com")

        when:
        userService.registerUser(user)

        then:
        1 * userRepository.save(user)
    }

    def "should not register a user with invalid email"() {
        given:
        User user = new User(name: "John Doe", email: "invalid-email")

        when:
        userService.registerUser(user)

        then:
        thrown(IllegalArgumentException)
    }
}