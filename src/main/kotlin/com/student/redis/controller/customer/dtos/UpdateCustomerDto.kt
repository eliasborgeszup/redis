package com.student.redis.controller.customer.dtos

import java.time.LocalDate
import javax.validation.constraints.*

class UpdateCustomerDto(
        @field: NotBlank(message = "{validation.blank}")
        val name: String,

        @field: Past(message = "{validation.invalid.date}")
        val birthDate: LocalDate,

        @field: Email(message = "{validation.invalid.email}")
        @field: NotBlank(message = "{validation.blank}")
        val email: String,

        @field: Pattern(regexp = "^[0-9]*", message = "{validation.phone}")
        @field: Size(min = 10, max = 11, message = "{validation.size}")
        val phone: String,

        @field: NotBlank(message = "{validation.blank}")
        val address: String
)