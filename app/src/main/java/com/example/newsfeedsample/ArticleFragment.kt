package com.example.newsfeedsample

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.newsfeedsample.model.Article
import kotlinx.android.synthetic.main.fragment_article.view.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.rc_item.view.*

class ArticleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        val article = arguments?.getParcelable<Article>("article")

        view.fragment_article_link.text = article?.url ?: ""
        view.fragment_article_image.load(article?.urlToImage) {
            error(R.drawable.icon_article_placeholder)
        }

        return view
    }
}