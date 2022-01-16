package com.iramarfalcao.minibank.repository

import com.iramarfalcao.minibank.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long>
