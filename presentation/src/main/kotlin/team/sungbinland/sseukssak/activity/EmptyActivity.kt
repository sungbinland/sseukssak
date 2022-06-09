/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import dagger.hilt.android.AndroidEntryPoint
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseActivity
import team.sungbinland.sseukssak.databinding.ActivityEmptyBinding
import team.sungbinland.sseukssak.util.extensions.repeatOnStarted
import team.sungbinland.sseukssak.util.extensions.toast

@AndroidEntryPoint
class EmptyActivity : BaseActivity<ActivityEmptyBinding, EmptyViewModel>(R.layout.activity_empty) {
    override val vm: EmptyViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = vm

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        setToolbar()

        setDrawer()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    private fun handleEvent(event: EmptyViewModel.Event) = when (event) {
        is EmptyViewModel.Event.OpenDrawer -> {
            binding.drawerLayout.open()
        }
    }

    private fun setToolbar() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.toolbarMain.visibility = View.VISIBLE

//            val customToolbarFragmentSet = setOf(
//              R.id.~~
//            )

            when (destination.id) {
                R.id.nav_sseukssak -> binding.toolbarTitle.text =
                    getString(R.string.label_sseukssak_list)
            }

//            if (customToolbarFragmentSet.contains(destination.id)) binding.toolbarMain.visibility = View.GONE
        }
    }

    private fun setDrawer() {
        drawerLayout = binding.drawerLayout

        binding.mainNavi.constraintSseukssakList.setOnClickListener {
            drawerLayout.closeDrawers()
        }

        binding.mainNavi.constraintBoard.setOnClickListener {
            drawerLayout.closeDrawers()
        }

        binding.mainNavi.constraintProfile.setOnClickListener {
            drawerLayout.closeDrawers()
        }

        binding.mainNavi.btnService.setOnClickListener {
            drawerLayout.closeDrawers()
        }

        binding.mainNavi.btnQuestion.setOnClickListener {
            drawerLayout.closeDrawers()
        }
    }

}
