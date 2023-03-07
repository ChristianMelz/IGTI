package br.com.igti.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.igti.mysubscribers.R
import br.com.igti.mysubscribers.data.db.AppDatabase
import br.com.igti.mysubscribers.data.db.dao.SubscriberDAO
import br.com.igti.mysubscribers.extension.hideKeyboard
import br.com.igti.mysubscribers.repository.DataBaseDataSource
import br.com.igti.mysubscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar

class SubscriberFragment: Fragment(R.layout.subscriber_fragment) {

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriveDAO: SubscriberDAO =
                    AppDatabase.gettInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DataBaseDataSource(subscriveDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }
    private val args: SubscriberFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.subscriber?.let{ subscriber ->
            button_subscriber.text = getString(R.string.subscriber_button_update)
            input_name.setText(subscriber.name)
            input_email.setText(subscriber.email)

            button_delete.visibility = View.VISIBLE
        }
        observeEvents()
        setListners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner){subscriberState ->
            when(subscriberState){
                is SubscriberViewModel.SubscriberState.Inserted,
                is SubscriberViewModel.SubscriberState.Updated,
                is SubscriberViewModel.SubscriberState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.mensageStateEventData.observe((viewLifecycleOwner)){ stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()

        }
    }

    private fun clearFields() {
        input_name.text?.clear
        input_email.text?.clear
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListners() {
       button_subscriber.setOnClickListener{
           val name = input_name.text.toString()
           val email = input_name.text.toString()
           viewModel.addOrUpdateSubscriber(name, email, args.subscriber?.id?:0)
       }
       button_subscriber.setOnClickListener{
           viewModel.removeSubscriber(args.subscriber?.id:0)
       }
val teste: Nu
    }

}