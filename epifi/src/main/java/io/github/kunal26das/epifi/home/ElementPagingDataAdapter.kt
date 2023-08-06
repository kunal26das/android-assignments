package io.github.kunal26das.epifi.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.kunal26das.epifi.databinding.ItemElementBinding
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.util.OnItemClickListener

class ElementPagingDataAdapter :
    PagingDataAdapter<Element, ElementViewHolder>(Element.DIffCallback()) {

    private var onElementClickListener: OnItemClickListener<Element>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemElementBinding.inflate(layoutInflater, parent, false)
        return ElementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = getItem(position)
        holder.bind(element).setOnElementClickListener {
            onElementClickListener?.invoke(element)
        }
    }

    fun setOnElementClickListener(
        onElementClickListener: OnItemClickListener<Element>
    ) {
        this.onElementClickListener = onElementClickListener
    }
}