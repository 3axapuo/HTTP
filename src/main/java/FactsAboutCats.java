import com.fasterxml.jackson.annotation.JsonProperty;

public class FactsAboutCats {

    private String id;
    private String text;
    private String type;
    private String user;
    private int upvotes;

    public FactsAboutCats(@JsonProperty("id") String id, // уникальный идентификатор записи
                          @JsonProperty("text") String text, // сообщение
                          @JsonProperty("type") String type, // тип животного
                          @JsonProperty("user") String user, // имя пользователя
                          @JsonProperty("upvotes") int upvotes) { // голоса
        this.id = id; // уникальный идентификатор записи
        this.text = text; // сообщение
        this.type = type; // тип животного
        this.user = user; // имя пользователя
        this.upvotes = upvotes; // голоса
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "ФАКТ: "
                + "\n Уникальный идентификатор записи (id): " + id
                + "\n Сообщение (text): " + text
                + "\n Тип животного (type): " + type
                + "\n Имя пользователя (user): " + user
                + "\n Голоса (upvotes): " + upvotes + "\n";
    }
}
