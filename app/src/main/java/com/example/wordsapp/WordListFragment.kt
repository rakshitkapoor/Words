package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentLetterListBinding
import com.example.wordsapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    companion object {
        const val LETTER="letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
    private var _binding:FragmentWordListBinding?=null
    private val binding get() = _binding!!
    private lateinit var LetterID:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            LetterID=it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWordListBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val letterId =LetterID

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordAdapter(letterId,this.requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL)
        )

       val title = getString(R.string.detail_prefix) + " " + LetterID
        requireActivity().title=title

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}