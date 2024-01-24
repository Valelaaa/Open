package com.example.openmind.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.openmind.ui.navigation.Navigation
import com.example.openmindproject.ui.theme.OpenMindProjectTheme

class MainFragment : Fragment() {
    private var _contentView: View? = null
    private var contentView: View
        get() = _contentView!!
        set(value) {
            _contentView = value
        }
    private var _toolbar: Toolbar? = null;

    private var toolbar: Toolbar
        set(value) {
            _toolbar = value
        }
        get() = _toolbar!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OpenMindProjectTheme {
                    Navigation()
                }
            }
        }

    }

}