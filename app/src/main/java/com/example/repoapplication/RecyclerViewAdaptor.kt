package com.example.repositoryviewer

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repoapplication.R
import com.example.repoapplication.RepoItem
import com.example.repoapplication.RepositoryActivity


class RecyclerViewAdaptor(private val context: Context, private var repos_list: List<RepoItem>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.data_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repos_name.text = repos_list[position].name
        holder.repos_owner.text = repos_list[position].owner.login
        if(position != repos_list.size-1){
            holder.card_ending.height = 0
            holder.card_ending.visibility = View.INVISIBLE
        }

        holder.repos_image.setOnClickListener{
            val intent = Intent(context,RepositoryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("repoDataItem", repos_list[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return repos_list.size
    }

    fun replaceRepoList(repoList : List<RepoItem>){
        this.repos_list = repoList
        notifyDataSetChanged()
    }

}


class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val repos_name: TextView = itemView.findViewById(R.id.repos_name)
    val repos_owner: TextView = itemView.findViewById(R.id.repos_owner)
    val repos_image: ImageView = itemView.findViewById(R.id.imageView)
    val card_ending: TextView = itemView.findViewById(R.id.textView3)
}