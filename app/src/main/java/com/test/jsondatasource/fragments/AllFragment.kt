package com.test.jsondatasource.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.jsondatasource.R
import com.test.jsondatasource.Util
import com.test.jsondatasource.data.CustomAdapter
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset



class AllFragment : Fragment() {

    private var adapter: RecyclerView.Adapter<CustomAdapter.MyViewHolder>? = null

    var title: ArrayList<String> = ArrayList()
    var date: ArrayList<String> = ArrayList()
    var amount: ArrayList<String> = ArrayList()
    var statusName: ArrayList<String> = ArrayList()
    var isCredit: ArrayList<Boolean> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            val obj = JSONArray(Util().loadJSONFromAsset(requireContext()))
            Log.d("Check json", obj.toString())
            Log.d("Check json", obj.length().toString())

            for (i in 0 until obj.length()) {
                val userDetail = obj.getJSONObject(i)
                title.add(userDetail.getString("transactionTypeName"))
                date.add(userDetail.getString("transactionDate"))
                amount.add(userDetail.getString("amount"))
                statusName.add(userDetail.getString("statusName"))
                isCredit.add(userDetail.getBoolean("isCredit"))
            }
            Log.d("AllFragment", (title + "" + date + " " + amount + " " + statusName + " " + isCredit).toString())
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        //recycler
        val recyclerView: RecyclerView = view.findViewById(R.id.transaction_history_rv)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        // define an adapter
        adapter = CustomAdapter(requireContext(), title, date, amount, statusName, isCredit)
        recyclerView.adapter = adapter
    }

}