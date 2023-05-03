package app.site.service.messagehandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Steve Zou
 */
public class ReplyingContentUtil {
    public static String welcomeMessage() {
        return "你好呀，欢迎加入我们！\n我是非智能聊天机器人，" + randomChoice(getChatBotNames()) + "！";
    }
    public static List<String> getUnsupportedMsgReplies() {
        ArrayList<String> replies = new ArrayList<>();
        replies.add("这个算是知识盲区了...");
        replies.add("这个咱不会...");
        replies.add("要不，咱们聊点别的？");
        replies.add("我觉得我们都应该冷静一下...");
        replies.add("这个咱不会，但是可以学，嘻嘻~");
        return replies;
    }

    public static List<String> getChatBotNames() {
        ArrayList<String> chatBotNames = new ArrayList<>();
        chatBotNames.add("小野");
        chatBotNames.add("小Z");
        chatBotNames.add("小L");
        chatBotNames.add("H");
        chatBotNames.add("MR.野");
        chatBotNames.add("MR.Z");
        chatBotNames.add("MR.L");
        chatBotNames.add("MR.H");
        return chatBotNames;
    }

    public static String randomChoice(List<String> choices) {
        if (choices.isEmpty()) return "";
        Random random = new Random();
        int i = random.nextInt(choices.size());
        return choices.get(i);
    }
}
