package com.iramarfalcao.minibank.service

import com.iramarfalcao.minibank.model.Account
import com.iramarfalcao.minibank.repository.AccountRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository
) : AccountService {

    override fun create(account: Account): Account = accountRepository.save(account)

    override fun getAll(): List<Account> = accountRepository.findAll()

    override fun getById(id: Long): Optional<Account> = accountRepository.findById(id)

    override fun update(id: Long, account: Account): Optional<Account> {
        val optional = getById(id)
        if (optional.isEmpty) Optional.empty<Account>()

        return optional.map {
            val accountToUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            accountRepository.save(accountToUpdate)
        }
    }

    override fun delete(id: Long) {
        accountRepository.findById(id).map {
            accountRepository.delete(it)
        }.orElseThrow { throw RuntimeException("Id not found $id") }
    }
}
