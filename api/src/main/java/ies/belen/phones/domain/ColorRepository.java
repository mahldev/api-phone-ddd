package ies.belen.phones.domain;

import java.util.Optional;

public interface ColorRepository {

    Color create(Color color);

    Optional<Color> findByName(Color.ColorEnum color);

}
