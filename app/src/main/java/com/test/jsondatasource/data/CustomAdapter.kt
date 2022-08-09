package com.test.jsondatasource.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.jsondatasource.R

class CustomAdapter(
    private var context: Context,
    private var title: ArrayList<String>,
    private var date: ArrayList<String>,
    private var amount: ArrayList<String>,
    private var statusName: ArrayList<String>,
    private var isCredit: ArrayList<Boolean>
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction_history, parent, false)
        return MyViewHolder(v)
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        holder.title.text = title[position]
        holder.date.text = date[position]
        holder.amount.text = amount[position]

        if (statusName[position] == "Failed") {
            holder.status.setTextColor(R.color.errorColor)
            holder.status.text = statusName[position]
        } else {
            holder.status.text = statusName[position]
        }

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click
            Toast.makeText(context, title[position], Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return title.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById<View>(R.id.title) as TextView
        var date: TextView = itemView.findViewById<View>(R.id.date) as TextView
        var amount: TextView = itemView.findViewById<View>(R.id.amount) as TextView
        var status: TextView = itemView.findViewById<View>(R.id.status) as TextView
    }
}