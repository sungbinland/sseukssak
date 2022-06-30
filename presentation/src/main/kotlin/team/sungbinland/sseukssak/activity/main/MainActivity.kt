/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import dagger.hilt.android.AndroidEntryPoint
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseActivity
import team.sungbinland.sseukssak.databinding.ActivityMainBinding
import team.sungbinland.sseukssak.util.extensions.hide
import team.sungbinland.sseukssak.util.extensions.repeatOnStarted

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val vm: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = vm

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        setToolbar()
        setDrawer()
        repeatOnStarted {
            vm.eventFlow.collect(::handleEvent)
        }
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.OpenDrawer -> {
            binding.drawerLayout.open()
        }
    }

    private fun setToolbar() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.toolbarMain.hide(false)

//            val customToolbarFragmentSet = setOf(
//              R.id.~~
//            )

            when (destination.id) {
                R.id.nav_sseukssak -> binding.tvTitle.text =
                    getString(R.string.label_sseukssak_list)
            }

//            if (customToolbarFragmentSet.contains(destination.id)) binding.toolbarMain.hide(true)
        }
    }

    private fun setDrawer() {
        val drawerLayout = binding.drawerLayout

        listOf(
            binding.mainNavi.constraintSseukssakList,
            binding.mainNavi.constraintBoard,
            binding.mainNavi.constraintProfile,
            binding.mainNavi.btnService,
            binding.mainNavi.btnQuestion
        ).forEach { viewId -> viewId.setOnClickListener {
                drawerLayout.closeDrawers()
            }
        }
    }

}
