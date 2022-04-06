package ru.dragonestia.jdash.converter;

import org.springframework.core.convert.converter.Converter;
import ru.dragonestia.jdash.gd.util.score.ScoreType;

public class ScoreTypeResolveConverter implements Converter<String, ScoreType> {

    @Override
    public ScoreType convert(String source) {
        return ScoreType.valueOf(source.toUpperCase());
    }
}
