package com.iramarfalcao.minibank

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountRepository: AccountRepository
) {
    @PostMapping
    fun create(@RequestBody account: Account): Account = accountRepository.save(account)

    @GetMapping
    fun getAll(): List<Account> = accountRepository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long):ResponseEntity<Account> =
        accountRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())
}