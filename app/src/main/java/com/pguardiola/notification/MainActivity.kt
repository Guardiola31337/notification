package com.pguardiola.notification

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val samples = ArrayList(Arrays.asList(
                SampleItem(
                        getString(R.string.title_activity_one),
                        getString(R.string.description_activity_one),
                        ActivityOne::class.java
                ),
                SampleItem(
                        getString(R.string.title_activity_two),
                        getString(R.string.description_activity_two),
                        ActivityTwo::class.java
                )
        ))

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = MainAdapter(samples)
        recyclerView.adapter = adapter
    }

    private inner class MainAdapter internal constructor(private val samples: List<SampleItem>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

        internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            internal val nameView: TextView = view.findViewById(R.id.nameView)
            internal val descriptionView: TextView = view.findViewById(R.id.descriptionView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_main_feature, parent, false)

            view.setOnClickListener {
                val position = recyclerView.getChildLayoutPosition(view)
                val intent = Intent(view.context, samples[position].activity)
                startActivity(intent)
            }

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
            holder.nameView.text = samples[position].name
            holder.descriptionView.text = samples[position].description
        }

        override fun getItemCount(): Int {
            return samples.size
        }
    }
}
