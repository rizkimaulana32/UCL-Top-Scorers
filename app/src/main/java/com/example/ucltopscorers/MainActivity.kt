package com.example.ucltopscorers

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ucltopscorers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvTopScore: RecyclerView
    private val list = ArrayList<TopScore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvTopScore = binding.rvTopScore
        rvTopScore.setHasFixedSize(true)
        list.addAll(getListTopScore())
        showRecyclerList()
    }

    private fun getListTopScore(): ArrayList<TopScore> {
        val topScoreimg = resources.obtainTypedArray(R.array.top_scorers_img)
        val topScoreName = resources.getStringArray(R.array.top_scorers_name)
        val topScoregoal = resources.getIntArray(R.array.top_scorers_goal)
        val topScoredesc = resources.getStringArray(R.array.top_scorers_description)
        val listTopScore = ArrayList<TopScore>()
        for (i in topScoreName.indices) {
            val topScore =
                TopScore(
                    topScoreimg.getResourceId(i, -1),
                    topScoreName[i],
                    topScoregoal[i],
                    topScoredesc[i]
                )
            listTopScore.add(topScore)
        }
        topScoreimg.recycle()
        return listTopScore
    }

    private fun showRecyclerList() {
        rvTopScore.layoutManager = LinearLayoutManager(this)
        val listTopScoreAdapter = ListTopScoreAdapter(list)
        rvTopScore.adapter = listTopScoreAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_page) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
