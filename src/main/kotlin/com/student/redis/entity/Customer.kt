package com.student.redis.entity

import com.student.redis.controller.customer.dtos.CreateCustomerDto
import com.student.redis.repository.CustomerRepository
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Customer(
        @Id
        @Column(updatable = false, unique = true, nullable = false)
        val id: String,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val birthDate: LocalDate,

        @Column(nullable = false, unique = true, length = 11)
        val cpf: String,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false, length = 11)
        val phone: String,

        @Column(nullable = false)
        val address: String,
) {
    companion object {
        fun create(dto: CreateCustomerDto, repository: CustomerRepository): String {
            return repository.save(
                    Customer(UUID.randomUUID().toString(),
                            dto.name,
                            dto.birthDate,
                            dto.cpf,
                            dto.email,
                            dto.phone,
                            dto.address
                    )).id
        }

        fun update(customer: Customer, repository: CustomerRepository): String {
            return repository.save(customer).id
        }
    }

    fun delete(customer: Customer, repository: CustomerRepository) {
        repository.delete(customer)
    }
}