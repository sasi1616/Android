package com.example.testapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.AdapterMobileNumberBinding

class MobileNumberAdapter  : RecyclerView.Adapter<MainViewHolder>()  {

    var numberList = mutableListOf<MobileNumber>()

    fun setNumbers(movies: List<MobileNumber>) {
        this.numberList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMobileNumberBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val mobileNumber = numberList[position]
        if (ValidationUtil.validateNumber(mobileNumber).equals("Validation successfull")) {
            holder.binding.textView.text = mobileNumber.mobilenumber
           // Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }
    }

    override fun getItemCount(): Int {
        return numberList.size
    }
}

class MainViewHolder(val binding: AdapterMobileNumberBinding) : RecyclerView.ViewHolder(binding.root) {

}