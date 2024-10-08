package com.parthiv.rapidrescue.ui.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parthiv.rapidrescue.databinding.EachItemBinding


class AddAdapter(private val list: MutableList<AddDataModel>)
    : RecyclerView.Adapter<AddAdapter.AddViewHolder>() {

    private var listener: AddAdapterClicksInterface? = null

    fun setListener(listener: AddAdapterClicksInterface) {
        this.listener = listener
    }

    inner class AddViewHolder(itemView: View, val binding: EachItemBinding, clickListener: AddAdapterClicksInterface)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
            binding.callicon.setOnClickListener {
                clickListener.onCallIconClicked(list[adapterPosition].phoneNumber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {
        val binding = EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddViewHolder(binding.root, binding, listener!!)
    }

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.registeredName.text = this.name
                binding.registeredNumber.text = this.phoneNumber
                binding.deleteNumber.setOnClickListener {
                    listener?.onDeleteNumberBtnClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface AddAdapterClicksInterface {
        fun onDeleteNumberBtnClicked(addNumberData: AddDataModel)
        fun onEditNumberBtnClicked(addNumberData: AddDataModel)
        fun onItemClick(position: Int)
        fun onCallIconClicked(phoneNumber: String) // Add this method
    }
}
