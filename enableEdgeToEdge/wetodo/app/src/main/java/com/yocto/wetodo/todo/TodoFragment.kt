package com.yocto.wetodo.todo

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.yocto.wetodo.MainActivity
import com.yocto.wetodo.R
import com.yocto.wetodo.databinding.FragmentGalleryBinding
import com.yocto.wetodo.databinding.TodoFragmentBinding

class TodoFragment : Fragment() {
    private var _binding: TodoFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle

    private var colorPrimary: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initResource()

        updateStatusBarColor()
    }

    private fun initResource() {
        val typedValue = TypedValue()
        val theme = requireContext().theme
        theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true)
        colorPrimary = typedValue.data;
    }

    private fun updateStatusBarColor() {
        (activity as? MainActivity)?.updateStatusBarColor(colorPrimary)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initToolbar()

        return root
    }

    private fun initToolbar() {
        this.toolbar = binding.toolbar

        this.toolbar.title = "Hello Todo!"

        val activity = activity as? MainActivity ?: return

        this.actionBarDrawerToggle = object : ActionBarDrawerToggle(
            activity,
            activity.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
        }

        this.actionBarDrawerToggle.syncState()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TodoFragment()
    }
}