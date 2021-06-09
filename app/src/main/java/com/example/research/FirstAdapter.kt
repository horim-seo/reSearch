package com.example.research

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.research.fragmentlist.Fragmentlist
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.row_research.view.*
import java.util.HashMap

class FirstAdapter (private val dataSet: List<FirstDataSet>):
    RecyclerView.Adapter<FirstAdapter.ViewHolder>(){

    val database = Firebase.database
    val myRef = database.getReference("newsData")

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView
        val textViewContent: TextView
        val textUri:TextView
        var textPosition:TextView
        val imageView: SimpleDraweeView
        lateinit var rootView: View
        init {
            // Define click listener for the ViewHolder's View.
            textViewTitle = view.findViewById<TextView>(R.id.tTextView)
            textViewContent = view.findViewById<TextView>(R.id.cTextView)
            textUri = view.findViewById<TextView>(R.id.vTextView)
            textPosition = view.findViewById<TextView>(R.id.vPosition)
            imageView = view.findViewById<SimpleDraweeView>(R.id.tImageView)
            rootView = view

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        Fresco.initialize(MyApplication.ApplicationContext())
        //Fresco.initialize(viewGroup.context)

        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_research, viewGroup, false)

        // 설문조사 화면 전환
        view.setOnClickListener { view ->
            //var item = mutableMapOf<String, String>()
            var item: ArrayList<String> = ArrayList()
            var title = view.tTextView.text.toString()
            var description = view.cTextView.text.toString()
            var uri = view.vTextView.text.toString()
            var textPosition = view.vPosition.text.toString()

            //item.put("title", title)
            //item.put("description", description)

            item.add(title)
            item.add(description)
            item.add(uri)
            item.add(textPosition)

            Intent(MyApplication.ApplicationContext(), Fragmentlist::class.java).apply {
                putExtra("data", item)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run {
                MyApplication.ApplicationContext().startActivity(this) }
        }

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        var newsData: FirstDataSet = dataSet.get(position)

        var uri: Uri = Uri.parse(newsData.urlToImage.toString())

        var title = newsData.title.toString()
        var content = newsData.content.toString()
        var description = newsData.description.toString()
        var stringUri = newsData.urlToImage.toString()

        Log.d("title", "title : "+title)
        Log.d("content", "content : "+content)

        viewHolder.textViewTitle.text = newsData.title.toString()
        //viewHolder.textViewContent.text = newsData.content.toString()

        viewHolder.textUri.text = stringUri
        viewHolder.textViewContent.text = description
        viewHolder.imageView.setImageURI(uri)
        viewHolder.textPosition.text = position.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}