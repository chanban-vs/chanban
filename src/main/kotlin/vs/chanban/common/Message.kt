package vs.chanban.common

class Message {
    object Validation {
        const val CANNOT_BE_BLANK = "cannot be blank"
        const val CANNOT_BE_NULL = "cannot be null"
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
        const val INVALID_PASSWORD_BY_MINIMUM_LENGTH = "Password should be at least %d characters long"
        const val INVALID_PASSWORD_BY_MAXIMUM_LENGTH = "Password should not exceed %d characters long"
    }

    object Poll {
        const val POLL_NOT_FOUND = "Poll not found"
    }

    object Comment {
        const val COMMENT_NOT_FOUND = "Comment with %s %s not found"
        const val INVALID_PARENT_COMMENT_BY_PARENT_COMMENT = "Parent comment should not have parent comment"
    }

    object Bookmark {
        const val BOOKMARK_NOT_FOUND = "Bookmark with %s %s not found"
    }

    object Authentication {
        const val UNAUTHORIZED = "Unauthorized"
        const val INVALID_TOKEN = "Invalid token"
        const val EMPTY_TOKEN = "Token is empty"
        const val FORBIDDEN = "Forbidden"
    }

    object Mail {
        const val VERIFY_SUBJECT = "Chanban email verification code"
    }
}