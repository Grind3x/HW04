import java.util.Objects;

public class Symbol implements SentenceElement{
    private Character character;

    public Symbol() {
    }

    public Symbol(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(character, symbol.character);
    }

    @Override
    public int hashCode() {

        return Objects.hash(character);
    }

    @Override
    public String toString() {
        return character.toString();
    }
}
