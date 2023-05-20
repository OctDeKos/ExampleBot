package bot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DailyAdviceProvider {
    private List<String> adviceList;

    public DailyAdviceProvider() {
        adviceList = new ArrayList<>();
        adviceList.add("Не бойтесь делать ошибки, ошибки помогают нам учиться.");
        adviceList.add("Делайте то, что вам нравится, и вы никогда не будете работать ни дня в своей жизни.");
        adviceList.add("Важно находить время для себя и своих близких, чтобы не упустить самое важное в жизни.");
        // добавьте свои советы в список
    }

    public String getRandomAdvice() {
        Random random = new Random();
        int index = random.nextInt(adviceList.size());
        return adviceList.get(index);
    }
}
