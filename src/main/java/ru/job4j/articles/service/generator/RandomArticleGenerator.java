package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        Collections.shuffle(words);
        StringJoiner sj = new StringJoiner(" ");
        words.stream()
                .map(Word::getValue)
                .forEach(sj::add);
        return new Article(sj.toString());
    }
}
