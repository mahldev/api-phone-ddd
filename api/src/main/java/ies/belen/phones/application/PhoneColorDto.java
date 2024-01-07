package ies.belen.phones.application;

import ies.belen.phones.domain.Color;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneColor;

public record PhoneColorDto(

        String commercialName,

        ColorDto color

) {

    public static PhoneColor toPhoneColor(String commercialName, Phone phone, Color color) {
        return new PhoneColor(commercialName, phone, color);
    }
}
