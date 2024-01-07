package ies.belen.phones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = "id")
public class Color {

  public enum ColorEnum {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    ORANGE,
    PURPLE,
    PINK,
    BROWN,
    BLACK,
    WHITE,
    GRAY,
    SILVER,
    GOLD,
    CYAN,
    MAGENTA,
    LIME,
    INDIGO,
    VIOLET,
    TEAL,
    OLIVE
  }

  public static ColorEnum fromStringToColorEnum(String colorCode) {
    switch (colorCode.toLowerCase()) {
      case "red":
        return ColorEnum.RED;
      case "blue":
        return ColorEnum.BLUE;
      case "green":
        return ColorEnum.GREEN;
      case "yellow":
        return ColorEnum.YELLOW;
      case "orange":
        return ColorEnum.ORANGE;
      case "purple":
        return ColorEnum.PURPLE;
      case "pink":
        return ColorEnum.PINK;
      case "brown":
        return ColorEnum.BROWN;
      case "black":
        return ColorEnum.BLACK;
      case "white":
        return ColorEnum.WHITE;
      case "gray":
        return ColorEnum.GRAY;
      case "silver":
        return ColorEnum.SILVER;
      case "gold":
        return ColorEnum.GOLD;
      case "cyan":
        return ColorEnum.CYAN;
      case "magenta":
        return ColorEnum.MAGENTA;
      case "lime":
        return ColorEnum.LIME;
      case "indigo":
        return ColorEnum.INDIGO;
      case "violet":
        return ColorEnum.VIOLET;
      case "teal":
        return ColorEnum.TEAL;
      case "olive":
        return ColorEnum.OLIVE;
      default:
        throw new IllegalArgumentException("Invalid colorCode value");
    }
  }

  public static String fromColorEnumToString(ColorEnum colorEnum) {
    switch (colorEnum) {
      case RED:
        return "red";
      case BLUE:
        return "blue";
      case GREEN:
        return "green";
      case YELLOW:
        return "yellow";
      case ORANGE:
        return "orange";
      case PURPLE:
        return "purple";
      case PINK:
        return "pink";
      case BROWN:
        return "brown";
      case BLACK:
        return "black";
      case WHITE:
        return "white";
      case GRAY:
        return "gray";
      case SILVER:
        return "silver";
      case GOLD:
        return "gold";
      case CYAN:
        return "cyan";
      case MAGENTA:
        return "magenta";
      case LIME:
        return "lime";
      case INDIGO:
        return "indigo";
      case VIOLET:
        return "violet";
      case TEAL:
        return "teal";
      case OLIVE:
        return "olive";
      default:
        throw new IllegalArgumentException("Invalid ColorEnum value");
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private ColorEnum color;

  public Color(String name) {
    this.color = fromStringToColorEnum(name);
  }

  public Color(ColorEnum colorEnum) {
    this.color = colorEnum;
  }

}
