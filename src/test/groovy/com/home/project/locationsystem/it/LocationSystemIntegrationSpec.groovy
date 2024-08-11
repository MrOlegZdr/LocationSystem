package com.home.project.locationsystem.it

import com.home.project.locationsystem.entity.User
import com.home.project.locationsystem.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class LocationSystemIntegrationSpec extends Specification {

    @Autowired
    UserRepository userRepository

    def "should register and retrieve a user"() {
        given:
        String name = "John Doe"
        String email = "john.doe@example.com"

        when:
        User user = new User(name: name, email: email)
        userRepository.save(user)

        then:
        User foundUser = userRepository.findByEmail(email)
        foundUser != null
        foundUser.name == name
    }
}