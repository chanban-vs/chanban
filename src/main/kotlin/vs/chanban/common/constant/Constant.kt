package vs.chanban.common.constant

class Constant {
    object User {
        const val MINIMUM_PASSWORD_LENGTH: Int = 2
        const val ID: String = "id"
        const val USER_ID = "userId"
        const val EMAIL: String = "email"
    }

    object TemporaryUser {
        // Seconds, 10 minutes
        const val TIME_TO_LIVE: Long = 600
        const val VERIFICATION_CODE_LENGTH: Int = 6
    }

    object Authentication {
        const val HS256_ALGORITHM: String = "HS256"
        const val AES_ALGORITHM: String = "AES"
        const val SECRET_KEY_BITS: Int = 256
        const val ISSUER: String = "Chanban"
        // Milliseconds, 2 hours
        const val EXPIRES_AFTER: Long = 7200000
        const val AUTHORIZATION: String = "Authorization"
        const val TOKEN_PREFIX: String = "Bearer "
    }
}