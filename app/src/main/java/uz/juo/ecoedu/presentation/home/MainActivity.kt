package uz.juo.ecoedu.presentation.home

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.juo.ecoedu.R
import uz.juo.ecoedu.databinding.ActivityMainBinding
@AndroidEntryPoint
class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_article, R.id.nav_quiz
            ), drawerLayout
        )

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.apply {
            setViewScale(Gravity.START, 0.9f)
//            setViewElevation(Gravity.START, 20f)
            useCustomBehavior(Gravity.END)
            setViewRotation(GravityCompat.START, 15F);
            setViewScrimColor(GravityCompat.START, Color.TRANSPARENT); //set drawer overlay coloe (color)
            drawerElevation = 20F; //set drawer elevation (dimension)
            setContrastThreshold(3F); //set maximum of contrast ratio between white text and background color.
            setRadius(GravityCompat.START, 25F);
        }
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
           }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.action_right -> {
//                binding.drawerLayout.openDrawer(GravityCompat.END)
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}