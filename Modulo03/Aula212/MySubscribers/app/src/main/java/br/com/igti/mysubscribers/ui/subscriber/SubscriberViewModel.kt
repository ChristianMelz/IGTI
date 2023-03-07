package br.com.igti.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.igti.mysubscribers.repository.SubscriberRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.igti.mysubscribers.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select


class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {
    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _mensageEventData = MutableLiveData<Int>()
    val mensageStateEventData: LiveData<Int>
        get() = _mensageEventData

    fun addOrUpdateSubscriber(name: String, email: String, id: Long = 0) = viewModelScope.launch()
    {
        if (id > 0) {
            updateSubscriber(id, name, email)
        } else {
            insertSubscriber(name, email)
        }
    }



    private fun insertSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _mensageEventData.value = R.string.subscriber_inserted_successfully
            }
        } catch (ex: Exception) {
            _mensageEventData.value = R.string.subscriber_error_to_insert
            Log.e("Erro Insert Subscribe", ex.toString())

        }
    }

    private fun updateSubscriber(id: Long, name: String, email: String) = viewModelScope.launch {
        try {
            repository.updateSubscriber(id, name, email)
            _subscriberStateEventData.value = SubscriberState.Updated
            _mensageEventData.value = R.string.subscriber_updated_successfully
        } catch (ex: Exception) {
            _mensageEventData.value = R.string.subscriber_error_to_update
            Log.e("Erro Update Subscribe", ex.toString())
        }

    }

    private fun removeSubscriber(id: Long) = viewModelScope.launch {
        try {
            if (id >0)
            repository.deleteSubscriber(id)
            _subscriberStateEventData.value = SubscriberState.Deleted
            _mensageEventData.value = R.string.subscriber_deleted_successfully
        } catch (ex: Exception) {
            _mensageEventData.value = R.string.subscriber_error_to_delete
            Log.e("Erro Remove Subscribe", ex.toString())
        }

    }


    sealed class SubscriberState {
        object Inserted : SubscriberState()
        object Updated : SubscriberState()
        object Deleted : SubscriberState()
    }

    // companion object{
    //   private val TAG = SubscriberViewModel::class.java
    // }
}

