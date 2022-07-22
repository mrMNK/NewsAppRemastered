package com.mrmnk.newsappremastered.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mrmnk.newsappremastered.R
import com.mrmnk.newsappremastered.databinding.FragmentNewsDetailBinding
import com.squareup.picasso.Picasso
import java.lang.Exception
import javax.inject.Inject

class NewsDetailFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as NewsApp).component
    }

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding: FragmentNewsDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsDetailBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = getTitle()
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        viewModel.getNewsInfo(title).observe(viewLifecycleOwner) {
            with(binding) {
                newsDetailTitle.text = it.title
                newsDetailContent.text = it.content
                newsDetailPublishedAt.text = it.publishedAt
                loadImage(it.urlToImage, newsDetailImageView)
            }
        }
    }

    /**
     *Gets the news title from the Bundle object
     */
    private fun getTitle(): String {
        return requireArguments().getString(EXTRA_TITLE, EMPTY_STRING)
    }

    /**
     * Loads image from URL, if URL == null sets image from R.mipmap
     */
    private fun loadImage(url: String, imageView: ImageView) {
        try {
            Picasso.get().load(url).into(imageView)
        } catch (e: Exception) {
            Picasso.get().load(R.mipmap.ic_launcher).into(imageView)
        }
    }

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EMPTY_STRING = ""

        fun newInstance(title: String): Fragment {
            return NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TITLE, title)
                }
            }
        }
    }
}
