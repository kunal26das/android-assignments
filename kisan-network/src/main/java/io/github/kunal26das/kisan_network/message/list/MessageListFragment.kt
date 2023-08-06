package io.github.kunal26das.kisan_network.message.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.kisan_network.databinding.FragmentMessageListBinding

@AndroidEntryPoint
class MessageListFragment : Fragment() {

    private lateinit var binding: FragmentMessageListBinding
    private val viewModel by viewModels<MessageListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.messageList.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMessageList().observe(viewLifecycleOwner) {
            binding.messageList.adapter = MessageAdapter(it)
        }
    }
}