package uz.juo.ecoedu.presentation.start.code_check

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.juo.ecoedu.R
import uz.juo.ecoedu.databinding.FragmentCodeCheckBinding

class CodeCheckFragment : Fragment() {
    lateinit var binding: FragmentCodeCheckBinding
    lateinit var timer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeCheckBinding.inflate(inflater, container, false)

         timer = object : CountDownTimer(59000, 1000) {
            override fun onTick(second: Long) {
                binding.timerTv.text = "0:${(second/1000)}"
            }
            override fun onFinish() {
                binding.refreshBtn.visibility = View.VISIBLE
            }
        }.start()

        binding.refreshBtn.setOnClickListener {
            binding.refreshBtn.visibility = View.INVISIBLE
            timer.start()
        }

        binding.enterBtn.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        return binding.root
    }

}