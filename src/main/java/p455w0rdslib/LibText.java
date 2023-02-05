package p455w0rdslib;

import net.minecraft.util.EnumChatFormatting;

public class LibText {

    public static final String RADIO_BARS;
    public static final String LINE_SEP = "==========================";
    public static final String BLACK;
    public static final String D_BLUE;
    public static final String D_GREEN;
    public static final String D_AQUA;
    public static final String D_RED;
    public static final String D_PURPLE;
    public static final String GOLD;
    public static final String GRAY;
    public static final String D_GRAY;
    public static final String BLUE;
    public static final String GREEN;
    public static final String AQUA;
    public static final String RED;
    public static final String L_PURPLE;
    public static final String YELLOW;
    public static final String WHITE;
    public static final String RESET;
    public static final String OBF;
    public static final String BOLD;
    public static final String UNDER;
    public static final String STRIKE;
    public static final String ITALIC;

    public static String wrapRadioBars(String text) {
        return GOLD + RADIO_BARS + WHITE + BOLD + text + RESET + GOLD + RADIO_BARS;
    }

    public static String lineSep(String color) {
        return color + "==========================" + RESET;
    }

    static {
        RADIO_BARS = EnumChatFormatting.OBFUSCATED + "|||||||" + EnumChatFormatting.RESET;
        BLACK = EnumChatFormatting.BLACK.toString();
        D_BLUE = EnumChatFormatting.DARK_BLUE.toString();
        D_GREEN = EnumChatFormatting.DARK_GREEN.toString();
        D_AQUA = EnumChatFormatting.DARK_AQUA.toString();
        D_RED = EnumChatFormatting.DARK_RED.toString();
        D_PURPLE = EnumChatFormatting.DARK_PURPLE.toString();
        GOLD = EnumChatFormatting.GOLD.toString();
        GRAY = EnumChatFormatting.GRAY.toString();
        D_GRAY = EnumChatFormatting.DARK_GRAY.toString();
        BLUE = EnumChatFormatting.BLUE.toString();
        GREEN = EnumChatFormatting.GREEN.toString();
        AQUA = EnumChatFormatting.AQUA.toString();
        RED = EnumChatFormatting.RED.toString();
        L_PURPLE = EnumChatFormatting.LIGHT_PURPLE.toString();
        YELLOW = EnumChatFormatting.YELLOW.toString();
        WHITE = EnumChatFormatting.WHITE.toString();
        RESET = EnumChatFormatting.RESET.toString();
        OBF = EnumChatFormatting.OBFUSCATED.toString();
        BOLD = EnumChatFormatting.BOLD.toString();
        UNDER = EnumChatFormatting.UNDERLINE.toString();
        STRIKE = EnumChatFormatting.STRIKETHROUGH.toString();
        ITALIC = EnumChatFormatting.ITALIC.toString();
    }
}
