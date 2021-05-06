package com.student.redis.exception

class PaginationSizeLimitExceededException(
        override var message: String
) : RuntimeException()