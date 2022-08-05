package uz.juo.ecoedu.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.delay
import uz.juo.ecoedu.R
import uz.juo.ecoedu.databinding.ActivityMainBinding
import uz.juo.ecoedu.databinding.ActivityStartBinding
import uz.juo.ecoedu.ui.home.MainActivity

class StartActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityStartBinding


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navView: NavigationView = binding.
        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        navView.setupWithNavController(navController)
        Thread.sleep(1500)
            installSplashScreen()
            setContentView(R.layout.activity_start)
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
//        }
    }
}