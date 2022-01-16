package com.iramarfalcao.minibank

import org.springframework.http.HttpStatus
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
    fun getById(@PathVariable id: Long): ResponseEntity<Account> =
        accountRepository.findById(id).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody account: Account
    ): ResponseEntity<Account> = accountRepository.findById(id).map {
        val accountToUpdate = it.copy(
            name = account.name,
            document = account.document,
            phone = account.phone
        )
        ResponseEntity.ok(accountRepository.save(accountToUpdate))
    }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        accountRepository.findById(id).map {
            accountRepository.delete(it)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
}
