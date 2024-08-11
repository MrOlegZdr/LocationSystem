package com.home.project.locationsystem.service

import com.home.project.locationsystem.entity.Location
import com.home.project.locationsystem.entity.User
import com.home.project.locationsystem.repository.LocationRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LocationServiceSpec extends Specification {

    @Autowired
    LocationService locationService

    @SpringBean
    LocationRepository locationRepository = Mock()

    def "should create a new location for a user"() {
        given:
        User user = new User(name: "John Doe", email: "john.doe@example.com")
        Location location = new Location(locationName: "Home", address: "123 Main St", owner: user)

        when:
        locationService.createLocation(location)

        then:
        1 * locationRepository.save(location)
    }

    def "should not create a location with missing fields"() {
        given:
        User user = new User(name: "John Doe", email: "john.doe@example.com")
        Location location = new Location(locationName: "", address: "", owner: user)

        when:
        locationService.createLocation(location)

        then:
        thrown(IllegalArgumentException)
    }
}