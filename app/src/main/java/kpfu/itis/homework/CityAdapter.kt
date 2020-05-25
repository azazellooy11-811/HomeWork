package kpfu.itis.homework

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(
    var list: MutableList<WeatherResponse>,
    private val clickLambda: (Int) -> Unit) : RecyclerView.Adapter<townViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): townViewHolder =
        townViewHolder.create(parent, clickLambda)

    override fun getItemCount() = list.size



    fun update(newList: MutableList<WeatherResponse>) {
        val callback = CityItemDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(newList)
    }

    override fun onBindViewHolder(holder: townViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as Bundle
            holder.updateFromBundle(bundle)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: townViewHolder, position: Int) {
        holder.bind(list[position])
    }

}
