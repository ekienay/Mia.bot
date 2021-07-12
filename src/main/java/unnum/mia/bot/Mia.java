package unnum.mia.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Mia extends TelegramLongPollingBot {

    public void sendMassages (Message message, String text){
        SendMessage botMassage = new SendMessage();
        botMassage.enableMarkdown(true);
        botMassage.setChatId(message.getChatId().toString());
        botMassage.setReplyToMessageId(message.getMessageId());
        botMassage.setText(text);
        try {
            showButtons(botMassage);
            execute(botMassage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void showButtons(SendMessage message){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(markup);
        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);

        List<KeyboardRow> list = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("?Погода"));
        row.add(new KeyboardButton("?Курс доллора"));

        list.add(row);
        markup.setKeyboard(list);
    }

    @Override
    public String getBotUsername () {
        return "MiaUnnumBot";
    }

    @Override
    public String getBotToken () {
        return "1855475979:AAE6LWIkGRZEASNmnZDTSHlK7htZt8DZcWI";
    }

    @Override
    public void onUpdateReceived (Update update){
        Message massage = update.getMessage();
        if (massage.getText() != null && massage.hasText()) {
            switch (massage.getText()) {
                case "!Mia":
                    sendMassages(massage, "Привет, чем могу быть полезной?");
                    break;
                case "?Погода":
                    sendMassages(massage,"Выбери город");
                     break;
                case "?Курс доллора":
                    sendMassages(massage,"Ваш курс: 77.09");
                    break;
                default:
            }
        }
    }
}
