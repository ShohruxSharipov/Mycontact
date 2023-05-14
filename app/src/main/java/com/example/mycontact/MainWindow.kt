package com.example.mycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.mycontact.Adapter.Adapter
import com.example.mycontact.Data.Database.AppDatabase
import com.example.mycontact.databinding.FragmentMainWindowBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainWindow.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainWindow : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val appData: AppDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

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
       val binding = FragmentMainWindowBinding.inflate(inflater,container,false)

        val list = appData.runContact().getUsers()
        val adapter = Adapter(list,object : Adapter.OnClickContact{
            override fun setOnClickContact(id: Int) {
                val bundle = bundleOf("id" to id)
                parentFragmentManager.beginTransaction().replace(R.id.main,InformWin.newInstance(id)).addToBackStack("MainWindow").commit()
            }

        })
        binding.contactList.adapter = adapter

        binding.addContact.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main,NewContact()).addToBackStack("MainWindow").commit()
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
         * @return A new instance of fragment MainWindow.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainWindow().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}