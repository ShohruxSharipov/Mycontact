package com.example.mycontact

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mycontact.Data.Database.AppDatabase
import com.example.mycontact.Data.Entity.User
import com.example.mycontact.databinding.FragmentEditWindow2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditWindow.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditWindow : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    val appData: AppDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditWindow2Binding.inflate(inflater,container,false)
        var user = appData.runContact().getUserById(param1!!)
        binding.cName.text = Editable.Factory.getInstance().newEditable(user.name)
        binding.cNumber.text = Editable.Factory.getInstance().newEditable(user.number)

        binding.done.setOnClickListener {
            val name = binding.cName.text.toString()
            val number = binding.cNumber.text.toString()
            if (name.isEmpty() || number.isEmpty()){
                Toast.makeText(requireContext(), "FIll all", Toast.LENGTH_SHORT).show()
            }
            else{
                appData.runContact().updateUser(name,number,param1!!)
                Toast.makeText(requireContext(), "All Done !", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().replace(R.id.main,MainWindow()).commit()
            }
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditWindow.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            EditWindow().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}