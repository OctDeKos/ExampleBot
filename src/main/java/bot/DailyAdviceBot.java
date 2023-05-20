package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DailyAdviceBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // здесь вы можете обработать входящее сообщение, если это необходимо
    }

    @Override
    public String getBotUsername() {
        // возвращает имя бота, как оно указано в Telegram
        return "DailyAdviceBot";
    }

    @Override
    public String getBotToken() {
        // возвращает токен, полученный от @BotFather
        return "your_bot_token_here";
    }


    void sendDailyAdvice() {
        SendMessage sendMessage = new SendMessage();
        //sendMessage.setChatId(chatId.toString());
        DailyAdviceProvider adviceProvider = new DailyAdviceProvider();
        String advice = adviceProvider.getRandomAdvice();
        sendMessage.setText(advice);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

