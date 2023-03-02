package br.com.igti.mysubscribers.repository

import androidx.lifecycle.LiveData
import br.com.igti.mysubscribers.data.db.dao.SubscriberDAO
import br.com.igti.mysubscribers.data.db.entity.SubscriberEntity


class DataBaseDataSource(private val subscribeDAO: SubscriberDAO): SubscriberRepository {

    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )
        return subscribeDAO.insert(subscriber)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        return subscribeDAO.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscribeDAO.delete(id)
    }

    override suspend fun deleteAllSubscriber() {
        subscribeDAO.deleteAll()
    }

    override suspend fun getAllSubscriber(): LiveData<List<SubscriberEntity>> {
        return subscribeDAO.getAll()
    }
}