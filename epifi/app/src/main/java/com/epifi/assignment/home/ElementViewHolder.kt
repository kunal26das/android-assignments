package com.epifi.assignment.home

import android.graphics.Color
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import coil.load
import com.epifi.assignment.R
import com.epifi.assignment.databinding.ItemElementBinding
import com.epifi.assignment.model.Element
import com.epifi.assignment.util.OnItemClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElementViewHolder(
    private val binding: ItemElementBinding
) : ViewHolder<ItemElementBinding>(binding) {

    private val viewModel by viewModels<ElementViewModel>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var onElementClickListener: OnItemClickListener<Element>? = null

    fun bind(element: Element?): ElementViewHolder {
        if (element == null) return this
        binding.element = element
        binding.poster.load(element.poster)
        binding.bookmark.setOnClickListener {
            binding.element = element.also {
                it.isBookmarked = !it.isBookmarked
                viewModel.manageElement(element)
                if (it.isBookmarked) snackBar(element)
            }
        }
        binding.poster.setOnClickListener {
            onElementClickListener?.invoke(element)
        }
        updateBookmarkStatus(element)
        return this
    }

    private fun updateBookmarkStatus(element: Element) {
        coroutineScope.launch {
            val isBookmarked = viewModel.isElementBookmarked(element)
            binding.element = element.also {
                it.isBookmarked = isBookmarked
            }
        }
    }

    private fun snackBar(element: Element?) {
        val message = context.getString(R.string.element_bookmarked_successfully, element?.title)
        val snackBar = Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT)
        val textViewId = com.google.android.material.R.id.snackbar_text
        val textView = snackBar.view.findViewById<TextView>(textViewId)
        val font = ResourcesCompat.getFont(context, R.font.gilroy_bold)
        textView.textAlignment = TEXT_ALIGNMENT_CENTER
        snackBar.setBackgroundTint(COLOR_SNACK_BAR)
        snackBar.setTextColor(Color.WHITE)
        textView.typeface = font
        snackBar.show()
    }

    fun setOnElementClickListener(
        onElementClickListener: OnItemClickListener<Element>
    ) {
        this.onElementClickListener = onElementClickListener
    }

    companion object {
        private val COLOR_SNACK_BAR = Color.parseColor("#555555")
    }

}