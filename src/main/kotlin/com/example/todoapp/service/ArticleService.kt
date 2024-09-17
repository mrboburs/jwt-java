package com.example.todoapp.service

import com.example.todoapp.model.Article
import com.example.todoapp.repository.ArticleRepository
import org.springframework.stereotype.Service


@Service
class ArticleService(
    val articleRepository: ArticleRepository
) {
    fun findAll(): List<Article> = articleRepository.findAll()
}