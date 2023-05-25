package com.yusufguler.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yusufguler.todoapp.R
import com.yusufguler.todoapp.data.models.Priority
import com.yusufguler.todoapp.databinding.FragmentAddBinding
import com.yusufguler.todoapp.databinding.FragmentUpdateBinding
import com.yusufguler.todoapp.fragments.SharedViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel : SharedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.currentTitleEt.setText(args.current.title)
        binding.currentDescriptionEt.setText(args.current.description)
        binding.currentPrioritiesSpinner.setSelection(parsePriority(args.current.priority))
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    private fun parsePriority(priortiy : Priority):Int{
        return when(priortiy){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }


}