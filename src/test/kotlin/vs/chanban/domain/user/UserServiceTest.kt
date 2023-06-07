package vs.chanban.domain.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import vs.chanban.common.Message.User.USER_NOT_FOUND
import vs.chanban.common.constant.Constant.User.EMAIL
import vs.chanban.common.exception.ChanbanBizException
import vs.chanban.testdata.TestData.User.TEST_USER
import java.util.*

@SpringBootTest
class UserServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userService = UserService(userRepository)
    }

    @Test
    @DisplayName("get existing user by user email")
    fun getExistingUserByEmail() {
        val userEmail = TEST_USER.userEmail
        `when`(userRepository.findByUserEmail(userEmail)).thenReturn(Optional.of(TEST_USER))

        val user: User = userService.getUserByUserEmail(userEmail)

        Assertions.assertEquals(TEST_USER, user)
    }

    @Test
    @DisplayName("get non-existing user by user email")
    fun getNonExistingUserByEmail() {
        val userEmail = TEST_USER.userEmail
        `when`(userRepository.findByUserEmail(userEmail)).thenReturn(Optional.empty())

        val exception = Assertions.assertThrows(ChanbanBizException::class.java) {
            userService.getUserByUserEmail(userEmail)
        }

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.httpStatus)
        Assertions.assertEquals(USER_NOT_FOUND.format(EMAIL, userEmail), exception.message)
    }

    @Test
    @DisplayName("get existence by existing user email")
    fun getExistingEmailExistence() {
        `when`(userRepository.existsByUserEmail(TEST_USER.userEmail)).thenReturn(true)

        val exists = userService.existsByUserEmail(TEST_USER.userEmail)

        Assertions.assertEquals(true, exists)
    }

    @Test
    @DisplayName("get existence by non-existing user email")
    fun getNonExistingEmailExistence() {
        `when`(userRepository.existsByUserEmail(TEST_USER.userEmail)).thenReturn(false)

        val exists = userService.existsByUserEmail(TEST_USER.userEmail)

        Assertions.assertEquals(false, exists)
    }
}