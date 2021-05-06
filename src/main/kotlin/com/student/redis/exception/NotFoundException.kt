package com.student.redis.exception

class NotFoundException(
        override var message: String
) : RuntimeException()