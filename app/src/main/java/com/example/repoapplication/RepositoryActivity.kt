package com.example.repoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        val repoData = intent.getSerializableExtra("repoDataItem") as RepoItem
        findViewById<TextView>(R.id.repos_owner).text = repoData.owner.login
        findViewById<TextView>(R.id.repos_name).text = repoData.name
        findViewById<TextView>(R.id.repos_description).text = repoData.description
        findViewById<TextView>(R.id.hostLink).text = repoData.url.replace("https://api.github.com/repos/","")
        findViewById<TextView>(R.id.starCount).text = repoData.stargazers_count.toString() + if(repoData.stargazers_count == 1) " Star" else " Stars"
        findViewById<TextView>(R.id.forkCount).text = repoData.forks_count.toString() + if(repoData.forks_count == 1) " Fork" else " Forks"
        findViewById<TextView>(R.id.issueCount).text = repoData.open_issues_count.toString() + if(repoData.open_issues_count == 1) " Issue" else " Issues"
        findViewById<TextView>(R.id.watchCount).text = repoData.watchers_count.toString() + if(repoData.watchers_count == 1) " Watcher" else " Watchers"
    }
}