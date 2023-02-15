package cm.chettas.university.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cm.chettas.university.databinding.UniversityItemRowBinding
import cm.chettas.university.model.data.UniversitiesResponseItem

class UniversityAdapter : RecyclerView.Adapter<UniversityAdapter.UnversitiesViewHolder>() {

    private val list = mutableListOf<UniversitiesResponseItem?>()
    private lateinit var binding: UniversityItemRowBinding

    fun addUniversityList(newList: List<UniversitiesResponseItem?>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class UnversitiesViewHolder(private val binding: UniversityItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: UniversitiesResponseItem?) {
            binding.model = model
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnversitiesViewHolder {
        binding = UniversityItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UnversitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnversitiesViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size
}