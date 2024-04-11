package com.example.openmind.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.ui.navigation.Navigation
import com.example.openmind.utils.SessionManager
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

class MainFragment : Fragment() {

    private lateinit var navController: NavHostController

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SessionManager.init(requireContext())
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OpenMindProjectTheme {
                    navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }

}