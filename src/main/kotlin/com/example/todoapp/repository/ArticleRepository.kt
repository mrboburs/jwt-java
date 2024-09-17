package com.example.todoapp.repository

import com.example.todoapp.model.Article
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class ArticleRepository {
    private  val articleList = mutableListOf(
        Article(id = UUID.randomUUID(), title = "article1", content = "content1"),
        Article(id = UUID.randomUUID(), title = "article2", content = "content2"),
        Article(id = UUID.randomUUID(), title = "article3", content = "content3"),
    )

    fun findAll(): List<Article> = articleList
}