package unnum.mia;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import unnum.mia.bot.Mia;

public class Run {
    public static void main(String[] args) {
        TelegramBotsApi api;

        try{
            api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Mia());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}