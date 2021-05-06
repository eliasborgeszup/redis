package com.student.redis.controller.customer

import com.student.redis.controller.customer.config.PageSizeValidator
import com.student.redis.controller.customer.dtos.CreateCustomerDto
import com.student.redis.controller.customer.dtos.CustomerDto
import com.student.redis.controller.customer.dtos.CustomerIdDto
import com.student.redis.controller.customer.dtos.UpdateCustomerDto
import com.student.redis.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

const val SIZE_MAX_PAGE: Int = 100

@RestController
@RequestMapping("/customers")
class CustomerController(
        private var service: CustomerService
) {
    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@Valid @RequestBody dto: CreateCustomerDto) = CustomerIdDto(service.create(dto))

    @ResponseStatus(OK)
    @GetMapping
    fun getAll(
            @PageableDefault(sort = ["name"], direction = ASC, size = 20)
            page: Pageable,
    ): Page<CustomerDto>{
        PageSizeValidator.validate("CustomerController", SIZE_MAX_PAGE, page.pageSize)

        return service.getAll(page).map { CustomerDto.fromCustomer(it) }
    }

    @ResponseStatus(OK)
    @GetMapping("/{cpf}")
    fun getByCpf(@PathVariable cpf: String) = CustomerDto.fromCustomer(service.getByCpf(cpf))

    @ResponseStatus(OK)
    @PutMapping("/{cpf}")
    fun update(@PathVariable cpf: String, @Valid @RequestBody dto: UpdateCustomerDto) = CustomerIdDto(service.update(cpf, dto))

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{cpf}")
    fun delete(@PathVariable cpf: String){
        service.delete(cpf)
    }
}