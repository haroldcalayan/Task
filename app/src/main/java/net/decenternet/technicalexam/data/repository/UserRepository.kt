package net.decenternet.technicalexam.data.repository

interface UserRepository {
    fun register(username: String, password: String)
    fun login(username: String, password: String)
}

class UserRepositoryImpl(): UserRepository {
    override fun register(username: String, password: String) {

    }

    override fun login(username: String, password: String) {
    }

}