package uz.juo.ecoedu.presentation.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.juo.ecoedu.R
import uz.juo.ecoedu.databinding.ActivityStartBinding


@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1500)
        installSplashScreen()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findNavController(R.id.nav_host_fragment_content_start)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_start)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}