package com.mrmnk.newsappremastered.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val adapter = NewsInfoAdapter(requireContext())
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
    }

    private fun launchNewsDetailFragment(title: String) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsDetailFragment.newInstance(title))
            .addToBackStack(null)
            .commit()
    }
}