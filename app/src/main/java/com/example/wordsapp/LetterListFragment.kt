package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {
    private var _binding:FragmentLetterListBinding?=null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLetterListBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView=binding.recyclerView
        chooseLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu,menu)
        val layoutButton=menu.findItem(R.id.action_switch)
        chooseIcon(layoutButton)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
    private fun chooseLayout(){
        Log.d("MainActivity","chooseLayout called")
        if (isLinearLayoutManager){
            recyclerView.layoutManager= LinearLayoutManager(context)
        }
        else{
            recyclerView.layoutManager= GridLayoutManager(context,4)
        }
        recyclerView.adapter=LetterAdapter()
    }

    private fun chooseIcon(itemMenu:MenuItem?){
        Log.d("MainActivity","chooseIcon called")
        if (itemMenu==null){
            return
        }
        itemMenu.icon=
            if (isLinearLayoutManager){
                ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_grid_layout)
            }else{
                ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_list_layout)
            }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity","onOptionsItemSelected called")
        return when(item.itemId){
            R.id.action_switch-> {
                isLinearLayoutManager= !isLinearLayoutManager
                chooseLayout()
                chooseIcon(item)
                return true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

}