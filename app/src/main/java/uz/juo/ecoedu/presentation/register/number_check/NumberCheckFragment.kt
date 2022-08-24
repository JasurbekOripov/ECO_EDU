package uz.juo.ecoedu.presentation.register.number_check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.juo.ecoedu.R
import uz.juo.ecoedu.databinding.FragmentNumberCheckBinding


@AndroidEntryPoint
class NumberCheckFragment : Fragment() {
    lateinit var binding: FragmentNumberCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberCheckBinding.inflate(inflater, container, false)
        binding.ccp.registerCarrierNumberEditText(binding.etNumber)
        binding.enterBtn.setOnClickListener {
//            if (binding.etNumber.text.length == 9) {
                findNavController().navigate(R.id.codeCheckFragment)
//            } else {
//                Snackbar.make(
//                    binding.etNumber,
//                    "Raqamingizni to'liq kiritig",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
        }
        return binding.root
    }

}