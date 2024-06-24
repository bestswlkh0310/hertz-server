package com.bestswlkh0310.hertz

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.DefaultTransactionDefinition

@SpringBootTest
@Transactional
class HertzApplicationTests {
    @Autowired
    private lateinit var transactionManager: PlatformTransactionManager
    private lateinit var status: TransactionStatus

    @BeforeEach
    fun beforeEach() {
        status = transactionManager.getTransaction(DefaultTransactionDefinition())
    }

    @AfterEach
    fun afterEach() {
        transactionManager.rollback(status)
    }
}
