package com.yusufguler.todoapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.yusufguler.todoapp.R
import com.yusufguler.todoapp.data.viewmodel.SharedViewModel
import com.yusufguler.todoapp.data.viewmodel.ToDoViewModel
import com.yusufguler.todoapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        //set up recyclerview
        setupRecyclerView()

        //Observe live data
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })



        //set menu
        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            confirmRemoval()
        }

        return super.onOptionsItemSelected(item)
    }

    //Show AlertDialog to Confirm Removal of All Items from Data Base Table
    private fun confirmRemoval() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(), "All Deleted", Toast.LENGTH_LONG).show()
        }
        alertDialog.setNegativeButton("No") { _, _ -> }
        alertDialog.setTitle("Do you want to delete all ?")
        alertDialog.setMessage("Are you sure you want to remove all?")
        alertDialog.create().show()
    }



    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}