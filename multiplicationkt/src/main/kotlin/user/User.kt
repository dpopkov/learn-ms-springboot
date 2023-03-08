package learn.mssbkt.multiplicationkt.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    var alias: String,
    @Id
    @GeneratedValue
    var id: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (alias != other.alias) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + alias.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(alias='$alias', id=$id)"
    }
}
