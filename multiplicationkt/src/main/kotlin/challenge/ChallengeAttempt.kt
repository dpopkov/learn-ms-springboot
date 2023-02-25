package learn.mssbkt.multiplicationkt.challenge

import learn.mssbkt.multiplicationkt.user.User

/**
 * Попытка решить задачу.
 */
data class ChallengeAttempt(
    /** ID попытки */
    val id: Long,
    /** Пользователь */
    val user: User,
    /** Первый множитель */
    val factorA: Int,
    /** Второй множитель */
    val factorB: Int,
    /** Ответный результат пользователя */
    val resultAttempt: Int,
    /** Флаг правильности результата */
    val correct: Boolean,
)
