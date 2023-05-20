package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MySportsBot extends TelegramLongPollingBot {
    private static final String START_MESSAGE = "Добро пожаловать! Для регистрации на гонку введите свои данные. Введите /help для помощи.";
    private static final String HELP_MESSAGE = "Пожалуйста, введите свои данные для регистрации на гонку. Вы можете ввести: Фамилия, Имя, Отчество (не обязательно), пол, возраст, команда, выбираемая дистанция, электронная почта, телефон (не обязательно).";
    private static final String REGISTER_MESSAGE = "Спасибо! Вы успешно зарегистрировались на гонку.";

    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private String age;
    private String team;
    private String distance;
    private String email;
    private String phone;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();

            if (messageText.equalsIgnoreCase("/start")) {
                sendTextMessage(chatId, START_MESSAGE);
            } else if (messageText.equalsIgnoreCase("/help")) {
                sendTextMessage(chatId, HELP_MESSAGE);
            } else if (messageText.equalsIgnoreCase("/register")) {
                String registrationMessage = String.format("Вы зарегистрировались на гонку с данными: Фамилия: %s, Имя: %s", lastName, firstName);
                if (middleName != null) {
                    registrationMessage += String.format(", Отчество: %s", middleName);
                }
                if (gender != null) {
                    registrationMessage += String.format(", Пол: %s", gender);
                }
                if (age != null) {
                    registrationMessage += String.format(", Возраст: %s", age);
                }
                if (team != null) {
                    registrationMessage += String.format(", Команда: %s", team);
                }
                if (distance != null) {
                    registrationMessage += String.format(", Дистанция: %s", distance);
                }
                if (email != null) {
                    registrationMessage += String.format(", Электронная почта: %s", email);
                }
                if (phone != null) {
                    registrationMessage += String.format(", Телефон: %s", phone);
                }

                sendTextMessage(chatId, registrationMessage);
            } else {
                if (lastName == null) {
                    lastName = messageText;
                    sendTextMessage(chatId, "Введите ваше Имя:");
                } else if (firstName == null) {
                    firstName = messageText;
                    sendTextMessage(chatId, "Введите ваше Отчество (не обязательно):");
                } else if (middleName == null) {
                    middleName = messageText;
                } else if (gender == null) {
                    gender = messageText;
                    sendTextMessage(chatId, "Введите ваш возраст:");
                } else if (age == null) {
                    age = messageText;
                    sendTextMessage(chatId, "Введите вашу команду:");
                } else if (team == null) {
                    team = messageText;
                    sendTextMessage(chatId, "Введите выбираемую дистанцию:");
                } else if (distance == null) {
                    distance = messageText;
                    sendTextMessage(chatId, "Введите вашу электронную почту:");
                } else if (email == null) {
                    email = messageText;
                    sendTextMessage(chatId, "Введите ваш телефон (не обязательно):");
                } else if (phone == null) {
                    phone = messageText;
                    sendTextMessage(chatId, REGISTER_MESSAGE);
                }
            }
        }
    }

    private void sendTextMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "BotNameBot";
    }

    @Override
    public String getBotToken() {
        return "TOKEN_HERE";
    }
}


