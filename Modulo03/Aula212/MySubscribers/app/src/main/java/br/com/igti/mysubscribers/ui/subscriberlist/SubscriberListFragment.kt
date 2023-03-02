package br.com.igti.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.igti.mysubscribers.R
import br.com.igti.mysubscribers.data.db.AppDatabase
import br.com.igti.mysubscribers.repository.DataBaseDataSource
import br.com.igti.mysubscribers.repository.SubscriberRepository


class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {
    private val viewModel: SubscriberListViewModel by viewModel{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDao= AppDatabase.gettInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DataBaseDataSource(subscriberDao)
                return SubscriberListViewModel(repository) as T
            }

        }

    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner){allSubscribers ->
            val direction

        }
    }
}