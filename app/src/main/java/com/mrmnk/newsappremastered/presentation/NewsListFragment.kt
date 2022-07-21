package com.mrmnk.newsappremastered.presentation

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mrmnk.newsappremastered.R
import com.mrmnk.newsappremastered.databinding.FragmentNewsListBinding
import com.mrmnk.newsappremastered.domain.NewsInfo
import com.mrmnk.newsappremastered.presentation.adapters.NewsInfoAdapter
import javax.inject.Inject

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as NewsApp).component
    }

    private var _binding: FragmentNewsListBinding? = null
    private val binding: FragmentNewsListBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsListBinding is null")

    private lateinit var adapter: NewsInfoAdapter

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = NewsInfoAdapter(requireContext())
        adapter.onNewsClickListener = object : NewsInfoAdapter.OnNewsClickListener {
            override fun onNewsClick(newsInfo: NewsInfo) {
                launchNewsDetailFragment(newsInfo.title)
            }
        }
        binding.newsRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        viewModel.newsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        setOnEnterKeyListener()
    }

    /**
     *Sets the handler for pressing the "Enter" key when entering text in an EditText
     */
    private fun setOnEnterKeyListener() {
        binding.searchEditTV.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getSortedNews()
                hideKeyboard(v)
                return@OnKeyListener true
            }
            false
        })
    }

    /**
     * Filters news by occurrence (case of letters of the searched phrase is ignored)
     */
    private fun getSortedNews() {
        val key = binding.searchEditTV.text.toString()
        viewModel.getSortedList(key)
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
    }


    /**
     *Hide keyboard
     */
    private fun hideKeyboard(v: View) {
        val imm = activity
            ?.applicationContext
            ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    /**
     *Launch NewsDetailFragment
     */
    private fun launchNewsDetailFragment(title: String) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsDetailFragment.newInstance(title))
            .addToBackStack(null)
            .commit()
    }
}