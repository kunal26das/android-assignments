package io.github.kunal26das.epifi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.model.Element.Companion.KEY_ELEMENT

@AndroidEntryPoint
class DetailInfoBSDFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DetailInfoBindingImpl
    private val viewModel by viewModels<DetailInfoViewModel>()
    private val element by lazy {
        requireArguments().getParcelable(
            KEY_ELEMENT,
            Element::class.java
        )
    }

    init {
        arguments = Bundle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailInfoBindingImpl(inflater, container)
        binding.bind(element, viewLifecycleOwner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.element.observe(viewLifecycleOwner) {
            binding.bind(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.setIsLoading(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
        binding.setOnCloseClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getElement(element)
    }

    fun setElement(element: Element?): DetailInfoBSDFragment {
        requireArguments().putParcelable(KEY_ELEMENT, element)
        return this
    }

}