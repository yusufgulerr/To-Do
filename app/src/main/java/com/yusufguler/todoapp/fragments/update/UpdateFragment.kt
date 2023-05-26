package com.yusufguler.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusufguler.todoapp.R
import com.yusufguler.todoapp.data.models.ToDoData
import com.yusufguler.todoapp.data.viewmodel.ToDoViewModel
import com.yusufguler.todoapp.databinding.FragmentUpdateBinding
import com.yusufguler.todoapp.data.viewmodel.SharedViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel : SharedViewModel by viewModels()
    private val mToDoViewModel : ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.args = args

        //Spinner Item Selected Listener
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title,description)

        if(validation){
            //Update current item
            val updatedItem = ToDoData(
                args.current.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )

            mToDoViewModel.updateData(updatedItem)

            Toast.makeText(requireContext(),"Successfully updated",Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_LONG).show()
        }

    }
    //show AlertDialog to Confirm Removal of  Item
    private fun confirmItemRemoval() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setPositiveButton("Yes"){_,_ ->
            mToDoViewModel.deleteData(args.current)
            Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        alertDialog.setNegativeButton("No"){_,_ -> }
        alertDialog.setTitle("Delete '${args.current.title}'?")
        alertDialog.setMessage("Are you sure you want to remove '${args.current.title}'")
        alertDialog.create().show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}