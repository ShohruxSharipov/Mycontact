package com.example.mycontact

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mycontact.Data.Database.AppDatabase
import com.example.mycontact.Data.Entity.User
import com.example.mycontact.Dialog.Dialogue
import com.example.mycontact.databinding.FragmentInformWindowBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformWin.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformWin : Fragment() {
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
        val binding = FragmentInformWindowBinding.inflate(inflater,container,false)
        var user = appData.runContact().getUserById(param1!!)
        binding.cName.text = user.name
        binding.cNumber.text = user.number

        binding.edit.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main,EditWindow.newInstance(param1!!)).addToBackStack("edit").commit()
        }

        binding.delete.setOnClickListener {
//            var a =Dialogue()
//            val manager = activity?.supportFragmentManager
//            a.show(manager!!,"")

            parentFragmentManager.beginTransaction().replace(R.id.main,MainWindow()).commit()
            appData.runContact().deleteUser(appData.runContact().getUserById(param1!!))
        }
        binding.call.setOnClickListener {
            openCall(user.number)
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
            InformWin().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
    fun openCall(number: String) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),
                1
            )
        } else {
            if (number.isNotEmpty()) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$number")
                activity?.startActivity(callIntent)
            }
        }
    }
}