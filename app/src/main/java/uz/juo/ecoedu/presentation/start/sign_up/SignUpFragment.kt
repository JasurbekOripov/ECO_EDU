package uz.juo.ecoedu.presentation.start.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.juo.ecoedu.databinding.FragmentSignUpBinding
import uz.juo.ecoedu.presentation.home.MainActivity

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.apply {
            singInBtn.setOnClickListener {
                Toast.makeText(requireContext(), "SIGN IN", Toast.LENGTH_SHORT).show()
            }
            saveBtn.setOnClickListener {
                requireActivity().finish()
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }

        return binding.root
    }


}