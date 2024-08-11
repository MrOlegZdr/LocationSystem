package com.home.project.locationsystem.service

import com.home.project.locationsystem.entity.AccessLevel
import com.home.project.locationsystem.entity.Location
import com.home.project.locationsystem.entity.LocationAccess
import com.home.project.locationsystem.entity.User
import com.home.project.locationsystem.repository.LocationAccessRepository
import com.home.project.locationsystem.repository.LocationRepository
import com.home.project.locationsystem.repository.UserRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import java.util.Optional

@SpringBootTest
class LocationAccessServiceImplSpec extends Specification {

    @Autowired
    LocationAccessService locationAccessService

    @SpringBean
    LocationAccessRepository locationAccessRepository = Mock()

    @SpringBean
    LocationRepository locationRepository = Mock()

    @SpringBean
    UserRepository userRepository = Mock()

    def "should share location successfully"() {
        given:
        Long locationId = 1L
        Long userId = 1L
        AccessLevel accessLevel = AccessLevel.READ_ONLY
        Location location = new Location(id: locationId)
        User user = new User(id: userId)
        LocationAccess locationAccess = new LocationAccess(location: location, user: user, accessLevel: accessLevel)

        locationRepository.findById(locationId) >> Optional.of(location)
        userRepository.findById(userId) >> Optional.of(user)
        locationAccessRepository.save(_) >> locationAccess

        when:
        def result = locationAccessService.shareLocation(locationId, userId, accessLevel)

        then:
        result == locationAccess
        1 * locationRepository.findById(locationId)
        1 * userRepository.findById(userId)
        1 * locationAccessRepository.save(_)
    }

    def "should throw exception when location or user not found during shareLocation"() {
        given:
        Long locationId = 1L
        Long userId = 1L
        AccessLevel accessLevel = AccessLevel.READ_ONLY

        locationRepository.findById(locationId) >> Optional.empty()
        userRepository.findById(userId) >> Optional.empty()

        when:
        locationAccessService.shareLocation(locationId, userId, accessLevel)

        then:
        thrown(IllegalArgumentException)
        1 * locationRepository.findById(locationId)
        1 * userRepository.findById(userId)
        0 * locationAccessRepository.save(_)
    }

    def "should find location access successfully"() {
        given:
        Long locationId = 1L
        Long userId = 1L
        Location location = new Location(id: locationId)
        User user = new User(id: userId)
        LocationAccess locationAccess = new LocationAccess(location: location, user: user, accessLevel: AccessLevel.ADMIN)

        locationAccessRepository.findAll() >> [locationAccess]

        when:
        def result = locationAccessService.findLocationAccess(locationId, userId)

        then:
        result.isPresent()
        result.get() == locationAccess
    }

    def "should return empty optional when location access not found"() {
        given:
        Long locationId = 1L
        Long userId = 1L

        locationAccessRepository.findAll() >> []

        when:
        def result = locationAccessService.findLocationAccess(locationId, userId)

        then:
        !result.isPresent()
    }

    def "should update access level successfully"() {
        given:
        Long locationId = 1L
        Long userId = 1L
        AccessLevel newAccessLevel = AccessLevel.ADMIN
        Location location = new Location(id: locationId)
        User user = new User(id: userId)
        LocationAccess locationAccess = new LocationAccess(location: location, user: user, accessLevel: AccessLevel.READ_ONLY)

        locationAccessRepository.findAll() >> [locationAccess]
        locationAccessRepository.save(_) >> locationAccess

        when:
        locationAccessService.updateAccessLevel(locationId, userId, newAccessLevel)

        then:
        locationAccess.accessLevel == newAccessLevel
        1 * locationAccessRepository.save(_)
    }

    def "should throw exception when updating access level for non-existing access"() {
        given:
        Long locationId = 1L
        Long userId = 1L
        AccessLevel newAccessLevel = AccessLevel.ADMIN

        locationAccessRepository.findAll() >> []

        when:
        locationAccessService.updateAccessLevel(locationId, userId, newAccessLevel)

        then:
        thrown(IllegalArgumentException)
        0 * locationAccessRepository.save(_)
    }
}
