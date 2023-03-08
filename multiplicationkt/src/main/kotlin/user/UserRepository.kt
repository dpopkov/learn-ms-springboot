package learn.mssbkt.multiplicationkt.user

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UserRepository : CrudRepository<User, Long> {
    fun findByAlias(alias: String): Optional<User>
}
