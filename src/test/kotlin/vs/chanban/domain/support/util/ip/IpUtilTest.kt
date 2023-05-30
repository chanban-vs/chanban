package vs.chanban.domain.support.util.ip

import vs.chanban.testdata.TestData.IpAddress.TEST_IP_ADDRESS
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest

class IpUtilTest {
    @Test
    @DisplayName("retrieve ip address")
    fun testGetClientIpAddress_WithXForwardedForHeader() {
        val request = MockHttpServletRequest()
        request.addHeader("X-Forwarded-For", TEST_IP_ADDRESS)

        val retrievedIpAddress = IpUtil.getClientIpAddress(request)

        assertEquals(TEST_IP_ADDRESS, retrievedIpAddress)
    }

}