package learn.mssbkt.multiplicationkt.challenge

import jakarta.persistence.*
import learn.mssbkt.multiplicationkt.user.User

/**
 * Попытка решить задачу.
 */
@Entity
class ChallengeAttempt(
    /** Пользователь */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    var user: User,
    /** Первый множитель */
    var factorA: Int,
    /** Второй множитель */
    var factorB: Int,
    /** Ответный результат пользователя */
    var resultAttempt: Int,
    /** Флаг правильности результата */
    var correct: Boolean,
    /** ID попытки */
    @Id
    @GeneratedValue
    var id: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChallengeAttempt

        if (id != other.id) return false
        if (user != other.user) return false
        if (factorA != other.factorA) return false
        if (factorB != other.factorB) return false
        if (resultAttempt != other.resultAttempt) return false
        if (correct != other.correct) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + factorA
        result = 31 * result + factorB
        result = 31 * result + resultAttempt
        result = 31 * result + correct.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChallengeAttempt(user=$user, factorA=$factorA, factorB=$factorB, resultAttempt=$resultAttempt, correct=$correct, id=$id)"
    }
}
