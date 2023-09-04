package ru.job4j.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;
import ru.job4j.articles.service.generator.ArticleGenerator;
import ru.job4j.articles.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleArticleService implements ArticleService {
    private final Logger logger = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());
    private final ArticleGenerator articleGenerator;
    public SimpleArticleService(ArticleGenerator articleGenerator) {
        this.articleGenerator = articleGenerator;
    }

    @Override
    public void generate(Store<Word> wordStore, int count, Store<Article> articleStore) {
        logger.info("Генерация статей в количестве {}", count);
        var words = wordStore.findAll();
//        List<Article> articles = IntStream.iterate(0, i -> i + 1).limit(count)
//                .peek(i -> logger.info("Сгенерирована статья № {}", i))
//                .mapToObj(x -> articleGenerator.generate(words))
//                .collect(Collectors.toList());
        List<Article> articles = new ArrayList<>(count / 4);
        for (int i = 0; i <= count; i++) {
            logger.info("Сгенерирована статья № {}", i);
            articles.add(articleGenerator.generate(words));
            if (i == count / 4 + 1) {
                articles.forEach(articleStore::save);
                articles.clear();
            }
        }
    }
}
