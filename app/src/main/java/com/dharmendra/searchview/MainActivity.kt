package com.dharmendra.searchview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find View By Id For Listview
        val listview = findViewById(R.id.listView) as ListView

        //Find View By Id For SearchView
        val searchView = findViewById(R.id.searchView) as SearchView

        /*Create and ArrayList of Integer Type To Store Images From drawable.Here we add Images to ArrayList.
        We have Images of Android Icons of Diffrent versions.
        */
        val image = ArrayList<Int>()
        image.add(R.drawable.cupcake)
        image.add(R.drawable.donut)
        image.add(R.drawable.eclair)
        image.add(R.drawable.froyo)
        image.add(R.drawable.gingerbread)
        image.add(R.drawable.honeycomb)
        image.add(R.drawable.icecreamsandwich)
        image.add(R.drawable.jellybean)
        image.add(R.drawable.kitkat)
        image.add(R.drawable.lollipop)
        image.add(R.drawable.marshmallow)
        image.add(R.drawable.nougat)
        image.add(R.drawable.oreo)


        // Here We take and Array of Android OS names in Same Sequence as we take Images.

        val name = arrayOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Icecreamsandwich",
                "Jellybean", "Kitkat", "Lollipop", "Marshmallow", "Nougat", "Oreo")


        // Here We take and Array of Android OS Version in Same Sequence as we take Images and name.

        val version = arrayOf("1.5", "1.6", "2.0", "2.2", "2.3", "3.0", "4.0",
                "4.1", "4.4", "5.0", "6.0", "7.0", "8.0")


        /*Create ArrayList of HashMap to Store Name and Version with Key value Pair at Same poition

        Ex:-
                At Position 1:
                                name:"Cupcake"
                                version:"1.5"
                At Position 2:
                                name:"Donut"
                                version:"1.6"
                                .
                                .
                                .
                                So On
        */
        val info = ArrayList<HashMap<String, String>>()

        //Here We take HashMap in that we add Name and Version from Array
        var hashMap: HashMap<String, String> = HashMap<String, String>()

        for (i in 0..name.size - 1) {
            hashMap = HashMap<String, String>()
            hashMap.put("name", name[i])
            hashMap.put("version", version[i])

            //Add HashMap to ArrayList
            info.add(hashMap)

            /*
            ArrayList Start with Position 0

             So we have At position 0:

                                name:"Cupcake"
                                version:"1.5"

            */

        }

        //We Have Created Custom Adapter Class in that we pass Context,Array of Image and ArrayList<Hashmap<String,String>>
        val customAdapter = CustomAdapter(this, image, info)


        //Set Adapter to ArrayList
        listview.adapter = customAdapter

        //On Click for ListView Item
        listview.setOnItemClickListener { adapterView, view, position, l ->

            //Provide the data on Click position in our listview
            val hashMap: HashMap<String, String> = customAdapter.getItem(position) as HashMap<String, String>

            Toast.makeText(this@MainActivity, "Name : " + hashMap.get("name") + "\nVersion : " + hashMap.get("version"), Toast.LENGTH_LONG).show()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val text = newText
                /*Call filter Method Created in Custom Adapter
                    This Method Filter ListView According to Search Keyword
                 */
                customAdapter.filter(text)
                return false
            }
        })

    }

}

