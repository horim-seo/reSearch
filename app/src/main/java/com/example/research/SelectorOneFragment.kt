package com.example.research

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectorOneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectorOneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // 리사이클러 뷰 설정
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var viewManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_selector_one, container, false)

        //리사이클러 뷰 설정
        viewManager = LinearLayoutManager(MyApplication.ApplicationContext())

        recyclerView = rootView.findViewById<RecyclerView>(R.id.my_recycler_view).apply {

            setHasFixedSize(true)

            layoutManager = viewManager

        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retroService = retrofit.create(RetrofitService::class.java)
        val call: Call<FirstData> = retroService.getNews()

        call.enqueue(object: Callback<FirstData> {
            override fun onResponse(
                call: Call<FirstData>,
                response: retrofit2.Response<FirstData>
            ) {
                val data:FirstData? = response.body()
                var dataStr = ""

                var dataSize = data!!.articles.size

                val news: MutableList<FirstDataSet> = ArrayList()

                for (i in 0 until dataSize) {
                    val obj = data!!.articles.get(i)

                    val newsdata = FirstDataSet()
                    newsdata.title = obj.title
                    newsdata.description = obj.description
                    newsdata.urlToImage = obj.urlToImage
                    newsdata.content = obj.content

                    Log.d("Succed:title", "newstitle:$obj.title")
                    Log.d("Succed:newsdata.title", "newsdata.title:${newsdata.title.toString()}")

                    news.add(newsdata)
                }

                mAdapter = FirstAdapter(news)
                recyclerView!!.adapter = mAdapter

            }

            override fun onFailure(call: Call<FirstData>, t: Throwable) {
                Log.d("Failed:", "Failed:${t.message}")
            }
        })

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectorOneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectorOneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}