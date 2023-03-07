package br.com.igti.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.igti.mysubscribers.R
import br.com.igti.mysubscribers.data.db.AppDatabase
import br.com.igti.mysubscribers.repository.DataBaseDataSource
import br.com.igti.mysubscribers.repository.SubscriberRepository


class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {
    private val viewModel: SubscriberListViewModel by viewModels{
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
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            setHasOptionsMenu(allSubscribers.size > 1)
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers) { subscriber ->
                val directions =  SubscriberListFragmentDirections
                    .actionSubscriberListFragmentToSubscriberFragment(subscriber)
                findNavController().navigateWithAnimations(directions)
            }
            with(recycler_subscribers) {
                setHasOptionsMenu(true)
                adapter = subscriberListAdapter
            }
        }

        viewModel.deleteAllSubscribersEvent.observe(viewLifecycleOwner){
            viewModel.getSubscribers()
        }
    }
    private fun configureViewListeners() {
       fabAddSubscriber.setOnClickListener{
           findNavController().navigateWithAnimations(R.id.action_subscriberListFragment_to_subscriberFragment)
       }
    }

    override fun OnCreateOptinsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate((R.menu.subscriber_menu, menu))
    }

    override fun OnOpitionsItemSelected(item: MenuItem):Boolean{
        return if(item.itemId == R.id.delete_subscribers){
            viewModel.deleteAllSubscribersEvent()
            true
        }else super.onOptionsItemSelected(item)
    }

}