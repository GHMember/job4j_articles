package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        Collections.shuffle(words);
        List<String> textList = new ArrayList<>(words.size());
        words.stream()
                .map(Word::getValue)
                .forEach(textList::add);
        return new Article(String.join(" ", textList));
    }
}
