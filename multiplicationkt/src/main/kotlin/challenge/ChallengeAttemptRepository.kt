package learn.mssbkt.multiplicationkt.challenge

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ChallengeAttemptRepository : CrudRepository<ChallengeAttempt, Long> {
    fun findTop10ByUserAliasOrderByIdDesc(userAlias: String): List<ChallengeAttempt>

    @Query("SELECT a FROM ChallengeAttempt a WHERE a.user.alias = :userAlias ORDER BY a.id DESC")
    fun lastAttempts(@Param("userAlias") userAlias: String): List<ChallengeAttempt>
}
