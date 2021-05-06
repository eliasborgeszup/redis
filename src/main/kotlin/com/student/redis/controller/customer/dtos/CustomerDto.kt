package com.student.redis.controller.customer.dtos

import com.student.redis.entity.Customer
import java.time.LocalDate

data class CustomerDto(
        val id: String,
        val name: String,
        val birthDate: LocalDate,
        val cpf: String,
        val email: String,
        val phone: String,
        val address: String
) {
    companion object {
        fun fromCustomer(customer: Customer): CustomerDto {
            return CustomerDto(
                    customer.id,
                    customer.name,
                    customer.birthDate,
                    customer.cpf,
                    customer.email,
                    customer.phone,
                    customer.address
            )
        }
    }
}