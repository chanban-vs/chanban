package vs.chanban.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "pagination")
class PaginationConfig {
    var defaultPage: Int = 0
    var defaultPageSize: Int = 10
}