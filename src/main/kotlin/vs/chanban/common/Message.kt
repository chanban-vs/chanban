package vs.chanban.common

class Message {
    object Validation {
        const val CANNOT_BE_BLANK = "cannot be blank"
        const val INVALID_ENUM_VALUE = "Invalid %s: %s"
    }

    object Topic {
        const val TOPIC_NOT_FOUND = "Topic with Id %d not found"
    }

    object User {
        const val USER_NOT_FOUND = "User with %s %s not found"
        const val WRONG_PASSWORD = "Wrong password"
        const val TEMPORARY_USER_NOT_FOUND = "Temporary user with email %s not found"
        const val WRONG_VERIFICATION_CODE = "Wrong verification code"
        const val DUPLICATED_EMAIL = "Duplicated email %s"
        const val INVALID_PASSWORD_BY_LENGTH = "Password should be at least %d characters long"
    }
    object Authentication {
        const val UNAUTHORIZED = "Unauthorized"
        const val INVALID_TOKEN = "Invalid token"
    }

    object Mail {
        const val VERIFY_SUBJECT = "Chanban email verification code"
    }

}