package com.example.rapidrescue.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rapidrescue.databinding.EachItemBinding

class AddAdapter(private val list:MutableList<AddDataModel>)
    :RecyclerView.Adapter<AddAdapter.AddViewHolder>() {

    private var listener:AddAdapter.AddAdapterClicksInterface?=null
    fun setListener(listener:AddAdapter.AddAdapterClicksInterface){
        this.listener=listener
    }
    inner class AddViewHolder(val binding:EachItemBinding)
            :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddViewHolder {
        val binding=EachItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.registeredName.text=this.name
                binding.registeredNumber.text=this.phoneNumber
                binding.deleteNumber.setOnClickListener {
                    listener?.onDeleteNumberBtnClicked(this)
                }

                binding.editNumber.setOnClickListener {
                    listener?.onEditNumberBtnClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface AddAdapterClicksInterface{
        fun onDeleteNumberBtnClicked(addNumberData: AddDataModel)
        fun onEditNumberBtnClicked(addNumberData: AddDataModel)
    }

}