package com.student.redis.exception

class DocumentAlreadyExistsException(
        override var message: String
) : RuntimeException()